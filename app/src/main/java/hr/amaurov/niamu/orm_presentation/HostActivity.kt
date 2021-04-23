package hr.amaurov.niamu.orm_presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import hr.amaurov.niamu.orm_presentation.fragments.ContactDetailsFragment
import hr.amaurov.niamu.orm_presentation.interfaces.IORMService
import hr.amaurov.niamu.orm_presentation.models.Contact
import hr.amaurov.niamu.orm_presentation.models.ContactDao
import hr.amaurov.niamu.orm_presentation.utils.ContactsAdapter
import org.greenrobot.greendao.query.Query


private const val CONTACT_KEY = "PRODUCT"

class HostActivity : AppCompatActivity() {
    private var contactDao: ContactDao? = null
    private var contactsQuery: Query<Contact>? = null
    private var contactsAdapter: ContactsAdapter? = null

    // CRUD functionality
    inner class ContactApi : IORMService {
        override fun createContact(contact: Contact) {
            contactDao?.insert(contact)
            //if (contactDao != null) {
            //  Log.d("created", "The contact $contact was created")
            //}
        }

        override fun getAllContacts(): List<Contact>? {
            return contactsQuery?.list()
        }

        override fun getContactDetails(id: Long): Contact? {
            val contact =
                contactDao!!.queryBuilder().where(ContactDao.Properties.Id.eq(id)).build().unique()
            navigateToProductDetail("${contact.firstName} ${contact.lastName}")
            Log.d("query", "Contact (id: $id) details viewed")
            return contact
        }

        override fun updateContact(contact: Contact) {
            contactDao?.update(contact)
            updateContacts()
            //if (contactDao != null) {
            //  Log.d("query", "Contact $contact updated")
            //}
        }

        override fun deleteContact(id: Long) {
            val contact =
                contactDao!!.queryBuilder().where(ContactDao.Properties.Id.eq(id)).build().unique()
            contactDao?.delete(contact)
            updateContacts()
            //if (contactDao != null) {
            //  Log.d("query", "Contact (id: $id) was deleted")
            //}
        }
    }

    inner class HostContactClickListener : ContactsAdapter.ContactClickListener {
        override fun onContactClick(position: Int) {
            contactsAdapter?.dataset?.get(position)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contactDao = SplashScreenActivity.daoSession?.contactDao
        // Query all contacts, sorted alphabetically by first name
        contactsQuery =
            contactDao?.queryBuilder()?.orderAsc(ContactDao.Properties.FirstName)
                ?.build()
        contactsAdapter =
            ContactsAdapter(mutableListOf<Contact>(), this, HostContactClickListener())

        updateContacts()

        setContentView(R.layout.activity_host)
    }

    private fun updateContacts() {
        val contacts = contactsQuery?.list()
        if (contacts != null) {
            contactsAdapter?.setContacts(contacts)
        }
    }

    /**
     * Load the details view for the given contact's full name "${firstName} ${lastName}".
     */
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
