package com.azamovhudstc.movieappforpdp.data.converter

import androidx.room.TypeConverter
import com.azamovhudstc.movieappforpdp.data.network.response.Genre
import com.google.gson.reflect.TypeToken

import com.google.gson.Gson
import java.lang.reflect.Type


class GenreConverter {
    @TypeConverter
    fun fromGenreList(list: List<Genre?>?): String? {
        if (list == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Genre?>?>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun toGenreList(genreString: String?): List<Genre>? {
        if (genreString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Genre?>?>() {}.type
        return gson.fromJson<List<Genre>>(genreString, type)
    }
}