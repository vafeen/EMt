package ru.vafeen.local_storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VacancyIDEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val vacancyID: String,
)