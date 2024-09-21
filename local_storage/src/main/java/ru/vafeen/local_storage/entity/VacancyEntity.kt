package ru.vafeen.local_storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VacancyEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val id: String,
    val lookingNumber: Int? = null,
    val title: String,
    val addressTown: String,
    val addressStreet: String,
    val addressHouse: String,
    val company: String,
    val experiencePreviewText: String,
    val experienceText: String,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salaryFull: String,
    val salaryShort: String? = null,
    val schedules: List<String>,
    val appliedNumber: Int? = null,
    val description: String? = null,
    val responsibilities: String,
    val questions: List<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is VacancyEntity) return false
        return isFavorite == other.isFavorite
    }
}