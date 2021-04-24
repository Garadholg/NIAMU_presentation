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
import hr.amaurov.niamu.orm_presentation.dao.implementations.ORMService
import hr.amaurov.niamu.orm_presentation.dao.interfaces.IORMService
import hr.amaurov.niamu.orm_presentation.dao.repo.CustomOpenHelper
import hr.amaurov.niamu.orm_presentation.models.Contact
import hr.amaurov.niamu.orm_presentation.models.ContactDao
import hr.amaurov.niamu.orm_presentation.models.DaoMaster
import hr.amaurov.niamu.orm_presentation.models.DaoSession
import hr.amaurov.niamu.orm_presentation.utils.ContactsAdapter
import kotlinx.android.synthetic.main.fragment_contacts_list.*
import org.greenrobot.greendao.query.Query

class ContactsListFragment : Fragment() {

    private var contacts: List<Contact>? = ArrayList();

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contacts = ORMService.getAllContacts()

        // RecyclerView
        rvContacts.layoutManager = LinearLayoutManager(this.requireContext())
        rvContacts.adapter  = ContactsAdapter(contacts, this.requireContext()) { item ->
            (requireActivity() as HostActivity).navigateToContactDetails(item.id)
        };

        btnAdd.setOnClickListener {
            (requireActivity() as HostActivity).navigateToContactDetails(null)
        }
    }
}