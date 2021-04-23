package hr.amaurov.niamu.orm_presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hr.amaurov.niamu.orm_presentation.HostActivity
import hr.amaurov.niamu.orm_presentation.R
import hr.amaurov.niamu.orm_presentation.databinding.FragmentContactDetailsBinding
import hr.amaurov.niamu.orm_presentation.orm.room.entities.ContactRoom
import hr.amaurov.niamu.orm_presentation.orm.room.viewModels.ContactViewModel

private const val CONTACT_KEY = "CONTACT_ID"

class ContactDetailsFragment : Fragment() {
    private var contactViewModelF: ContactViewModel?= null;

    private var _binding: FragmentContactDetailsBinding? = null
    private val binding get() = _binding!!

    private var contactDetails: ContactRoom? =null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        contactViewModelF = (activity as HostActivity).contactViewModel
        _binding = FragmentContactDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arguments = arguments
        val contactId :Long = arguments!!.getLong(CONTACT_KEY)
        getDetailsAndFillViews(contactId)
    }

    private fun getDetailsAndFillViews(contactId:Long) {
        getDetails(contactId)
        fillUpViews()
    }

    private fun fillUpViews() {
        binding.etDetailsFullName.setText(contactDetails!!.firstName+" "+contactDetails!!.lastName)
        binding.etDateOfBirth.setText(contactDetails!!.dateOfBirth!!.toLocalDate().toString())
        binding.etEmail.setText(contactDetails!!.email)
        binding.etPhoneNumber.setText(contactDetails!!.phoneNumber)
    }

    private fun getDetails(contactId:Long) {
        contactDetails=contactViewModelF!!.getContactDetails(contactId)
    }

}