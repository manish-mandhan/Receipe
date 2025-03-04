package com.manish.mandhan.search.data.mappers

import androidx.room.TypeConverter
import com.google.gson.Gson

class AnyTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromAny(value: Any?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toAny(value: String?): Any? {
        return gson.fromJson(value, Any::class.java)
    }
}