package com.app.pepens.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.app.pepens.R
import com.app.pepens.model.Brands.Brands
import kotlinx.android.synthetic.main.custom_business_items_layout.view.*

class BrandsAdapter : RecyclerView.Adapter<BrandsAdapter.ViewHolder> {

    private var context: Context
    private var brands: Brands

    constructor(context: Context, brands: Brands) {
        this.context = context
        this.brands = brands
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.custom_business_items_layout, viewGroup, false)
        )
    }

    override fun getItemCount(): Int {
        return if (brands!!.brandsData == null) {
            0
        } else {
            brands!!.brandsData!!.count()
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvTitle.text = brands!!.brandsData!![position].brand!!.brandName
        viewHolder.ivCheck.visibility = View.GONE
        viewHolder.tvBrands.visibility = View.GONE
        viewHolder.ivCheck1.visibility = View.VISIBLE
        if (brands!!.brandsData!![position].inStock) {
            viewHolder.ivCheck1.setColorFilter(ContextCompat.getColor(context, R.color.green), android.graphics.PorterDuff.Mode.SRC_IN)
            viewHolder.tvTitle.setTextColor(context.resources.getColor(R.color.black))
        } else {
            viewHolder.ivCheck1.setColorFilter(ContextCompat.getColor(context, R.color.grey), android.graphics.PorterDuff.Mode.SRC_IN)
            viewHolder.tvTitle.setTextColor(context.resources.getColor(R.color.grey))
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivCheck: ImageView = view.ivCheck
        val ivCheck1: ImageView = view.ivCheck1
        val tvTitle: TextView = view.tvTitle
        val tvBrands: TextView = view.tvBrands
    }

}