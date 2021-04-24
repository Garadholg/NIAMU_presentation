package hr.amaurov.niamu.orm_presentation.orm.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(tableName = "contacts",foreignKeys = arrayOf(
    ForeignKey(entity = CityRoom::class,
    parentColumns = arrayOf("cityID"),
    childColumns = arrayOf("cityId"),
    onDelete = ForeignKey.CASCADE)
))
data class ContactRoom (
        @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="contactID")
    var contactID: Long? = null,
        @ColumnInfo(name = "firstName")
    var firstName: String? = null,
        @ColumnInfo(name = "lastName")
    var lastName: String? = null,
        @ColumnInfo(name = "dateOfBirth")
    var dateOfBirth: LocalDateTime? = null,
        @ColumnInfo(name = "email")
    var email: String? = null,
        @ColumnInfo(name = "phoneNumber")
    var phoneNumber: String? = null,
        @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean? = false,
        @ColumnInfo(name = "cityId")
    var cityId: Long? = null
)