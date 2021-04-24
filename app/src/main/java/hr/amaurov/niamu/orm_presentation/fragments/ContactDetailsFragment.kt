package hr.amaurov.niamu.orm_presentation.fragments

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import hr.amaurov.niamu.orm_presentation.R
import hr.amaurov.niamu.orm_presentation.dao.implementations.ORMService
import hr.amaurov.niamu.orm_presentation.models.Contact
import hr.amaurov.niamu.orm_presentation.utils.SpinnerCity
import kotlinx.android.synthetic.main.fragment_contact_details.*
import java.lang.RuntimeException
import java.time.DateTimeException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private const val CONTACT_KEY = "CONTACT"

class ContactDetailsFragment : Fragment() {

    private var contact: Contact? = null
    private var isStarFull: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fillUpForm()
        setListeners()
    }

    private fun fillUpForm() {
        val arguments = arguments
        val contactId = arguments?.getLong(CONTACT_KEY)

        if (contactId != 0L) {
            contact = contactId?.let { ORMService.getContactDetails(it) };
            try {
                val dateOfBirth: String =
                    contact?.dateOfBirth?.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"))
                        .toString()
                etDetailsDateOfBirth.setText(dateOfBirth)
            } catch (e: Exception) {
            }
            etDetailsFirstName.setText(contact?.firstName)
            etDetailsLastName.setText(contact?.lastName)
            etDetailsEmail.setText(contact?.email)
            etDetailsPhoneNumber.setText(contact?.phoneNumber)

            if (contact?.isFavorite!!) {
                ivStar.setImageResource(R.drawable.ic_favorite_full)
                isStarFull = true
            }

            btnSave.text = resources.getText(R.string.contactdetails_button_savechanges)
        } else {
            btnSave.text = resources.getText(R.string.contactdetails_button_addnewcontact)
            btnDelete.visibility = View.INVISIBLE;
        }

        fillCitySpinner()
    }

    private fun fillCitySpinner() {
        val cities = ORMService.getAllCities();
        var spinnerItems = ArrayList<SpinnerCity>()

        cities?.forEach {
            spinnerItems.add(SpinnerCity(it.id, it.name, it.country.name))
        }

        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            spinnerItems
        )
        spCity.adapter = spinnerAdapter

        if (contact != null) {
            var city = contact?.city
            var index = cities?.indexOf(city)!!
            spCity.setSelection(index)
        }
    }

    private fun setListeners() {
        btnCancel.setOnClickListener { activity?.onBackPressed() }
        btnSave.setOnClickListener { addUpdateContact() }
        btnDelete.setOnClickListener {
            if (contact != null) {
                ORMService.deleteContact(contact?.id!!)
            }
            activity?.onBackPressed()
        }

        ivStar.setOnClickListener {
            ivStar.setImageResource(if (isStarFull) R.drawable.ic_favorite_full else R.drawable.ic_favorite_empty)
        }
    }

    private fun addUpdateContact() {
        if (contact == null) {
            contact = Contact()
            if (fillContactInfo()) {
                ORMService.createContact(contact!!)
                activity?.onBackPressed()
            }
        } else {
            if (fillContactInfo()) {
                ORMService.updateContact(contact!!)
                activity?.onBackPressed()
            }
        }
    }

    /**
     * Return false if an exception occurs.
     */
    private fun fillContactInfo(): Boolean {
        contact?.firstName = etDetailsFirstName.text.toString()
        contact?.lastName = etDetailsLastName.text.toString()
        try {
            contact?.dateOfBirth = LocalDate.parse(
                etDetailsDateOfBirth.text.toString(),
                DateTimeFormatter.ofPattern("MM-dd-yyyy")
            )

            val email = etDetailsEmail.text.toString()
            if (email.isEmpty()) throw RuntimeException("email")
            contact?.email = email

            val phoneNumber = etDetailsPhoneNumber.text.toString()
            if (phoneNumber.isEmpty()) throw RuntimeException("phone number")
            contact?.phoneNumber = phoneNumber
        } catch (e: DateTimeException) {
            Toast.makeText(this.context, "Please enter a valid date", Toast.LENGTH_SHORT).show()
            return false
        } catch (re: RuntimeException) {
            Toast.makeText(
                this.context,
                "Please enter a valid ${re.localizedMessage}",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        contact?.isFavorite = isStarFull
        contact?.cityId = spCity.selectedItemId + 1
        return true
    }

}
