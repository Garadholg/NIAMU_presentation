package hr.amaurov.niamu.orm_presentation.orm.room.database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import hr.amaurov.niamu.orm_presentation.orm.room.DAOs.CityDAO
import hr.amaurov.niamu.orm_presentation.orm.room.DAOs.ContactDAO
import hr.amaurov.niamu.orm_presentation.orm.room.DAOs.CountryDAO
import hr.amaurov.niamu.orm_presentation.orm.room.entities.CityRoom
import hr.amaurov.niamu.orm_presentation.orm.room.entities.ContactRoom
import hr.amaurov.niamu.orm_presentation.orm.room.entities.CountryRoom
import kotlinx.coroutines.launch
import java.time.chrono.HijrahChronology

@Database(entities = [ContactRoom ::class, CityRoom::class, CountryRoom::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
public abstract class ApplicationRoomDatabase : RoomDatabase() {

    abstract fun contactDAO(): ContactDAO
    abstract fun countryDAO(): CountryDAO
    abstract fun cityDAO(): CityDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ApplicationRoomDatabase? = null

        fun getDatabase(context: Context): ApplicationRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val converter=Converters()
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ApplicationRoomDatabase::class.java,
                    "contactApp_database"
                )
                    .addTypeConverter(converter)
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}