package ru.vafeen.local_storage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VacancyID(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val vacancyID: String,
)