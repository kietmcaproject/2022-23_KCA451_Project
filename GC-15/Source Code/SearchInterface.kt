package com.app.pepens.Room

import androidx.room.*
import com.app.pepens.Room.Model.SearchData

@Dao
interface SearchInterface {

    @Query("SELECT * FROM Search")
    fun getAll(): List<SearchData>

    @Query("SELECT * FROM Search WHERE businessId LIKE:businessId")
    fun findById(businessId: Int): SearchData

    @Query("SELECT * FROM Search WHERE categoryId LIKE:categoryId")
    fun findByCategoryId(categoryId: Int): SearchData

    @Insert
    fun insert(vararg searchData: SearchData)

    @Query("DELETE FROM Search")
    fun deleteAll()

    @Query("DELETE FROM Search WHERE businessId=:businessId")
    fun deleteItem(businessId: Int)

    @Query("DELETE FROM Search WHERE categoryId=:categoryId")
    fun deleteItemByCategoryId(categoryId: Int)

    @Delete
    fun delete(searchData: SearchData)

    @Update()
    fun update(vararg searchData: SearchData)

}