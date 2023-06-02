package com.app.pepens.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.pepens.R
import com.app.pepens.fragment.AllCategoriesFragment
import com.app.pepens.model.Categories.CategoriesData
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.custom_child_list_items_layout.view.*

class CategorySearchChildItemsAdapter :
    RecyclerView.Adapter<CategorySearchChildItemsAdapter.ViewHolder> {

    private val context: Context
    private val list: MutableList<CategoriesData>
    private var itemClick: ItemClick

    constructor(
        context: Context,
        list: ArrayList<CategoriesData>,
        allCategoriesFragment: AllCategoriesFragment
    ) : super() {
        this.context = context
        this.list = list
        this.itemClick = allCategoriesFragment
    }

    interface ItemClick {
        fun itemClick(data: CategoriesData)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_child_list_items_layout, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvTitle.text = "${list[position].businessCategoryName}"
        Glide.with(context)
            .load(list[position].iconUrl)
            .placeholder(R.mipmap.ic_launcher)
            .into(viewHolder.imageView)

        viewHolder.itemView.setOnClickListener {
            itemClick.itemClick(list!![position])
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.tvTitle
        val imageView: ImageView = view.imageView
    }

}