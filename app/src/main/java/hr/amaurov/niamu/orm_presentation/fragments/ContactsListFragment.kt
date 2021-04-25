package hr.amaurov.niamu.orm_presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import hr.amaurov.niamu.orm_presentation.HostActivity
import hr.amaurov.niamu.orm_presentation.R
import hr.amaurov.niamu.orm_presentation.SplashScreenActivity
import hr.amaurov.niamu.orm_presentation.application.ContactApplication
import hr.amaurov.niamu.orm_presentation.databinding.FragmentContactDetailsBinding
import hr.amaurov.niamu.orm_presentation.databinding.FragmentContactsListBinding
import hr.amaurov.niamu.orm_presentation.orm.room.entities.ContactRoom
import hr.amaurov.niamu.orm_presentation.orm.room.viewModels.ContactViewModel
import hr.amaurov.niamu.orm_presentation.orm.room.viewModels.ContactsViewModelFactory
import hr.amaurov.niamu.orm_presentation.utils.ContactsAdapter
import hr.amaurov.niamu.orm_presentation.utils.showToast
import java.lang.Exception

class ContactsListFragment : Fragment() {

    private var contactViewModelF:ContactViewModel?= null;

    private var _binding: FragmentContactsListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentContactsListBinding.inflate(inflater, container, false)
        contactViewModelF = (activity as HostActivity).contactViewModel
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setUpRecycleView()
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.btnAddContact.setOnClickListener{item ->(requireActivity() as HostActivity).navigateToProductDetail(0)}
    }

    private fun setUpRecycleView() {
        //Get all contacts
        try {
            contactViewModelF!!.allContacts.observe(this.viewLifecycleOwner, Observer { contacts ->
                contacts.let {
                    binding.rvContacts.layoutManager = LinearLayoutManager(this.requireContext())
                    binding.rvContacts.adapter = ContactsAdapter(
                            contacts,
                            this.requireContext()
                    ) { item ->
                        (requireActivity() as HostActivity).navigateToProductDetail(item.contactID!!)
                    };
                }
            })
        } catch (e: Exception) {
            requireActivity().showToast("Error getting contacts: "+e.message, Toast.LENGTH_LONG)
        }
    }
}