package com.app.pepens.adapter

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.app.pepens.R
import com.app.pepens.fragment.ShoppingFragment
import com.app.pepens.model.ItemCategories.ItemCategory
import com.app.pepens.utils.Utils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.custom_shopping_category_layout.view.*

/**
 * Created by Vikas Kumar Singh on 11/07/20.
 */

class AllSubCategoriesAdapter : RecyclerView.Adapter<AllSubCategoriesAdapter.ViewHolder> {

    private var context: Context
    private var itemCategoryArrayList: ArrayList<ItemCategory>

    constructor(context: Context, itemCategoryArrayList: ArrayList<ItemCategory>) : super() {
        this.context = context
        this.itemCategoryArrayList = itemCategoryArrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.custom_shopping_category_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return itemCategoryArrayList.size
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var sharedPreferences = context!!.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        Glide.with(context)
            .load(itemCategoryArrayList!![position].defaultPictureUrl)
            .placeholder(R.drawable.ic_category_icon)
            .into(holder.imageView)
        holder.tvTitle.text = itemCategoryArrayList!![position].itemCategoryName

        holder.itemView.setOnClickListener {
            Utils().clickStream(
                context,
                sharedPreferences.getString("USER_ID", "0")!!,
                "25",
                "${itemCategoryArrayList!![position].itemCategoryId}",
                "23",
                "${itemCategoryArrayList!![position].parentItemCategoryId}",
                ""
            )
            var bundle = Bundle()
            bundle.putInt("BusinessCategoryId", ShoppingFragment.shoppingBusinessCategoryId)
            bundle.putInt("CategoryId", itemCategoryArrayList!![position].parentItemCategoryId)
            bundle.putInt("SubCategoryId", itemCategoryArrayList!![position].itemCategoryId)
            if (Navigation.findNavController(it).currentDestination?.id == R.id.shoppingFragment)
                it.findNavController().navigate(R.id.action_shoppingFragment_to_shoppingProductsFragment, bundle)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.imageView
        val tvTitle: TextView = view.tvTitle
    }

}