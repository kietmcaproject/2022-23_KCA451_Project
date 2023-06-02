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
import com.app.pepens.fragment.AllProductsFragment
import com.app.pepens.model.ProductsWithCategory.BusinessItem
import kotlinx.android.synthetic.main.custom_business_items_layout.view.*

class AllProductsAdapter : RecyclerView.Adapter<AllProductsAdapter.ViewHolder> {

    private var context: Context
    private var businessItems: ArrayList<BusinessItem>
    private var clickEvent: ClickEvent

    constructor(
        context: Context,
        businessItems: ArrayList<BusinessItem>,
        allProductsFragment: AllProductsFragment
    ) {
        this.context = context
        this.businessItems = businessItems
        this.clickEvent = allProductsFragment
    }

    interface ClickEvent {
        fun viewBrands(itemId: BusinessItem)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.custom_business_items_layout, viewGroup, false)
        )
    }

    override fun getItemCount(): Int {
        return businessItems.count()
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvTitle.text = businessItems!![position].item!!.itemName

        if (businessItems!![position].inStock) {
            viewHolder.ivCheck.setColorFilter(
                ContextCompat.getColor(context, R.color.green),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
            viewHolder.tvTitle.setTextColor(context.resources.getColor(R.color.colorText))
            viewHolder.tvBrands.setTextColor(context.resources.getColor(R.color.green))
            viewHolder.tvBrands.setBackgroundResource(R.drawable.view_brand_active)
        } else {
            viewHolder.ivCheck.setColorFilter(
                ContextCompat.getColor(context, R.color.grey),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
            viewHolder.tvTitle.setTextColor(context.resources.getColor(R.color.grey))
            viewHolder.tvBrands.setTextColor(context.resources.getColor(R.color.grey))
            viewHolder.tvBrands.setBackgroundResource(R.drawable.view_brand_inactive)
        }
        if (businessItems!![position].noOfBrands > 0) {
            viewHolder.tvBrands.visibility = View.VISIBLE
        } else {
            viewHolder.tvBrands.visibility = View.GONE
        }

        viewHolder.tvBrands.setOnClickListener {
//            if (businessItems[position].inStock){
            clickEvent.viewBrands(businessItems[position])
//            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivCheck: ImageView = view.ivCheck
        val tvTitle: TextView = view.tvTitle
        val tvBrands: TextView = view.tvBrands
    }

}