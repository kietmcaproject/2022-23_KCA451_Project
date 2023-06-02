package com.app.pepens.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.app.pepens.R
import com.app.pepens.activity.MainActivity
import com.app.pepens.model.Ads.Ads
import com.app.pepens.model.LogData
import com.app.pepens.utils.Urls
import com.app.pepens.utils.Utils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.custom_ads_layout.view.*

/**
 * Created by Vikas Kumar Singh on 10/07/20.
 */

class AdsPagerAdapter : RecyclerView.Adapter<AdsPagerAdapter.ViewHolder> {

    private var context: Context
    private var ads: Ads

    constructor(context: Context, ads: Ads) : super() {
        this.context = context
        this.ads = ads
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.custom_ads_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (ads!!.adsData == null) {
            0
        } else {
            ads!!.adsData!!.count()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var sharedPreferences = context!!.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        Glide.with(context!!)
            .load(ads!!.adsData!![position].coverPictureUrl)
            .into(holder.ivImage)

        holder.itemView.setOnClickListener {
            if (ads!!.adsData!![position].linkTypeId == 1) {

                when (Navigation.findNavController(it).currentDestination?.id) {
                    R.id.homeFragment -> {
                        Utils().updatePagesLog(
                            context,
                            LogData(
                                areaId = MainActivity.login!!.loginData!!.areaId,
                                pageId = Urls.HOME_PAGE,
                                title = "${ads!!.adsData!![position].title}",
                                eventName = "Ad Click",
                                pageEntityId = "${ads!!.adsData!![position].entityId}"
                            )
                        )
                    }
                    R.id.shoppingFragment -> {
                        Utils().updatePagesLog(
                            context,
                            LogData(
                                areaId = MainActivity.login!!.loginData!!.areaId,
                                pageId = Urls.SHOPPING_PAGE,
                                title = "${ads!!.adsData!![position].title}",
                                eventName = "Ad Click",
                                pageEntityId = "${ads!!.adsData!![position].entityId}"
                            )
                        )
                    }
                    R.id.restaurantsFragment -> {
                        Utils().updatePagesLog(
                            context,
                            LogData(
                                areaId = MainActivity.login!!.loginData!!.areaId,
                                pageId = Urls.RESTAURANT_PAGE,
                                title = "${ads!!.adsData!![position].title}",
                                eventName = "Ad Click",
                                pageEntityId = "${ads!!.adsData!![position].entityId}"
                            )
                        )
                    }
                }

                when (ads!!.adsData!![position].entityType) {
                    1 -> {
                        Utils().clickStream(
                            context,
                            sharedPreferences.getString("USER_ID", "0")!!,
                            "16",
                            "${ads!!.adsData!![position].adId}",
                            "2",
                            "${ads!!.adsData!![position].adId}",
                            ""
                        )
                        var bundle = Bundle()
                        bundle.putInt("businessId", ads!!.adsData!![position].entityId)
                        bundle.putString("businessName", "")
                        when (Navigation.findNavController(it).currentDestination?.id) {
                            R.id.homeFragment -> {
                                it.findNavController().navigate(
                                    R.id.action_homeFragment_to_categoryBusinessDetailsFragment,
                                    bundle
                                )
                            }
                            R.id.shoppingFragment -> {
                                it.findNavController().navigate(
                                    R.id.action_shoppingFragment_to_categoryBusinessDetailsFragment,
                                    bundle
                                )
                            }
                            R.id.restaurantsFragment -> {
                                it.findNavController().navigate(
                                    R.id.action_restaurantsFragment_to_categoryBusinessDetailsFragment,
                                    bundle
                                )
                            }
                        }
                    }
                    2 -> {
                        Utils().clickStream(
                            context,
                            sharedPreferences.getString("USER_ID", "0")!!,
                            "7",
                            "${ads!!.adsData!![position].adId}",
                            "2",
                            "${ads!!.adsData!![position].adId}",
                            ""
                        )
                        var bundle = Bundle()
                        bundle.putInt("offerId", ads!!.adsData!![position].entityId)
                        when (Navigation.findNavController(it).currentDestination?.id) {
                            R.id.homeFragment -> {
                                it.findNavController()
                                    .navigate(
                                        R.id.action_homeFragment_to_offerDetailsFragment,
                                        bundle
                                    )
                            }
                            R.id.shoppingFragment -> {
                                it.findNavController()
                                    .navigate(
                                        R.id.action_shoppingFragment_to_offerDetailsFragment,
                                        bundle
                                    )
                            }
                            R.id.restaurantsFragment -> {
                                it.findNavController()
                                    .navigate(
                                        R.id.action_restaurantsFragment_to_offerDetailsFragment,
                                        bundle
                                    )
                            }
                        }
                    }
                    3 -> {
                        Utils().clickStream(
                            context,
                            sharedPreferences.getString("USER_ID", "0")!!,
                            "6",
                            "${ads!!.adsData!![position].adId}",
                            "2",
                            "${ads!!.adsData!![position].adId}",
                            ""
                        )
                        var bundle = Bundle()
                        bundle.putInt("id", ads!!.adsData!![position].entityId)
                        when (Navigation.findNavController(it).currentDestination?.id) {
                            R.id.homeFragment -> {
                                it.findNavController()
                                    .navigate(
                                        R.id.action_homeFragment_to_feedDetailsFragment,
                                        bundle
                                    )
                            }
                            R.id.shoppingFragment -> {
                                it.findNavController()
                                    .navigate(
                                        R.id.action_shoppingFragment_to_feedDetailsFragment,
                                        bundle
                                    )
                            }
                            R.id.restaurantsFragment -> {
                                it.findNavController()
                                    .navigate(
                                        R.id.action_restaurantsFragment_to_feedDetailsFragment,
                                        bundle
                                    )
                            }
                        }
                    }
                    4 -> {
                        when (ads!!.adsData!![position].entityId) {
                            1 -> {

                            }
                            2 -> {
                                when (Navigation.findNavController(it).currentDestination?.id) {
                                    R.id.homeFragment -> {
                                        it.findNavController()
                                            .navigate(R.id.action_homeFragment_to_referralFragment)
                                    }
                                    R.id.shoppingFragment -> {
                                        it.findNavController()
                                            .navigate(R.id.action_shoppingFragment_to_referralFragment)
                                    }
                                    R.id.restaurantsFragment -> {
                                        it.findNavController()
                                            .navigate(R.id.action_restaurantsFragment_to_referralFragment)
                                    }
                                }
                            }
                            3 -> {
                                when (Navigation.findNavController(it).currentDestination?.id) {
                                    R.id.homeFragment -> {
                                        it.findNavController()
                                            .navigate(R.id.action_homeFragment_to_rewardsFragment)
                                    }
                                    R.id.shoppingFragment -> {
                                        it.findNavController()
                                            .navigate(R.id.action_shoppingFragment_to_rewardsFragment)
                                    }
                                    R.id.restaurantsFragment -> {
                                        it.findNavController()
                                            .navigate(R.id.action_restaurantsFragment_to_rewardsFragment)
                                    }
                                }
                            }
                            4 -> {
                                when (Navigation.findNavController(it).currentDestination?.id) {
                                    R.id.homeFragment -> {
                                        it.findNavController()
                                            .navigate(R.id.action_homeFragment_to_shoppingFragment)
                                    }
                                    R.id.shoppingFragment -> {

                                    }
                                    R.id.restaurantsFragment -> {
                                        it.findNavController()
                                            .navigate(R.id.action_restaurantsFragment_to_shoppingFragment)
                                    }
                                }
                            }
                        }
                    }
                    5 -> {
                        if (ads!!.adsData!![position].entityId == 0) {
                            if (!MainActivity.adFromR) {
                                when (Navigation.findNavController(it).currentDestination?.id) {
                                    R.id.homeFragment -> {
                                        it.findNavController()
                                            .navigate(R.id.action_homeFragment_to_restaurantsFragment)
                                    }
                                    R.id.shoppingFragment -> {
                                        it.findNavController()
                                            .navigate(R.id.action_shoppingFragment_to_restaurantsFragment)
                                    }
                                    R.id.restaurantsFragment -> {

                                    }
                                }
                            }
                        } else {
                            var bundle = Bundle()
                            bundle.putInt("businessId", ads!!.adsData!![position].entityId)
                            bundle.putString("businessName", "")
                            bundle.putInt("itemId", 0)
                            when (Navigation.findNavController(it).currentDestination?.id) {
                                R.id.homeFragment -> {
                                    it.findNavController().navigate(
                                        R.id.action_homeFragment_to_restaurantDetailsFragment,
                                        bundle
                                    )
                                }
                                R.id.shoppingFragment -> {
                                    it.findNavController().navigate(
                                        R.id.action_shoppingFragment_to_restaurantDetailsFragment,
                                        bundle
                                    )
                                }
                                R.id.restaurantsFragment -> {
                                    it.findNavController().navigate(
                                        R.id.action_restaurantsFragment_to_restaurantDetailsFragment,
                                        bundle
                                    )
                                }
                            }
                        }
                    }
                }
            } else if (ads!!.adsData!![position].linkTypeId == 2) {
                val defaultBrowser = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
                defaultBrowser.data = Uri.parse(ads!!.adsData!![position].linkUrl)
                context!!.startActivity(defaultBrowser)
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivImage: ImageView = view.ivImage
    }

}