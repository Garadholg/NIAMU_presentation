package hr.amaurov.niamu.orm_presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hr.amaurov.niamu.orm_presentation.fragments.ContactDetailsFragment


private const val CONTACT_KEY = "CONTACT"

class HostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // navigateToContactDetail("${contact.firstName} ${contact.lastName}")
        setContentView(R.layout.activity_host)
    }

    /**
     * Load the details view for the given contact's full name: "${firstName} ${lastName}".
     */
    fun navigateToContactDetail(contact: String) {
        val fragment = ContactDetailsFragment()
        val args = Bundle().apply {
            putString(CONTACT_KEY, contact)
        }
        fragment.arguments = args

        supportFragmentManager.beginTransaction()
            .addToBackStack("ABC")
            .replace(R.id.navHostFragment, fragment, "CDE")
            .commit()
    }
}
