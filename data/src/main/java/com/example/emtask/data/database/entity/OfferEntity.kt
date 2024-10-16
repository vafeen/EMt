package com.example.emtask.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OfferEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val id: String? = null,
    val title: String,
    val link: String,
    val buttonText: String? = null
)