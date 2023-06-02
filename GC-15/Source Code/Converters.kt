package com.app.pepens.Room

import androidx.room.TypeConverter
import com.app.pepens.Room.Model.SearchItems
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun listToJson(value: List<SearchItems>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<SearchItems>::class.java).toList()

}