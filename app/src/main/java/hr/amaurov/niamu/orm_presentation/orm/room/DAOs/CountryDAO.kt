package hr.amaurov.niamu.orm_presentation.orm.room.DAOs

import androidx.annotation.WorkerThread
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hr.amaurov.niamu.orm_presentation.orm.room.entities.CountryRoom

@Dao
interface CountryDAO {
    @Query("SELECT * FROM countries WHERE countryID=:id")
    fun getCityById(id: Long): CountryRoom

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun createCountry(country: CountryRoom):Long

    @Query("DELETE FROM countries")
    fun deleteAllCountries()
}