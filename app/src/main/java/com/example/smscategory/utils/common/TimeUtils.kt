package com.example.smscategory.utils.common

import java.text.SimpleDateFormat
import java.util.*


object TimeUtils {

    private const val SECOND_MILLIS = 1000
    private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
    private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
    private const val DAY_MILLIS = 24 * HOUR_MILLIS



    fun getDay(date:String): String? {
        return SimpleDateFormat("dd/MM/yyyy")
            .format(Date(date.toLong()))
    }

    fun getDifferenceHour(time1:String): Long {

//
//        val simpleDateFormat = SimpleDateFormat("HH:mm")
//
//        val date1 = simpleDateFormat.parse(time1)
//        val date2 = simpleDateFormat.parse(getCurrentTime())
//
//        val difference: Long = date2.getTime() - date1.getTime()
//        var hours = ((difference -  60 * 60 * 24) / ( 60 * 60))
//        var min =  (difference - (60*60*24) - (60*60*hours)) / (60);
//
//        hours = if (hours < 0) -hours else hours
//
//        return min

        val simpleDateFormat = SimpleDateFormat("HH:mm")

        val date1 = simpleDateFormat.parse(time1)
        val date2 = simpleDateFormat.parse(getCurrentTime())

        val mills: Long = date1.getTime() - date2.getTime()
        val hours: Long = mills / (1000 * 60 * 60)
        val mins = (mills / (1000 * 60) % 60)


        return hours


    }

    fun getDifferenceTime(time1:String, time2:String): Long {


        val simpleDateFormat = SimpleDateFormat("HH:mm")

        val date1 = simpleDateFormat.parse(time1)
        val date2 = simpleDateFormat.parse(time2)

        val mills: Long = date1.getTime() - date2.getTime()
        val hours: Long = mills / (1000 * 60 * 60)
        val mins = (mills / (1000 * 60) % 60)


        return hours


    }


    fun getCurrentDate(): String? {
        val cal = Calendar.getInstance()
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        return sdf.format(cal.time)
    }

    fun getCurrentTime(): String? {
        val cal = Calendar.getInstance()
        val sdf = SimpleDateFormat("HH:mm")
        return sdf.format(cal.time)
    }





}