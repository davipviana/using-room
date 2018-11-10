package com.davipviana.usingroom.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.migration.Migration
import android.content.Context

class DatabaseFactory {
    companion object {
        private const val DATABASE_NAME: String = "AppDB";
    }

    fun getDatabase(context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .allowMainThreadQueries()
            .addMigrations(migrationFrom2To3())
            .build()
    }

    private fun migrationFrom2To3(): Migration {
        return object : Migration(2,3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                val sql = "alter table Exam add column examResultDate integer not null default 0;"
                database.execSQL(sql)
            }

        }
    }
}