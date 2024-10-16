package com.example.emtask.data.database.type_converters

import com.example.emtask.data.database.entity.VacancyEntity
import com.example.emtask.data.network.response.Address
import com.example.emtask.data.network.response.Experience
import com.example.emtask.data.network.response.Salary
import com.example.emtask.data.network.response.Vacancy

object VacancyToVacancyEntityConverter {

    fun toVacancyEntity(vacancy: Vacancy): VacancyEntity = VacancyEntity(
        id = vacancy.id,
        lookingNumber = vacancy.lookingNumber,
        title = vacancy.title,
        addressTown = vacancy.address.town,
        addressStreet = vacancy.address.street,
        addressHouse = vacancy.address.house,
        company = vacancy.company,
        experiencePreviewText = vacancy.experience.previewText,
        experienceText = vacancy.experience.text,
        publishedDate = vacancy.publishedDate,
        isFavorite = vacancy.isFavorite,
        salaryFull = vacancy.salary.full,
        salaryShort = vacancy.salary.short,
        schedules = vacancy.schedules,
        appliedNumber = vacancy.appliedNumber,
        description = vacancy.description,
        responsibilities = vacancy.responsibilities,
        questions = vacancy.questions
    )

    fun toVacancy(vacancyEntity: VacancyEntity) =
        Vacancy(
            id = vacancyEntity.id,
            lookingNumber = vacancyEntity.lookingNumber,
            title = vacancyEntity.title,
            address = Address(
                town = vacancyEntity.addressTown, street = vacancyEntity.addressStreet,
                house = vacancyEntity.addressHouse
            ),
            company = vacancyEntity.company,
            experience = Experience(
                previewText = vacancyEntity.experiencePreviewText,
                text = vacancyEntity.experienceText
            ),
            publishedDate = vacancyEntity.publishedDate,
            isFavorite = vacancyEntity.isFavorite,
            salary = Salary(
                full = vacancyEntity.salaryFull,
                short = vacancyEntity.salaryShort
            ),
            schedules = vacancyEntity.schedules,
            appliedNumber = vacancyEntity.appliedNumber,
            description = vacancyEntity.description,
            responsibilities = vacancyEntity.responsibilities,
            questions = vacancyEntity.questions
        )
}