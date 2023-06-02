package com.app.pepens.adapter

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.app.pepens.R
import com.app.pepens.activity.MainActivity
import com.app.pepens.fragment.BusinessProductsFragment
import com.app.pepens.fragment.CategoryBusinessDetailsFragment
import com.app.pepens.model.Shopping.ShoppingItem.ShoppingItem
import com.app.pepens.utils.Utils
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.custom_shopping_product_2_layout.view.*
import java.math.RoundingMode

class BusinessProductsAdapter :
    RecyclerView.Adapter<BusinessProductsAdapter.ViewHolder> {

    private val context: Context
    private val shoppingItem: ShoppingItem
    private val clickEvent: ClickEvent

    constructor(
        context: Context,
        shoppingItem: ShoppingItem,
        businessProductsFragment: BusinessProductsFragment
    ) : super() {
        this.context = context
        this.shoppingItem = shoppingItem
        this.clickEvent = businessProductsFragment
    }

    interface ClickEvent {
        fun productClick(position: Int, change: Boolean)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.custom_shopping_product_2_layout, viewGroup, false)
        )
    }

    override fun getItemCount(): Int {
        return if (shoppingItem!!.shoppingItemData == null) {
            0
        } else {
            shoppingItem!!.shoppingItemData!!.size
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        var sharedPreferences = context!!.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        viewHolder.tvTitle.text = shoppingItem!!.shoppingItemData!![position].title
        viewHolder.tvCompany.text = shoppingItem!!.shoppingItemData!![position].brandName
        if (shoppingItem!!.shoppingItemData!![position].discountPercentage > 0 && shoppingItem!!.shoppingItemData!![position].price != shoppingItem!!.shoppingItemData!![position].mRP) {
            viewHolder.tvDiscount.visibility = View.VISIBLE
            viewHolder.ivOffer.visibility = View.VISIBLE
            viewHolder.viewCenter.visibility = View.VISIBLE
            viewHolder.tvSaving.visibility = View.VISIBLE
            viewHolder.tvDiscount.text = "OFF\n${shoppingItem!!.shoppingItemData!![position].discountPercentage}%"
            var mrp = "(${shoppingItem!!.shoppingItemData!![position].mRP})"
            viewHolder.tvPrice.text = Html.fromHtml(
                ("₹${shoppingItem!!.shoppingItemData!![position].price} $mrp").replace(
                    "$mrp",
                    "<s><font color='#ADADAD'>$mrp</font></s>"
                )
            )
            viewHolder.tvSaving.text = Html.fromHtml(
                ("Save ₹${
                    (shoppingItem!!.shoppingItemData!![position].mRP - shoppingItem!!.shoppingItemData!![position].price).toBigDecimal()
                        .setScale(2, RoundingMode.HALF_UP).toDouble()
                }").replace("Save", "<font color='#09AF00'>Save</font>")
            )
        } else {
            viewHolder.tvDiscount.visibility = View.GONE
            viewHolder.ivOffer.visibility = View.GONE
            viewHolder.viewCenter.visibility = View.GONE
            viewHolder.tvSaving.visibility = View.GONE
            viewHolder.tvPrice.text = "Price ₹${shoppingItem!!.shoppingItemData!![position].mRP}"
            viewHolder.tvPrice.setTextColor(ContextCompat.getColor(context, R.color.colorText))
        }
        Glide.with(context)
            .load(shoppingItem!!.shoppingItemData!![position].defaultPictureUrl)
            .placeholder(R.drawable.ic_product_icon)
            .into(viewHolder.ivLogo)

        if (shoppingItem!!.shoppingItemData!![position].inStock) {
            viewHolder.layoutUpdate.visibility = View.VISIBLE
            viewHolder.tvStock.visibility = View.GONE
            viewHolder.btnNotify.visibility = View.GONE
        } else {
            viewHolder.layoutUpdate.visibility = View.GONE
            viewHolder.tvStock.visibility = View.VISIBLE
            viewHolder.btnNotify.visibility = View.VISIBLE
        }

        if (shoppingItem!!.shoppingItemData!![position].cartQty > 0) {
            viewHolder.layoutAdd.visibility = View.GONE
            viewHolder.ivRemove.visibility = View.VISIBLE
            viewHolder.tvCount.visibility = View.VISIBLE
            viewHolder.ivAdd.visibility = View.VISIBLE
            viewHolder.tvCount.text = shoppingItem!!.shoppingItemData!![position].cartQty.toString()
            if (shoppingItem!!.shoppingItemData!![position].cartQty >= shoppingItem!!.shoppingItemData!![position].maxOrderQty) {
                viewHolder.ivAdd.isEnabled = false
//                viewHolder.ivAdd.setColorFilter(
//                    ContextCompat.getColor(context, R.color.grey),
//                    android.graphics.PorterDuff.Mode.SRC_IN
//                )
            } else {
                viewHolder.ivAdd.isEnabled = true
//                viewHolder.ivAdd.setColorFilter(
//                    ContextCompat.getColor(context, R.color.green),
//                    android.graphics.PorterDuff.Mode.SRC_IN
//                )
            }
        } else if (shoppingItem!!.shoppingItemData!![position].cartQty == 0) {
            viewHolder.layoutAdd.visibility = View.VISIBLE
            viewHolder.ivRemove.visibility = View.GONE
            viewHolder.tvCount.visibility = View.GONE
            viewHolder.ivAdd.visibility = View.GONE
        }

        viewHolder.layoutAdd.setOnClickListener {
            Utils().updateProductInFirebase(shoppingItem!!.shoppingItemData!![position].partnerItemId.toString(), shoppingItem!!.shoppingItemData!![position].title!!, context)
            clickEvent.productClick(position, true)
        }

        viewHolder.ivAdd.setOnClickListener {
            Utils().updateProductInFirebase(shoppingItem!!.shoppingItemData!![position].partnerItemId.toString(), shoppingItem!!.shoppingItemData!![position].title!!, context)
            val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.slide_out)
            viewHolder.tvCount.startAnimation(animation)
            Handler().postDelayed({
                clickEvent.productClick(position, true)
            }, 150)
        }

        viewHolder.ivRemove.setOnClickListener {
            val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.slide_remove)
            viewHolder.tvCount.startAnimation(animation)
            Handler().postDelayed({
                clickEvent.productClick(position, false)
            }, 150)
        }

        viewHolder.btnNotify.setOnClickListener {
            viewHolder.btnNotify.isEnabled = false
            Utils().addToWishlist(
                context,
                MainActivity.login!!.loginData!!.userId,
                shoppingItem.shoppingItemData[position].partnerItemId
            )
        }

        viewHolder.itemView.setOnClickListener {
            Utils().clickStream(
                context,
                sharedPreferences.getString("USER_ID", "0")!!,
                "26",
                "${shoppingItem.shoppingItemData[position].partnerItemId}",
                "25",
                "${shoppingItem.shoppingItemData[position].itemCategoryId}",
                ""
            )
            var bundle = Bundle()
            bundle.putString("id", shoppingItem.shoppingItemData[position].partnerItemId.toString())
            bundle.putString("businessId", CategoryBusinessDetailsFragment.businessId.toString())
            bundle.putString("businessName", CategoryBusinessDetailsFragment.businessName)
            bundle.putInt("businessCategoryId", CategoryBusinessDetailsFragment.businessCategoryId!!.toInt())
            bundle.putString("businessUrl", CategoryBusinessDetailsFragment.defaultPictureUrl)
            if (Navigation.findNavController(it).currentDestination?.id == R.id.categoryBusinessDetailsFragment)
                it.findNavController().navigate(R.id.action_categoryBusinessDetailsFragment_to_productDetailFragment, bundle)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivOffer: ImageView = view.ivOffer
        val tvDiscount: TextView = view.tvDiscount
        val ivLogo: ImageView = view.ivLogo
        val tvCompany: TextView = view.tvCompany
        val tvTitle: TextView = view.tvTitle
        val viewCenter: View = view.viewCenter
        val tvPrice: TextView = view.tvPrice
        val tvSaving: TextView = view.tvSaving
        val layoutAdd: ConstraintLayout = view.layoutAdd
        val ivRemove: ImageView = view.ivRemove
        val tvCount: TextView = view.tvCount
        val ivAdd: ImageView = view.ivAdd
        val layoutUpdate: ConstraintLayout = view.layoutUpdate
        val tvStock: TextView = view.tvStock
        val btnNotify: MaterialButton = view.btnNotify
    }
}