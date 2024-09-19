package ru.vafeen.emtask.ui.utils

import java.time.LocalDate
import java.time.Month

fun generatePeopleViewStringByCount(count: Int): String = "Сейчас просматривает $count человек"

fun generatePublishedDateByLocalDate(localDate: LocalDate): String =
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

fun generateMoreCountOfVacanciesByCount(count: Int): String = "Еще $count ${
    when {
        count in 11..19 -> "вакансий"
        count % 10 == 1 -> "вакансия"
        count % 10 in 2..4 -> "вакансии"
        else -> "вакансий"
    }
}"