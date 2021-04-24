package hr.amaurov.niamu.orm_presentation.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import hr.amaurov.niamu.orm_presentation.R
import hr.amaurov.niamu.orm_presentation.databinding.SpinnerRowBinding
import hr.amaurov.niamu.orm_presentation.orm.room.entities.CityRoom

class CityAdapter(val context: Context, var dataSource: List<CityRoom>) : BaseAdapter() {

    val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var binding: SpinnerRowBinding? = null

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        if (convertView == null) {
            view = mInflater.inflate(R.layout.spinner_row, parent, false)
            binding= SpinnerRowBinding.bind(view)
        } else {
            view = convertView
        }

        binding!!.tvCityName.text=dataSource[position].name

        return view
    }

}

