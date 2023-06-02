package com.app.pepens.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.pepens.R
import com.app.pepens.fragment.AllCategoriesFragment
import com.app.pepens.model.Categories.CategoriesData
import kotlinx.android.synthetic.main.custom_list_items_layout.view.*

class CategorySearchItemsAdapter : RecyclerView.Adapter<CategorySearchItemsAdapter.ViewHolder> {

    private val context: Context
    private val list: MutableList<CategoriesData>
    private var allCategoriesFragment: AllCategoriesFragment

    constructor(
        context: Context,
        list: ArrayList<CategoriesData>,
        allCategoriesFragment: AllCategoriesFragment
    ) : super() {
        this.context = context
        this.list = list
        this.allCategoriesFragment = allCategoriesFragment
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_list_items_layout, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvTitle.text = "${list[position].businessCategoryName}"
        viewHolder.tvTitle.typeface = Typeface.DEFAULT_BOLD
        viewHolder.recyclerView.visibility = View.VISIBLE
        viewHolder.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        var allCategoriesAdapter = CategorySearchChildItemsAdapter(
            context,
            list[position].categoriesData,
            allCategoriesFragment
        )
        viewHolder.recyclerView.adapter = allCategoriesAdapter
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.tvTitle
        val recyclerView: RecyclerView = view.recyclerView
    }

}