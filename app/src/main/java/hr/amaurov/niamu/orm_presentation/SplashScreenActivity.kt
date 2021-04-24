package hr.amaurov.niamu.orm_presentation

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import hr.amaurov.niamu.orm_presentation.dao.repo.CustomOpenHelper
import hr.amaurov.niamu.orm_presentation.dao.repo.DBRepo
import hr.amaurov.niamu.orm_presentation.models.ContactDao
import hr.amaurov.niamu.orm_presentation.models.DaoMaster
import hr.amaurov.niamu.orm_presentation.models.DaoSession
import hr.amaurov.niamu.orm_presentation.utils.startActivity
import org.greenrobot.greendao.database.Database

private const val DELAY = 3000L
private const val DB_NAME = "CONTACTS_DB"


class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        initDB()
        redirect()
    }

    private fun initDB() {
        val db = CustomOpenHelper(this, DB_NAME).writableDb
        DaoMaster.createAllTables(db, true)
        DBRepo.daoSession = DaoMaster(db).newSession()
    }

    private fun redirect() {
        Handler(Looper.getMainLooper()).postDelayed(
            { startActivity<HostActivity>() }, DELAY
        )
    }


}