package hr.amaurov.niamu.orm_presentation

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import hr.amaurov.niamu.orm_presentation.models.ContactDao
import hr.amaurov.niamu.orm_presentation.models.DaoMaster
import hr.amaurov.niamu.orm_presentation.models.DaoSession
import hr.amaurov.niamu.orm_presentation.utils.startActivity
import org.greenrobot.greendao.database.Database

private const val DELAY = 3000L
private const val DB_NAME = "contacts-db"

class SplashScreenActivity : AppCompatActivity() {
    companion object {
        var daoSession: DaoSession? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // For production, use a OpenHelper subclass instead.
        val db = CustomOpenHelper(this, DB_NAME).writableDb

        // Create tables
        DaoMaster.createAllTables(db, true)
        daoSession = DaoMaster(db).newSession()

        redirect()
    }

    private fun redirect() {
        Handler(Looper.getMainLooper()).postDelayed(
            { startActivity<HostActivity>() }, DELAY
        )
    }

    class CustomOpenHelper(context: Context?, name: String?) :
        DaoMaster.DevOpenHelper(context, name) {

        override fun onCreate(db: Database) {
            super.onCreate(db)
            Log.e("test", "***Worked***");
            // Insert example data.
            db.execSQL(
                "INSERT INTO " + ContactDao.TABLENAME.toString() + " (" +
                        ContactDao.Properties.Id.columnName.toString() + ", " +
                        ContactDao.Properties.FirstName.columnName.toString() + ", " +
                        ContactDao.Properties.LastName.columnName.toString() + ", " +
                        ContactDao.Properties.DateOfBirth.columnName.toString() + ", " +
                        ContactDao.Properties.Email.columnName.toString() + ", " +
                        ContactDao.Properties.PhoneNumber.columnName.toString() + ", " +
                        ContactDao.Properties.CityId.columnName.toString() + ", " +
                        ContactDao.Properties.IsFavorite.columnName.toString() +
                        ") VALUES(1, 'John', 'Doe', null, 'john.doe@mail.com', '+385951234567', 1, true)"
            )
        }
    }
}