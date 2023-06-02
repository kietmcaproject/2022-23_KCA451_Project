package com.app.pepens.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.pepens.R
import com.app.pepens.fragment.CategoryBusinessFragment
import com.app.pepens.model.SearchAutoComplete.AutoCompleteData
import kotlinx.android.synthetic.main.custom_search_history_layout.view.*

class BusinessHistoryAdapter : RecyclerView.Adapter<BusinessHistoryAdapter.ViewHolder> {

    private var context: Context
    private var list: ArrayList<AutoCompleteData>
    private var clickEvent:ClickEvent? =null

    constructor(context: Context, list: ArrayList<AutoCompleteData>, categoryBusinessFragment: CategoryBusinessFragment) {
        this.context = context
        this.list = list
        this.clickEvent = categoryBusinessFragment
    }

    interface ClickEvent {
        fun historyItemClick(position: Int)
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
        viewHolder.tvTitle.text = list!![position].label

        viewHolder.itemView.setOnClickListener {
            clickEvent!!.historyItemClick(position)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.tvTitle
    }

}