package com.app.pepens.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.pepens.R
import com.app.pepens.fragment.BusinessProductsFragment
import com.app.pepens.model.ItemCategories.ItemCategory
import kotlinx.android.synthetic.main.custom_item_category_layout.view.*

/**
 * Created by Vikas Kumar Singh on 29/09/21.
 */

class BusinessSubCategoriesAdapter : RecyclerView.Adapter<BusinessSubCategoriesAdapter.ViewHolder> {

    private var context: Context
    private var itemCategory: ArrayList<ItemCategory>
    private var clickEvent: ClickEvent

    constructor(
        context: Context,
        itemCategory: ArrayList<ItemCategory>,
        businessProductsFragment: BusinessProductsFragment
    ) : super() {
        this.context = context
        this.itemCategory = itemCategory
        this.clickEvent = businessProductsFragment
    }

    interface ClickEvent {
        fun clickOnSubCatItem(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_item_category_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return itemCategory!!.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = itemCategory!![position].itemCategoryName
        if (itemCategory!![position].isSelected) {
            holder.tvTitle.setTextColor(context.resources.getColor(R.color.colorPrimary))
            holder.tvTitle.setBackgroundResource(R.drawable.view_cat_active)
        } else {
            holder.tvTitle.setTextColor(context.resources.getColor(R.color.colorText))
            holder.tvTitle.setBackgroundResource(R.drawable.view_brand_inactive)
        }

        holder.itemView.setOnClickListener {
            clickEvent.clickOnSubCatItem(position)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.tvTitle
    }

}