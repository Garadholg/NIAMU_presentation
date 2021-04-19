package hr.amaurov.niamu.orm_presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hr.amaurov.niamu.orm_presentation.R
import kotlinx.android.synthetic.main.fragment_contact_details.*

private const val CONTACT_KEY = "PRODUCT"

class ContactDetailsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arguments = arguments

        etDetailsFullName.setText(arguments?.getString(CONTACT_KEY))
    }

}