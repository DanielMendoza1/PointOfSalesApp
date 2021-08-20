package daniel.ornelas.tianguisapp.util

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

    fun dateAString(date: Date): String{
        val dateFormat : DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        return dateFormat.format(date)
    }

