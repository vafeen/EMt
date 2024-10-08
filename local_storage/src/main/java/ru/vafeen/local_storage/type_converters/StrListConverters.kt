package ru.vafeen.local_storage.type_converters

import androidx.room.TypeConverter


object StrListConverters {
    @TypeConverter
    fun toString(value: List<String>?): String? {
        return value?.joinToString { it }
    }

    @TypeConverter
    fun dateToTimestamp(value: String?): List<String>? {
        return value?.split(", ")
    }
}