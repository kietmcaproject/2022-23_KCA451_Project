package com.app.pepens.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.pepens.R
import com.app.pepens.fragment.CategoryBusinessFragment
import com.app.pepens.model.TrendingSearch.TrendingSearchData
import kotlinx.android.synthetic.main.custom_search_history_layout.view.*

class BusinessDiscoverMoreAdapter : RecyclerView.Adapter<BusinessDiscoverMoreAdapter.ViewHolder> {

    private var context: Context
    private var list: ArrayList<TrendingSearchData>
    private var clickEvent:ClickEvent? =null

    constructor(context: Context, list: ArrayList<TrendingSearchData>, categoryBusinessFragment: CategoryBusinessFragment) {
        this.context = context
        this.list = list
        this.clickEvent = categoryBusinessFragment
    }

    interface ClickEvent {
        fun discoverMoreItemClick(position: TrendingSearchData)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.custom_search_history_layout, viewGroup, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvTitle.text = list!![position].itemName

        viewHolder.itemView.setOnClickListener {
            clickEvent!!.discoverMoreItemClick(list!![position])
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.tvTitle
    }

}