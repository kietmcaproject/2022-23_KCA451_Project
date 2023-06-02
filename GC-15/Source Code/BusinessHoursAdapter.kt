package com.app.pepens.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.app.pepens.R
import com.app.pepens.model.Restaurants.RestaurentDetails.BusinessHours
import kotlinx.android.synthetic.main.custom_business_hours_layout.view.*

/**
 * Created by Vikas Kumar Singh on 24/11/20.
 */

class BusinessHoursAdapter : RecyclerView.Adapter<BusinessHoursAdapter.ViewHolder> {

    private var context: Context
    private var businessHours: ArrayList<BusinessHours>

    constructor(
        context: Context,
        businessHours: ArrayList<BusinessHours>
    ) : super() {
        this.context = context
        this.businessHours = businessHours
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.custom_business_hours_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return businessHours.size
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position > 0) {
            if (businessHours[position].day == businessHours[position - 1].day) {
                holder.tvTitle.text = ""
            } else {
                holder.tvTitle.text = businessHours[position].day
            }
        } else {
            holder.tvTitle.text = businessHours[position].day
        }
        if (businessHours[position].isOn) {
            if (businessHours[position].is24Hours) {
                holder.tvTime.text = "24 Hours"
                holder.tvTime.setTextColor(context.resources.getColor(R.color.colorText))
            } else {
                holder.tvTime.text = "${businessHours[position].openTime} - ${businessHours[position].closeTime}"
                holder.tvTime.setTextColor(context.resources.getColor(R.color.colorText))
            }
        } else {
            holder.tvTime.text = "Close"
            holder.tvTime.setTextColor(context.resources.getColor(R.color.red))
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.tvTitle
        val tvTime: TextView = view.tvTime
    }

}