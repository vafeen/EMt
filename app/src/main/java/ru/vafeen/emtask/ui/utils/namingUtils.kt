package ru.vafeen.emtask.ui.utils

import java.time.LocalDate

fun generatePeopleViewStringByCount(count: Int): String = "Сейчас просматривает $count человек"

fun generatePublishedDateByLocalDate(localDate: LocalDate): String =
    "Опубликовано ${localDate.dayOfWeek}.${localDate.month}"