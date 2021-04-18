package hr.amaurov.niamu.orm_presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import hr.amaurov.niamu.orm_presentation.R
import hr.amaurov.niamu.orm_presentation.models.Contact
import hr.amaurov.niamu.orm_presentation.utils.ContactsAdapter
import kotlinx.android.synthetic.main.fragment_contacts_list.*

class ContactsListFragment : Fragment() {

    private val contacts: ArrayList<Contact> = ArrayList();

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addContacts();
        rvContacts.layoutManager = LinearLayoutManager(this.requireContext())
        rvContacts.adapter = ContactsAdapter(contacts, this.requireContext())
    }

    private fun addContacts() {
        contacts.add(Contact(firstName = "Anton", lastName = "Maurovic"));
        contacts.add(Contact(firstName = "Luis Daniel", lastName = "Vasquez Pena"));
        contacts.add(Contact(firstName = "Andro", lastName = "Zonja"));
        contacts.add(Contact(firstName = "John", lastName = "Doe"));
        contacts.add(Contact(firstName = "Jane", lastName = "Doe"));
        contacts.add(Contact(firstName = "Zdravko", lastName = "Mamic"));
        contacts.add(Contact(firstName = "Severina", lastName = "Kojic"));
    }
}