package hr.amaurov.niamu.orm_presentation.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.Visibility
import hr.amaurov.niamu.orm_presentation.HostActivity
import hr.amaurov.niamu.orm_presentation.application.ContactApplication
import hr.amaurov.niamu.orm_presentation.databinding.FragmentContactDetailsBinding
import hr.amaurov.niamu.orm_presentation.orm.room.entities.CityRoom
import hr.amaurov.niamu.orm_presentation.orm.room.entities.ContactRoom
import hr.amaurov.niamu.orm_presentation.orm.room.viewModels.ContactViewModel
import hr.amaurov.niamu.orm_presentation.utils.CityAdapter
import java.time.LocalDate

private const val CONTACT_KEY = "CONTACT_ID"

class ContactDetailsFragment : Fragment() {

    private var _binding: FragmentContactDetailsBinding? = null
    private val binding get() = _binding!!

    private var contactViewModelF: ContactViewModel?= null;
    private var contactDetails: ContactRoom? =null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        contactViewModelF = (activity as HostActivity).contactViewModel
        _binding = FragmentContactDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arguments = arguments
        val contactId :Long = arguments!!.getLong(CONTACT_KEY)
        init(contactId)
    }

    private fun init(contactId: Long) {
        fillSpinner()
        if(contactId==0L) {
            binding.btnDelete.visibility=View.GONE
            binding.btnSave.visibility=View.GONE
            binding.btnAdd.visibility=View.VISIBLE
        }
        else {
            getDetailsAndFillViews(contactId)
            binding.btnDelete.visibility=View.VISIBLE
            binding.btnSave.visibility=View.VISIBLE
            binding.btnAdd.visibility=View.GONE
        }
        setUpListeners()
    }

    private fun fillSpinner() {
        val cityDAO=(requireActivity().application as ContactApplication).database.cityDAO()
        var cities: List<CityRoom> = cityDAO.getAllCities()
        val adapter= context?.let { CityAdapter(it, cities) }
        binding.spCity.adapter=adapter
    }

    private fun setUpListeners() {
        binding.btnCancel.setOnClickListener{
            requireActivity().onBackPressed()
        }

        //Editing contact
        binding.btnSave.setOnClickListener{
            fromUiToObject()
            contactViewModelF!!.updateContact(contactDetails!!)
            requireActivity().onBackPressed()
        }

        //Deleting contact
        binding.btnDelete.setOnClickListener{
            contactViewModelF!!.deleteContact(contactDetails!!.contactID!!)
            requireActivity().onBackPressed()
        }

        //Creating contact
        binding.btnAdd.setOnClickListener{
            fromUiToObject()
            contactViewModelF!!.createContact(contactDetails!!)
            requireActivity().onBackPressed()
        }
    }

    private fun getDetailsAndFillViews(contactId: Long) {
        getDetails(contactId)
        fillUpViews()
    }

    private fun getDetails(contactId: Long) {
        //Get contact details
        contactDetails=contactViewModelF!!.getContactDetails(contactId)
    }

    private fun fillUpViews() {
        binding.etDetailsFirstName.setText(contactDetails!!.firstName)
        binding.etDetailsLastName.setText(contactDetails!!.lastName)
        binding.etDateOfBirth.setText(contactDetails!!.dateOfBirth!!.toLocalDate().toString())
        binding.etEmail.setText(contactDetails!!.email)
        binding.etPhoneNumber.setText(contactDetails!!.phoneNumber)
        if(contactDetails!!.isFavorite!!)
            binding.cbFavorite.isChecked=true
        for (i in 0 until binding.spCity.count) {
            val cityId: Long? = (binding.spCity.getItemAtPosition(i) as CityRoom).cityID
            if (cityId!!.equals(contactDetails!!.cityId)) {
                binding.spCity.setSelection(i)
            }
        }
    }

    private fun fromUiToObject() {
        if(contactDetails==null)
            contactDetails= ContactRoom()
        contactDetails!!.firstName=binding.etDetailsFirstName.text.toString()
        contactDetails!!.lastName=binding.etDetailsLastName.text.toString()
        contactDetails!!.dateOfBirth= (LocalDate.parse(binding.etDateOfBirth.text.toString())).atStartOfDay()
        contactDetails!!.email=binding.etEmail.text.toString()
        contactDetails!!.phoneNumber=binding.etPhoneNumber.text.toString()
        contactDetails!!.isFavorite=binding.cbFavorite.isChecked
        contactDetails!!.cityId=(binding.spCity.selectedItem as CityRoom).cityID
    }



}