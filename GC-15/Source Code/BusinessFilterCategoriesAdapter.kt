package com.app.pepens.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.pepens.R
import com.app.pepens.fragment.CategoryBusinessDetailsFragment
import com.app.pepens.model.ItemCategories.ItemCategoriesData
import kotlinx.android.synthetic.main.custom_shopping_filter_item_layout.view.*

/**
 * Created by Vikas Kumar Singh on 22/12/20.
 */

class BusinessFilterCategoriesAdapter : RecyclerView.Adapter<BusinessFilterCategoriesAdapter.ViewHolder> {

    private var context: Context
    private var itemCategories: ArrayList<ItemCategoriesData>
    private var filterClickEvent: FilterClickEvent

    constructor(
        context: Context,
        itemCategories: ArrayList<ItemCategoriesData>,
        categoryBusinessDetailsFragment: CategoryBusinessDetailsFragment
    ) : super() {
        this.context = context
        this.itemCategories = itemCategories
        this.filterClickEvent = categoryBusinessDetailsFragment
    }

    interface FilterClickEvent {
        fun clickOnFilterItem(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_shopping_filter_item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return itemCategories.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = itemCategories[position].itemCategoryName
        if (itemCategories[position].isSelected) {
            holder.ivCheck.visibility = View.VISIBLE
        } else {
            holder.ivCheck.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            filterClickEvent.clickOnFilterItem(position)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.tvTitle
        val ivCheck: ImageView = view.ivCheck
    }

}