package com.example.emtask.presentation.noui.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter


fun parseDateFromString(date: String): LocalDate =
    LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
