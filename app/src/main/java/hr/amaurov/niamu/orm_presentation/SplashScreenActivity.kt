package hr.amaurov.niamu.orm_presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import hr.amaurov.niamu.orm_presentation.application.ContactApplication
import hr.amaurov.niamu.orm_presentation.orm.room.entities.CityRoom
import hr.amaurov.niamu.orm_presentation.orm.room.entities.ContactRoom
import hr.amaurov.niamu.orm_presentation.orm.room.entities.CountryRoom
import hr.amaurov.niamu.orm_presentation.orm.room.viewModels.ContactViewModel
import hr.amaurov.niamu.orm_presentation.orm.room.viewModels.ContactsViewModelFactory
import hr.amaurov.niamu.orm_presentation.utils.startActivity
import java.time.LocalDateTime


class SplashScreenActivity : AppCompatActivity() {
    private var contactsViewModel:ContactViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        initViewModel()
        populateDb()
        redirect()
    }

    private fun initViewModel() {
         contactsViewModel = ViewModelProvider(application as ContactApplication,
            ContactsViewModelFactory((application as ContactApplication).contactRepository))
            .get(ContactViewModel::class.java)
    }

    private fun populateDb() {
        val cityDAO=(application as ContactApplication).database.cityDAO()
        val countryDAO=(application as ContactApplication).database.countryDAO()
        //Countries
        countryDAO.deleteAllCountries()
        val croatiaId: Long =countryDAO.createCountry(CountryRoom(name = "Croatia"))
        //Cities
        cityDAO.deleteAllCities()
        val zagrebId:Long=cityDAO.createCity(CityRoom(name="Zagreb", countryId = croatiaId))
        val bjelovarId:Long=cityDAO.createCity(CityRoom(name="Bjelovar", countryId = croatiaId))
        //Contacts
        contactsViewModel!!.deleteAllContacts()
        contactsViewModel!!.createContact(ContactRoom(firstName = "Anton", lastName = "Maurovic",dateOfBirth = LocalDateTime.of(1997,1,1,0,0,0),email = "amaurovic@mail.com",phoneNumber = "0991234567",isFavorite = true))
        contactsViewModel!!.createContact(ContactRoom(firstName = "Luis Daniel", lastName = "Vasquez Pena",dateOfBirth = LocalDateTime.of(1998,2,2,0,0,0),email = "lpena@mail.com",phoneNumber = "099765432",isFavorite = true))
        contactsViewModel!!.createContact(ContactRoom(firstName = "Andro", lastName = "Zonja",dateOfBirth = LocalDateTime.of(1999,3,3,0,0,0),email = "azonja@mail.com",phoneNumber = "0991234567",isFavorite = true, cityId = zagrebId))
        contactsViewModel!!.createContact(ContactRoom(firstName = "John", lastName = "Doe",dateOfBirth = LocalDateTime.of(1990,12,21,0,0,0),email = "anonymous@mail.com",phoneNumber = "09922244455",isFavorite = false))
        contactsViewModel!!.createContact(ContactRoom(firstName = "Jane", lastName = "Doe",dateOfBirth = LocalDateTime.of(1990,12,21,0,0,0),email = "anonymous@mail.com",phoneNumber = "09922244455",isFavorite = false))
        contactsViewModel!!.createContact(ContactRoom(firstName = "Zdravko", lastName = "Mamic",dateOfBirth = LocalDateTime.of(1990,12,21,0,0,0),email = "anonymous@mail.com",phoneNumber = "09922244455",isFavorite = false,cityId = bjelovarId))
    }


    private fun redirect() {
        Handler(Looper.getMainLooper()).post({ startActivity<HostActivity>() })
    }
}