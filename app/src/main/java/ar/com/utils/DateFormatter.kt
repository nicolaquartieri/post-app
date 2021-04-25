package ar.com.utils

import java.text.SimpleDateFormat
import java.util.*

class DateFormatter {

    private val ISOsdf by lazy { SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()) }

    fun formatFrom(date: String): Date = ISOsdf.parse(date)

    fun formatFrom(date: Date): String = ISOsdf.format(date)
}