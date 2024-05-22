package com.example.lab9.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memorable_places")
data class Place(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val latitude: Float,
    val longitude: Float
)
