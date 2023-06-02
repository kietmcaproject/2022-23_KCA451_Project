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
import com.app.pepens.model.Business.MultipleContacts
import kotlinx.android.synthetic.main.custom_business_calls_layout.view.*

/**
 * Created by Vikas Kumar Singh on 30/01/21.
 */

class BusinessDetailsContactsAdapter :
    RecyclerView.Adapter<BusinessDetailsContactsAdapter.ViewHolder> {

    private var context: Context
    private var multipleContacts: ArrayList<MultipleContacts>
    private var eventsListeners: EventListeners

    interface EventListeners {
        fun makeCall(position: Int)
    }

    constructor(
        context: Context,
        multipleContacts: ArrayList<MultipleContacts>,
        categoryBusinessDetailsFragment: CategoryBusinessDetailsFragment
    ) : super() {
        this.context = context
        this.multipleContacts = multipleContacts
        this.eventsListeners = categoryBusinessDetailsFragment
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.custom_business_calls_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return multipleContacts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = multipleContacts[position].title
        holder.tvTime.text = multipleContacts[position].contactNo

        holder.ivCall.setOnClickListener {
            eventsListeners.makeCall(position)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.tvTitle
        val tvTime: TextView = view.tvTime
        val ivCall: ImageView = view.ivCall
    }

}