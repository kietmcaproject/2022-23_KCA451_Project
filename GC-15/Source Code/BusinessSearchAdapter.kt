package com.app.pepens.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.pepens.R
import com.app.pepens.fragment.CategoryBusinessFragment
import com.app.pepens.model.SearchAutoComplete.AutoCompleteData
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.custom_search_layout.view.*

class BusinessSearchAdapter : RecyclerView.Adapter<BusinessSearchAdapter.ViewHolder> {

    private val context: Context
    private val autoCompleteData: ArrayList<AutoCompleteData>
    private val updateClick: UpdateClick
    private val type: Int

    constructor(
        context: Context,
        autoCompleteData: ArrayList<AutoCompleteData>,
        categoryBusinessFragment: CategoryBusinessFragment,
        type: Int
    ) : super() {
        this.context = context
        this.autoCompleteData = autoCompleteData
        this.updateClick = categoryBusinessFragment
        this.type = type
    }

    interface UpdateClick {
        fun itemClick(position: Int, type: Int)
        fun itemUpClick(position: Int, type: Int)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.custom_search_layout, viewGroup, false)
        )
    }

    override fun getItemCount(): Int {
        return autoCompleteData.count()
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvTitle.text = autoCompleteData[position].label
        if (!autoCompleteData[position].inText.isNullOrEmpty()) {
            viewHolder.tvType.visibility = View.VISIBLE
            viewHolder.tvType.text = autoCompleteData[position].inText
        } else {
            viewHolder.tvType.visibility = View.GONE
        }
        Glide.with(context)
            .load(autoCompleteData[position].img)
            .placeholder(R.drawable.placeholder_view)
            .error(R.drawable.ic_action_icon)
            .into(viewHolder.imageView)

        viewHolder.itemView.setOnClickListener {
            updateClick.itemClick(position, type)
        }
        viewHolder.ivUp.setOnClickListener {
            updateClick.itemUpClick(position, type)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.imageView
        val ivUp: ImageView = view.ivUp
        val tvTitle: TextView = view.tvTitle
        val tvType: TextView = view.tvType
    }

}