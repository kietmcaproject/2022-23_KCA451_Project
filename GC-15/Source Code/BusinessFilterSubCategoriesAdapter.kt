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
import com.app.pepens.model.ItemCategories.ItemCategory
import kotlinx.android.synthetic.main.custom_shopping_filter_item_layout.view.*

/**
 * Created by Vikas Kumar Singh on 22/12/20.
 */

class BusinessFilterSubCategoriesAdapter : RecyclerView.Adapter<BusinessFilterSubCategoriesAdapter.ViewHolder> {

    private var context: Context
    private var itemCategory: ArrayList<ItemCategory>
    private var filterClickEvent: FilterClickEvent

    constructor(
        context: Context,
        itemCategory: ArrayList<ItemCategory>,
        categoryBusinessDetailsFragment: CategoryBusinessDetailsFragment
    ) : super() {
        this.context = context
        this.itemCategory = itemCategory
        this.filterClickEvent = categoryBusinessDetailsFragment
    }

    interface FilterClickEvent {
        fun filterClickOnSubCatItem(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.custom_shopping_filter_item_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return itemCategory!!.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = itemCategory!![position].itemCategoryName
        if (itemCategory!![position].isSelected) {
            holder.ivCheck.visibility = View.VISIBLE
        } else {
            holder.ivCheck.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            filterClickEvent.filterClickOnSubCatItem(position)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.tvTitle
        val ivCheck: ImageView = view.ivCheck
    }

}