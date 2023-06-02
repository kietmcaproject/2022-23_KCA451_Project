package com.app.pepens.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.pepens.R
import com.app.pepens.fragment.ShoppingFragment
import com.app.pepens.model.ItemCategories.ItemCategories
import com.app.pepens.utils.Utils
import kotlinx.android.synthetic.main.custom_all_categories_layout.view.*

class AllCategoriesAdapter : RecyclerView.Adapter<AllCategoriesAdapter.ViewHolder> {

    private var context: Context
    private var itemCategories: ItemCategories

    constructor(context: Context, itemCategories: ItemCategories) {
        this.context = context
        this.itemCategories = itemCategories
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.custom_all_categories_layout, viewGroup, false)
        )
    }

    override fun getItemCount(): Int {
        return if (itemCategories!!.itemCategoriesData == null) {
            0
        } else {
            itemCategories!!.itemCategoriesData!!.size
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        var sharedPreferences = context.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        viewHolder.tvTitle.text = "Shop by ${itemCategories!!.itemCategoriesData!![position].itemCategoryName}"

        if (itemCategories!!.itemCategoriesData!![position].maxDiscountPercentage <= 0) {
            viewHolder.tvDiscount.visibility = View.GONE
        } else {
            viewHolder.tvDiscount.visibility = View.VISIBLE
            viewHolder.tvDiscount.text = "Upto ${itemCategories!!.itemCategoriesData!![position].maxDiscountPercentage}% OFF"
        }

        viewHolder.recyclerView.layoutManager = GridLayoutManager(context, 3)
        var categoriesAdapter = AllSubCategoriesAdapter(
            context,
            itemCategories.itemCategoriesData!![position].itemCategories!!
        )
        viewHolder.recyclerView.adapter = categoriesAdapter

        viewHolder.tvViewAll.setOnClickListener {
            Utils().clickStream(
                context,
                sharedPreferences.getString("USER_ID", "0")!!,
                "25",
                "${itemCategories!!.itemCategoriesData!![position].itemCategoryId}",
                "23",
                "${itemCategories!!.itemCategoriesData!![position].itemCategoryId}",
                ""
            )
            var bundle = Bundle()
            bundle.putInt("BusinessCategoryId", ShoppingFragment.shoppingBusinessCategoryId)
            bundle.putInt("CategoryId", itemCategories!!.itemCategoriesData!![position].itemCategoryId)
            bundle.putInt("SubCategoryId", 0)
            if (Navigation.findNavController(it).currentDestination?.id == R.id.shoppingFragment)
            it.findNavController().navigate(R.id.action_shoppingFragment_to_shoppingProductsFragment, bundle)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.tvTitle
        val tvViewAll: TextView = view.tvViewAll
        val tvDiscount: TextView = view.tvDiscount
        val recyclerView: RecyclerView = view.recyclerView
    }

}