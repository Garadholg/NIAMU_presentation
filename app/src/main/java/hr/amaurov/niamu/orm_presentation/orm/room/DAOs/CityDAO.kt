package hr.amaurov.niamu.orm_presentation.orm.room.DAOs

import androidx.annotation.WorkerThread
import androidx.room.*
import hr.amaurov.niamu.orm_presentation.orm.room.entities.CityRoom
import hr.amaurov.niamu.orm_presentation.orm.room.entities.ContactRoom
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDAO {
    @Query("SELECT * FROM cities")
    fun getAllCities(): List<CityRoom>

    @Query("SELECT * FROM cities WHERE id=:id")
    fun getCityById(id: Long): CityRoom

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun createCity(city: CityRoom) : Long

    @Query("DELETE FROM cities")
    fun deleteAllCities()
}