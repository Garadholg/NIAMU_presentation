package hr.amaurov.niamu.orm_presentation.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import hr.amaurov.niamu.orm_presentation.R
import hr.amaurov.niamu.orm_presentation.databinding.FragmentContactDetailsBinding
import hr.amaurov.niamu.orm_presentation.databinding.FragmentContactsListBinding
import hr.amaurov.niamu.orm_presentation.databinding.RecyclerviewContactRowBinding
import hr.amaurov.niamu.orm_presentation.orm.room.entities.ContactRoom

class ContactsAdapter(private val items: List<ContactRoom>, private val context: Context, private val listener: (ContactRoom) -> Unit)
    : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_contact_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tvFullName?.text = items[position].firstName + " " + items[position].lastName
        holder?.ivFavorite?.isVisible = items[position].isFavorite!!
        holder?.itemView.setOnClickListener{ listener(items[position]) }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val binding=RecyclerviewContactRowBinding.bind(view)
    val tvFullName: TextView = binding.tvFullName
    val ivFavorite: ImageView = binding.ivFavorite
}