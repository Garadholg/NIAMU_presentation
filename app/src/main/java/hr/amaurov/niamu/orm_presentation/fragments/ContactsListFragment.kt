package hr.amaurov.niamu.orm_presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hr.amaurov.niamu.orm_presentation.HostActivity
import hr.amaurov.niamu.orm_presentation.R
import hr.amaurov.niamu.orm_presentation.interfaces.IORMService
import hr.amaurov.niamu.orm_presentation.models.Contact
import hr.amaurov.niamu.orm_presentation.models.ContactDao
import hr.amaurov.niamu.orm_presentation.utils.ContactsAdapter
import kotlinx.android.synthetic.main.fragment_contacts_list.*
import org.greenrobot.greendao.query.Query

class ContactsListFragment : Fragment() {
    private var contactDao: ContactDao? = null
    private var contactsQuery: Query<Contact>? = null
    private var contactsAdapter: ContactsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView
        rvContacts.layoutManager = LinearLayoutManager(this.requireContext())
        contactsAdapter = ContactsAdapter(contacts, this.requireContext()) { item ->
            (requireActivity() as HostActivity).navigateToContactDetail("${item.firstName} ${item.lastName}")
        };
        rvContacts.adapter = contactsAdapter

        // Buttons
        btnAdd.setOnClickListener(View.OnClickListener { view: View? ->
            if (clickListener != null) {
                clickListener.onNoteClick(getAdapterPosition())
            }
        })

        val test = contactsQuery?.list()
        Log.e("hello", "$test")
    }


    // CRUD functionality
    inner class ContactApi : IORMService {
        override fun createContact(contact: Contact) {
            contactDao?.insert(contact)
            updateContacts()
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

    private fun updateContacts() {
        val contacts = contactsQuery?.list()
        if (contacts != null) {
            contactsAdapter?.setContacts(contacts)
        }
    }

    private var contacts: MutableList<Contact> = ArrayList();
}