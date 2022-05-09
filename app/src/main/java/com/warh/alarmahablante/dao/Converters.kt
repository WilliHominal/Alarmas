package com.warh.alarmahablante.dao

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun getIntListFromString(string: String): List<Int> {
        val list = mutableListOf<Int>()
        string.split(" ").forEach { if (it != "") list.add(it.toInt()) }
        return list
    }

    @TypeConverter
    fun getStringFromIntList(list: List<Int>): String {
        var ret = ""
        list.forEach { ret += "$it " }
        return ret
    }

    @TypeConverter
    fun getDateFromString(string: String): Date {
        return Date(string.toLong())
    }

    @TypeConverter
    fun getStringFromDate(date: Date): String {
        return date.time.toString()
    }
}