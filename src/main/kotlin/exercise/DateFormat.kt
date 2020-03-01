package ru.yole.jkid

import ru.yole.jkid.deserialization.JKidException
import java.text.SimpleDateFormat
import java.util.*

@Target(AnnotationTarget.PROPERTY)
annotation class DateFormat(val format: String)

class DateFormatSerializer(private val format: String): ValueSerializer<Date> {
    override fun toJsonValue(value: Date): Any? =
            SimpleDateFormat(format).format(value)
    override fun fromJsonValue(jsonValue: Any?): Date =
            (jsonValue as? String)?.let {
                SimpleDateFormat(format).parse(it)
            } ?: throw JKidException("Unexpected value $jsonValue")
}

