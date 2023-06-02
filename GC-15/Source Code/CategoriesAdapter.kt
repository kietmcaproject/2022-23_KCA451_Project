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
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.app.pepens.R
import com.app.pepens.model.Categories.Categories
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.custom_category_layout.view.*

/**
 * Created by Vikas Kumar Singh on 11/07/20.
 */

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private var context: Context
    private var categories: Categories

    constructor(context: Context, categories: Categories) : super() {
        this.context = context
        this.categories = categories
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.custom_category_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (categories == null) {
            0
        } else {
            categories!!.categoriesData!!.count()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
            holder.imageView.setImageResource(R.drawable.ic_view_list)
        } else {
            Glide.with(context)
                .load(categories!!.categoriesData!![position].displayIconUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imageView)
        }
        holder.tvTitle.text = categories!!.categoriesData!![position].businessCategoryName

        holder.itemView.setOnClickListener {
            var bundle = Bundle()
            bundle.putParcelable("data", categories!!.categoriesData!![position])
            if (it.findNavController().currentDestination?.id == R.id.categoriesFragment)
                it.findNavController().navigate(R.id.action_categoriesFragment_to_categoryBusinessFragment, bundle)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.imageView
        val tvTitle: TextView = view.tvTitle
    }

}