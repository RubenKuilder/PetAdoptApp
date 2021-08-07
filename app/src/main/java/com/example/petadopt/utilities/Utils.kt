package com.example.petadopt.utilities

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class Utils {
    fun dateMonthDiff(date: String): Int {
        var formatter = DateTimeFormatter
            .ofPattern("d MMMM yyyy")
            .withLocale(Locale.forLanguageTag("nl-NL"))
        val dateOfBirth = LocalDate.parse(date, formatter)
        val today = LocalDate.now()
        var ageInYears = today.year - dateOfBirth.year
        var ageInMonths = ageInYears * 12 + (today.monthValue - dateOfBirth.monthValue)

        if(today.dayOfMonth >= dateOfBirth.dayOfMonth) {
            ageInMonths--
        }

        return ageInMonths
    }
}