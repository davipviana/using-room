package com.davipviana.usingroom.database.converters

import android.arch.persistence.room.TypeConverter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateConverter {
    val DATE_PATTERN = "dd/MM/yyyy"
    val FORMAT = SimpleDateFormat(DATE_PATTERN)


    @TypeConverter
    fun convert(date: Calendar): Long {

        return date.time.time
    }

    @TypeConverter
    fun convert(timeInMiliseconds: Long?): Calendar {

        val calendar = Calendar.getInstance()

        calendar.time = Date(timeInMiliseconds!!)

        return calendar

    }

    fun convert(date: String): Calendar {

        val calendar = Calendar.getInstance()
        try {
            val date = FORMAT.parse(date)


            calendar.time = date


        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return calendar
    }


    fun convertToString(date: Calendar): String {

        return FORMAT.format(date.time)
    }
}