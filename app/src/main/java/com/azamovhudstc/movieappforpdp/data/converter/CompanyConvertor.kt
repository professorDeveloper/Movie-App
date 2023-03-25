package com.azamovhudstc.movieappforpdp.data.converter

import androidx.room.TypeConverter
import com.azamovhudstc.movieappforpdp.data.network.response.ProductionCompany
import com.google.gson.reflect.TypeToken

import com.google.gson.Gson
import java.lang.reflect.Type


class CompanyConverter {
    @TypeConverter
    fun fromCompanyList(list: List<ProductionCompany?>?): String? {
        if (list == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<ProductionCompany?>?>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun toCompanyList(companyString: String?): List<ProductionCompany>? {
        if (companyString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<ProductionCompany?>?>() {}.type
        return gson.fromJson<List<ProductionCompany>>(companyString, type)
    }
}