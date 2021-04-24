package hr.amaurov.niamu.orm_presentation.utils

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import hr.amaurov.niamu.orm_presentation.R
import hr.amaurov.niamu.orm_presentation.models.Contact
import kotlinx.android.synthetic.main.recyclerview_contact_row.view.*

class ContactsAdapter(
    var dataset: List<Contact>?,
    private val context: Context,
    private val listener: (Contact) -> Unit
)
    : RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.recyclerview_contact_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvFullName.text = "${dataset?.get(position)?.firstName} ${dataset?.get(position)?.lastName}"
        holder.ivFavorite.isVisible = dataset?.get(position)?.isFavorite!!
        holder.itemView.setOnClickListener{ listener(dataset!![position]) }
    }

    override fun getItemCount(): Int {
        return dataset?.size!!
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvFullName: TextView = view.tvFullName
    val ivFavorite: ImageView = view.ivFavorite
}