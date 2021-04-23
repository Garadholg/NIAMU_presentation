package hr.amaurov.niamu.orm_presentation.orm.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="countries")
data class CountryRoom (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id: Long? = null,
    @ColumnInfo(name="name")
    var name: String? = null
)