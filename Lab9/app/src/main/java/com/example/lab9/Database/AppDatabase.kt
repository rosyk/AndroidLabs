package com.example.lab9.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lab9.Entities.Place

@Database(entities = [Place::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun placeDao(): PlaceDao
}