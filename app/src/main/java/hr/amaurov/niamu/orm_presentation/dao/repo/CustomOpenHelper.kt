package hr.amaurov.niamu.orm_presentation.dao.repo

import android.content.Context
import android.util.Log
import hr.amaurov.niamu.orm_presentation.models.CityDao
import hr.amaurov.niamu.orm_presentation.models.ContactDao
import hr.amaurov.niamu.orm_presentation.models.CountryDao
import hr.amaurov.niamu.orm_presentation.models.DaoMaster
import org.greenrobot.greendao.database.Database

class CustomOpenHelper(context: Context?, name: String?) :
    DaoMaster.DevOpenHelper(context, name) {

    override fun onCreate(db: Database) {
        super.onCreate(db)
        // Insert example data.
        db.execSQL(
            "INSERT INTO " + ContactDao.TABLENAME + " (" +
                    ContactDao.Properties.Id.columnName.toString() + ", " +
                    ContactDao.Properties.FirstName.columnName.toString() + ", " +
                    ContactDao.Properties.LastName.columnName.toString() + ", " +
                    ContactDao.Properties.DateOfBirth.columnName.toString() + ", " +
                    ContactDao.Properties.Email.columnName.toString() + ", " +
                    ContactDao.Properties.PhoneNumber.columnName.toString() + ", " +
                    ContactDao.Properties.CityId.columnName.toString() + ", " +
                    ContactDao.Properties.IsFavorite.columnName.toString() +
                    ") VALUES (1, 'John', 'Doe', 1, 'john.doe@mail.com', '+385951234567', 1, 1), " +
                    "(2, 'Jane', 'Doe', 0, 'jane.doe@mail.com', null, 1, 0)"
        )

        db.execSQL(
            "INSERT INTO " + CountryDao.TABLENAME + " (" +
                    CountryDao.Properties.Id.columnName.toString() + ", " +
                    CountryDao.Properties.Name.columnName.toString() +
                    ") VALUES (1, 'Croatia'), " +
                    "(2, 'United Kingdom')"
        )

        db.execSQL(
            "INSERT INTO " + CityDao.TABLENAME + " (" +
                    CityDao.Properties.Id.columnName.toString() + ", " +
                    CityDao.Properties.Name.columnName.toString() + ", " +
                    CityDao.Properties.CountryId.columnName.toString() +
                    ") VALUES (1, 'London', 2), " +
                    "(2, 'Zagreb', 1), " +
                    "(3, 'Pula', 1)"
        )
    }
}