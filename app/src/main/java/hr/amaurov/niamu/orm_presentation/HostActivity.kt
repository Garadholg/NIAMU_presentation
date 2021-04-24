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

    fun navigateToContactDetails(contactId: Long?) {
        val fragment = ContactDetailsFragment()
        val args = Bundle().apply {
            if (contactId != null) {
                putLong(CONTACT_KEY, contactId)
            }
        }
        fragment.arguments = args

        supportFragmentManager.beginTransaction()
            .addToBackStack("ABC")
            .replace(R.id.navHostFragment, fragment, "CDE")
            .commit()
    }
}
