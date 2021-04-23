package hr.amaurov.niamu.orm_presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import hr.amaurov.niamu.orm_presentation.application.ContactApplication
import hr.amaurov.niamu.orm_presentation.fragments.ContactDetailsFragment
import hr.amaurov.niamu.orm_presentation.orm.room.viewModels.ContactViewModel
import hr.amaurov.niamu.orm_presentation.orm.room.viewModels.ContactsViewModelFactory

 private const val CONTACT_KEY = "CONTACT_ID"

class HostActivity : AppCompatActivity() {
    var contactViewModel:ContactViewModel?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contactViewModel=ViewModelProvider(application as ContactApplication,
            ContactsViewModelFactory((application as ContactApplication).contactRepository)
        ).get(ContactViewModel::class.java)
        setContentView(R.layout.activity_host)
    }

    fun navigateToProductDetail(contactId: Long ) {
        val fragment = ContactDetailsFragment()
        val args = Bundle().apply {
            putLong(CONTACT_KEY, contactId)
        }
        fragment.arguments = args

        supportFragmentManager.beginTransaction()
                .addToBackStack("ABC")
                .replace(R.id.navHostFragment, fragment, "CDE")
                .commit()
    }
}