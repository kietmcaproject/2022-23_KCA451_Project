package com.app.pepens.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.pepens.R
import com.app.pepens.fragment.PaymentsFragment
import com.app.pepens.model.Coupon.CouponData
import kotlinx.android.synthetic.main.custom_coupon_layout.view.*

class CouponsAdapter : RecyclerView.Adapter<CouponsAdapter.ViewHolder> {

    private var context: Context
    private var couponData: ArrayList<CouponData>
    private var clickEvent: ClickEvent

    constructor(
        context: Context,
        couponData: ArrayList<CouponData>,
        paymentsFragment: PaymentsFragment
    ) {
        this.context = context
        this.couponData = couponData
        this.clickEvent = paymentsFragment
    }

    interface ClickEvent {
        fun clickCoupon(position: Int)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.custom_coupon_layout, viewGroup, false)
        )
    }

    override fun getItemCount(): Int {
        return couponData.count()
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvTitle.text = couponData[position].couponName
        viewHolder.tvCode.text = couponData[position].couponCode
        viewHolder.tvDescription.text = couponData[position].note
        if (couponData[position].isSelected) {
            viewHolder.layout.setBackgroundResource(R.drawable.doted_selected_view)
        } else {
            viewHolder.layout.setBackgroundResource(R.drawable.doted_default_view)
        }

        viewHolder.itemView.setOnClickListener {
            clickEvent.clickCoupon(position)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val layout: ConstraintLayout = view.layout
        val tvTitle: TextView = view.tvTitle
        val tvCode: TextView = view.tvCode
        val tvDescription: TextView = view.tvDescription
    }

}