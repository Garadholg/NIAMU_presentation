package hr.amaurov.niamu.orm_presentation.orm.room.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.sql.Date
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

@ProvidedTypeConverter
class Converters {
    @TypeConverter
    fun toDatabaseColumn(dateOfBirth: LocalDateTime?): Long? {
        return dateOfBirth?.let { dateOfBirth.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() }
    }

    @TypeConverter
    fun fromDatabaseColumn(dateOfBirth: Long?): LocalDateTime? {
        return dateOfBirth?.let { Instant.ofEpochMilli(dateOfBirth).atZone(ZoneId.systemDefault()).toLocalDateTime() }
    }
}