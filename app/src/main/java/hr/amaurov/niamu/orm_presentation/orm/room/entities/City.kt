package hr.amaurov.niamu.orm_presentation.orm.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "cities", foreignKeys = arrayOf(ForeignKey(entity = CountryRoom::class,
parentColumns = arrayOf("countryID"),
childColumns = arrayOf("countryId"),
onDelete = ForeignKey.CASCADE)))
data class CityRoom (
        @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="cityID")
    var cityID: Long? = null,
        @ColumnInfo(name="name")
    var name: String? = null,
        @ColumnInfo(name="countryId")
    var countryId: Long? = null
)