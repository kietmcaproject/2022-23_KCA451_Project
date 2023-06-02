package com.app.pepens.Room

import androidx.room.*
import com.app.pepens.Room.Model.SearchData

@Database(entities = [SearchData::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class SearchDatabase : RoomDatabase() {

    abstract fun searchInterface(): SearchInterface

}