package com.app.pepens.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.pepens.R
import com.app.pepens.fragment.CategoryBusinessFragment
import com.app.pepens.model.Categories.CategoriesData
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.custom_child_list_items_layout.view.*

class CatChildItemsAdapter : RecyclerView.Adapter<CatChildItemsAdapter.ViewHolder>, Filterable {

    private val context: Context
    private val list: MutableList<CategoriesData>
    private var listFiltered: MutableList<CategoriesData>
//    var onItemClick: ((CategoriesData) -> Unit)? = null
    private var categoryBusinessFragment: CategoryBusinessFragment
    private var itemClick: ItemClick

    constructor(
        context: Context,
        list: ArrayList<CategoriesData>,
        categoryBusinessFragment: CategoryBusinessFragment
    ) : super() {
        this.context = context
        this.list = list
        this.listFiltered = list
        this.categoryBusinessFragment = categoryBusinessFragment
        this.itemClick = categoryBusinessFragment
    }

    interface ItemClick {
        fun itemClick(data: CategoriesData)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_child_list_items_layout, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return listFiltered.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvTitle.text = "${listFiltered[position].businessCategoryName}"
        if (listFiltered[position].businessCategoryName == "All Categories"){
            viewHolder.imageView.setBackgroundResource(R.drawable.ic_categories)
        } else {
            Glide.with(context)
                .load(listFiltered[position].iconUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(viewHolder.imageView)
        }

        viewHolder.itemView.setOnClickListener {
            itemClick.itemClick(listFiltered[position])
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.tvTitle
        val imageView:ImageView = view.imageView
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                var charString: String = p0.toString()
                listFiltered = if (charString.isEmpty()) {
                    list
                } else {
                    var filteredList: MutableList<CategoriesData> = mutableListOf()
                    for (s: CategoriesData in list) {
                        if (s.businessCategoryName!!.toLowerCase().contains(charString.toLowerCase())) {
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