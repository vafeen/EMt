package com.example.emtask.data.database.type_converters

import androidx.room.TypeConverter


internal object StrListConverters {
    @TypeConverter
    fun toString(value: List<String>?): String? {
        return value?.joinToString { it }
    }

    @TypeConverter
    fun dateToTimestamp(value: String?): List<String>? {
        return value?.split(", ")
    }
}