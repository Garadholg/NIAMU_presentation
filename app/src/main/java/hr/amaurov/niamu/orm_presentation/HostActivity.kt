package hr.amaurov.niamu.orm_presentation

import android.gesture.GestureUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hr.amaurov.niamu.orm_presentation.fragments.ContactDetailsFragment
import hr.amaurov.niamu.orm_presentation.models.Contact

private const val CONTACT_KEY = "PRODUCT"

class HostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
    }

    fun navigateToProductDetail(contact: String) {
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