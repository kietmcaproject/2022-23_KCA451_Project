package com.app.pepens.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.app.pepens.R
import com.app.pepens.model.Categories.CategoriesData
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.custom_child_category_layout.view.*
import java.util.*

/**
 * Created by Vikas Kumar Singh on 11/07/20.
 */

class ChildCategoriesAdapter : RecyclerView.Adapter<ChildCategoriesAdapter.ViewHolder>, Filterable {

    private var context: Context
    private var categoriesData: ArrayList<CategoriesData>
    private var listFiltered: MutableList<CategoriesData>

    constructor(context: Context, categoriesData: ArrayList<CategoriesData>) : super() {
        this.context = context
        this.categoriesData = categoriesData
        this.listFiltered = categoriesData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.custom_child_category_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listFiltered.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(listFiltered!![position].iconUrl)
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.imageView)
        holder.tvTitle.text = listFiltered!![position].businessCategoryName

        holder.itemView.setOnClickListener {
            var bundle = Bundle()
            bundle.putParcelable("data", listFiltered!![position])
            if (it.findNavController().currentDestination?.id == R.id.allCategoriesFragment)
                it.findNavController().navigate(R.id.action_allCategoriesFragment_to_categoryBusinessFragment, bundle)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.imageView
        val tvTitle: TextView = view.tvTitle
        val layout: ConstraintLayout = view.layout
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                var charString: String = p0.toString()
                listFiltered = if (charString.isEmpty()) {
                    categoriesData
                } else {
                    var filteredList: MutableList<CategoriesData> = mutableListOf()
                    for (s: CategoriesData in categoriesData) {
                        if (s.businessCategoryName!!.toLowerCase(Locale.ROOT)
                                .contains(charString.toLowerCase(Locale.ROOT))
                        ) {
                            filteredList.add(s)
                        }
                    }
                    filteredList
                }
                var filterResult = FilterResults()
                filterResult.values = listFiltered
                return filterResult
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                listFiltered = p1!!.values as MutableList<CategoriesData>
                notifyDataSetChanged()
            }
        }
    }

}