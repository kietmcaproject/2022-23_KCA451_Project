package com.app.pepens.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.pepens.R
import com.app.pepens.fragment.CategoryBusinessFragment
import com.app.pepens.model.Business.Business
import com.app.pepens.utils.Utils
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.custom_business_layout.view.*
import kotlinx.android.synthetic.main.fragment_category_business_details.*
import java.math.RoundingMode

/**
 * Created by Vikas Kumar Singh on 11/07/20.
 */

class BusinessAdapter : RecyclerView.Adapter<BusinessAdapter.ViewHolder> {

    private var context: Context
    private var business: Business
    private var eventsListeners: EventListeners

    interface EventListeners {
        fun showHours(position: Int)
        fun makeCall(position: Int)
        fun openLocation(position: Int)
        fun openOffer(position: Int)
        fun makeFavorite(position: Int)
        fun openGallery(position: Int)
        fun viewImage(position: Int, i: Int)
        fun closeDialog()
    }

    constructor(
        context: Context,
        business: Business,
        categoryBusinessFragment: CategoryBusinessFragment
    ) : super() {
        this.context = context
        this.business = business
        this.eventsListeners = categoryBusinessFragment
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.custom_business_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (business == null) {
            0
        } else {
            business.businessData!!.count()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var sharedPreferences = context!!.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        var displayMetrics = DisplayMetrics()
        (context as FragmentActivity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        var width = displayMetrics.widthPixels
        var height = (width / 2.33).toInt()
        holder.ivBanner.layoutParams.height = height
        Glide.with(context!!)
            .load(business!!.businessData!![position].coverPictureUrl)
            .placeholder(R.drawable.business_banner)
            .into(holder.ivBanner)
        Glide.with(context!!)
            .load(business!!.businessData!![position].defaultPictureUrl)
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.ivProfilePic)
        holder.tvTitle.text = business!!.businessData!![position].businessName
        if (business!!.businessData!![position].distance.isNullOrEmpty()) {
            holder.tvDistance.text = "0 KM"
        } else {
            holder.tvDistance.text = "${
                business!!.businessData!![position].distance!!.toBigDecimal()
                    .setScale(2, RoundingMode.HALF_UP).toDouble()
            } KM"
        }
//        holder.tvProducts.text = business!!.businessData!![position].noOfItems.toString()
        holder.tvCategory.text = business!!.businessData!![position].businessCategoryName
        if (business!!.businessData!![position].isFavorite) {
            holder.clFav.setBackgroundColor(context.resources.getColor(R.color.pink))
            holder.ivFavorite.setImageResource(R.drawable.ic_favorite)
        } else {
            holder.clFav.setBackgroundColor(context.resources.getColor(R.color.black))
            holder.ivFavorite.setImageResource(R.drawable.ic_favorite_border)
        }
        if (business!!.businessData!![position].noOfActiveOffers > 0) {
            holder.tvOfferCount.visibility = View.VISIBLE
            holder.tvOfferCount.text =
                business!!.businessData!![position].noOfActiveOffers.toString()
        } else {
            holder.tvOfferCount.visibility = View.GONE
        }
        if (business!!.businessData!![position].itemsAvailabilityPercentage > 0) {
            holder.tvProductCount.visibility = View.VISIBLE
            holder.tvProductCount.text =
                "${business!!.businessData!![position].itemsAvailabilityPercentage}%"
        } else {
            holder.tvProductCount.visibility = View.GONE
        }
        if (business!!.businessData!![position].isAvailableHomeDelivery) {
            holder.tvDelivery.visibility = View.VISIBLE
        } else {
            holder.tvDelivery.visibility = View.GONE
        }
        if (business!!.businessData!![position].noOfMatchedItems > 0) {
            if (business!!.businessData!![position].noOfMatchedItems == 1) {
                holder.tvMatch.text =
                    "${business!!.businessData!![position].noOfMatchedItems} matched"
            } else {
                holder.tvMatch.text =
                    "${business!!.businessData!![position].noOfMatchedItems} matched"
            }
            holder.tvMatch.visibility = View.VISIBLE
        } else {
            holder.tvMatch.visibility = View.GONE
        }

        if (business.businessData!![position].rating > 0) {
            holder.tvRatingValue.text = "${business.businessData!![position].rating.toString().replace(".0", "")} rating"
        } else {
            holder.tvRatingValue.text = "No rating"
        }
        when {
            business.businessData!![position].rating <= 1 -> {
                holder.cv6.background.setTint(Color.parseColor("#FF0000"))
                holder.ivRatingStar.imageTintList =
                    ColorStateList.valueOf(Color.parseColor("#FF0000"))
            }
            business.businessData!![position].rating <= 3 -> {
                holder.cv6.background.setTint(Color.parseColor("#FF8C00"))
                holder.ivRatingStar.imageTintList =
                    ColorStateList.valueOf(Color.parseColor("#FF8C00"))
            }
            else -> {
                holder.cv6.background.setTint(Color.parseColor("#09AF00"))
                holder.ivRatingStar.imageTintList =
                    ColorStateList.valueOf(Color.parseColor("#09AF00"))
            }
        }

        if (business!!.businessData!![position].items != null) {
            if (business!!.businessData!![position].items!!.size > 0) {
                holder.ivAutoDetectArrow.visibility = View.VISIBLE
                holder.recyclerViewProductsItems.visibility = View.VISIBLE
                val layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                holder.recyclerViewProductsItems.layoutManager = layoutManager
                var businessAdapter =
                    ProductItemsAdapter(context, business.businessData!![position].items!!)
                holder.recyclerViewProductsItems.adapter = businessAdapter
            } else {
                holder.recyclerViewProductsItems.visibility = View.GONE
                holder.ivAutoDetectArrow.visibility = View.GONE
            }
        } else {
            holder.recyclerViewProductsItems.visibility = View.GONE
            holder.ivAutoDetectArrow.visibility = View.GONE
        }

        when (business.businessData!![position].onOffStatus) {
            0 -> {
                holder.tvStatus.text = "CLOSED"
                holder.tvStatus.setTextColor(context.resources.getColor(R.color.red))
                holder.tvStatus.setBackgroundResource(R.drawable.view_shop_close)
                holder.ivArrow.setColorFilter(
                    ContextCompat.getColor(context, R.color.red),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
            }
            1 -> {
                holder.tvStatus.text = "OPEN"
                holder.tvStatus.setTextColor(context.resources.getColor(R.color.green))
                holder.tvStatus.setBackgroundResource(R.drawable.view_shop_open)
                holder.ivArrow.setColorFilter(
                    ContextCompat.getColor(context, R.color.green),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
            }
            2 -> {
                holder.tvStatus.text = "BUSY"
                holder.tvStatus.setTextColor(context.resources.getColor(R.color.grey))
                holder.tvStatus.setBackgroundResource(R.drawable.view_shop_busy)
                holder.ivArrow.setColorFilter(
                    ContextCompat.getColor(context, R.color.grey),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
            }
        }

        holder.tvStatus.setOnClickListener {
            if (!business!!.businessData!![position].businessHours.isNullOrEmpty()) {
                eventsListeners.showHours(position)
            }
        }

        holder.cl1.setOnClickListener {
            eventsListeners.openLocation(position)
        }

        holder.cl2.setOnClickListener {
            eventsListeners.makeCall(position)
        }

        holder.cl3.setOnClickListener {
            eventsListeners.openOffer(position)
        }

        holder.cl4.setOnClickListener {
            eventsListeners.makeFavorite(position)
        }

        holder.cl5.setOnClickListener {
            eventsListeners.openGallery(position)
        }

        holder.cl6.setOnClickListener {
            var bundle = Bundle()
            bundle.putInt("businessId", business.businessData!![position].businessId)
            bundle.putString("businessName", business.businessData!![position].businessName)
            bundle.putString("imageUrl", business.businessData!![position].defaultPictureUrl)
            bundle.putDouble("rate", business.businessData!![position].rating)
            bundle.putInt("count", business.businessData!![position].noOfRatings)
            if (it.findNavController().currentDestination?.id == R.id.categoryBusinessFragment)
                it.findNavController()
                    .navigate(R.id.action_categoryBusinessFragment_to_ratingFragment, bundle)
        }

//        holder.ivProfilePic.setOnClickListener {
//            eventsListeners.viewImage(position, 0)
//        }
//
//        holder.ivBanner.setOnClickListener {
//            eventsListeners.viewImage(position, 1)
//        }

        holder.itemView.setOnClickListener {
            Utils().clickStream(
                context,
                sharedPreferences.getString("USER_ID", "0")!!,
                "16",
                "${business!!.businessData!![position].businessId}",
                "8",
                "${business!!.businessData!![position].businessId}",
                ""
            )
            eventsListeners.closeDialog()
            CategoryBusinessFragment.scrollPage = false
            var bundle = Bundle()
            bundle.putParcelable("data", business!!.businessData!![position])
            if (it.findNavController().currentDestination?.id == R.id.categoryBusinessFragment)
                it.findNavController().navigate(
                    R.id.action_categoryBusinessFragment_to_categoryBusinessDetailsFragment,
                    bundle
                )
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivBanner: ImageView = view.ivBanner
        val ivProfilePic: ImageView = view.ivProfilePic
        val tvTitle: TextView = view.tvTitle
        val tvDistance: TextView = view.tvDistance
        val cl1: ConstraintLayout = view.cl1
        val cl2: ConstraintLayout = view.cl2
        val cl3: ConstraintLayout = view.cl3
        val cl4: ConstraintLayout = view.cl4
        val clFav: ConstraintLayout = view.clFav
        val cl5: ConstraintLayout = view.cl5
        val cl6: ConstraintLayout = view.cl6
        val tvRatingValue: TextView = view.tvRatingValue
        val cv6: MaterialCardView = view.cv6
        val ivRatingStar: ImageView = view.ivRatingStar
        val ivFavorite: ImageView = view.ivFavorite
        val recyclerViewProductsItems: RecyclerView = view.recyclerViewProductsItems
        val tvOfferCount: TextView = view.tvOfferCount
        val tvProductCount: TextView = view.tvProductCount
        val ivAutoDetectArrow: ImageView = view.ivAutoDetectArrow
        val tvCategory: TextView = view.tvCategory
        val tvMatch: TextView = view.tvMatch
        val tvStatus: TextView = view.tvStatus
        val ivArrow: ImageView = view.ivArrow
        val tvDelivery: ImageView = view.tvDelivery
    }

}