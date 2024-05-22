package com.example.lab9.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.lab9.Entities.Place

@Dao
interface PlaceDao {
    @Query ("SELECT * FROM memorable_places")
    fun getAll(): List<Place>

    @Insert
    fun insert(place: Place)

    @Delete
    fun delete(place: Place)
}