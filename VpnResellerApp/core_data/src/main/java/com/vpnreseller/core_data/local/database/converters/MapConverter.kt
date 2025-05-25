package com.vpnreseller.core_data.local.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Type converter for Map<String, String> to JSON String and vice-versa.
 * Used by Room to store Map objects in the database.
 */
class MapConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromStringMap(value: Map<String, String>?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toStringMap(value: String?): Map<String, String>? {
        return value?.let {
            val mapType = object : TypeToken<Map<String, String>>() {}.type
            gson.fromJson(it, mapType)
        }
    }
}
