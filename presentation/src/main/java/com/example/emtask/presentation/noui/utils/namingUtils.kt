package com.example.emtask.presentation.noui.utils

import java.time.LocalDate
import java.time.Month


internal fun generatePublishedDateByLocalDate(localDate: LocalDate): String =
    "Опубликовано ${localDate.dayOfMonth} ${
        when (localDate.month) {
            Month.JANUARY -> "января"
            Month.FEBRUARY -> "февраля"
            Month.MARCH -> "марта"
            Month.APRIL -> "апреля"
            Month.MAY -> "мая"
            Month.JUNE -> "июня"
            Month.JULY -> "июля"
            Month.AUGUST -> "августа"
            Month.SEPTEMBER -> "сентября"
            Month.OCTOBER -> "октября"
            Month.NOVEMBER -> "ноября"
            Month.DECEMBER -> "декабря"
            else -> ""
        }
    }"

internal fun <T> generateMoreCountOfVacanciesByCount(count: Int, addVacanciesWord: (String) -> T) =
    when {
        count in 11..19 -> addVacanciesWord("вакансий")
        count % 10 == 1 -> addVacanciesWord("вакансия")
        count % 10 in 2..4 -> addVacanciesWord("вакансии")
        else -> addVacanciesWord("вакансий")
    }

internal fun <T> generateCountOfPeopleByCount(count: Int, addPeopleWord: (String) -> T) =
    when {
        count % 10 in 2..4 -> addPeopleWord("человека")
        else -> addPeopleWord("человек")
    }