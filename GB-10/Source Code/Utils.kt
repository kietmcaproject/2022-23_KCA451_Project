package com.app.pepens.utils

import android.app.Activity
import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.content.res.AssetManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Geocoder
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.app.pepens.R
import com.app.pepens.activity.MainActivity
import com.app.pepens.activity.SplashActivity
import com.app.pepens.adapter.ShareAdapter
import com.app.pepens.model.CartItemsList
import com.app.pepens.model.LogData
import com.app.pepens.model.RestaurantCartList
import com.app.expensemanager.util.VolleySingleton
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.bumptech.glide.Glide
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.dynamiclinks.ktx.*
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.maps.android.SphericalUtil
import org.json.JSONObject
import java.io.InputStream
import java.io.UnsupportedEncodingException
import java.lang.reflect.Type
import java.math.RoundingMode
import java.net.InetAddress
import java.net.NetworkInterface
import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import kotlin.collections.ArrayList
import kotlin.math.*

/**
 * Created by Vikas Kumar Singh on 08/07/20.
 */

class Utils : Activity() {

    fun verifyAvailableNetwork(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun progressDialog(context: Context): Dialog {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.progress_dialog_layout)
        dialog.setCancelable(false)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    fun assetsJsonFile(filename: String?, context: Context): String? {
        val manager: AssetManager = context.assets
        val file: InputStream = manager.open(filename!!)
        val fromArray = ByteArray(file.available())
        file.read(fromArray)
        file.close()
        return String(fromArray)
    }

    fun hideSoftKeyboard() {
        val view = this.currentFocus
        view?.let { v ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.let { it.hideSoftInputFromWindow(v.windowToken, 0) }
        }
    }

    fun showHideKeyboard(show: Boolean, context: Context) {
        if (show) {
            (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?)?.toggleSoftInput(
                InputMethodManager.SHOW_IMPLICIT,
                0
            )
        } else {
            (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?)?.toggleSoftInput(
                0,
                InputMethodManager.HIDE_IMPLICIT_ONLY
            )
        }
    }

    fun getScreenSize(context: Context): Double {
        var windowManager: WindowManager =
            context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        var displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        var x = (displayMetrics.widthPixels / displayMetrics.xdpi).toDouble().pow(2.0)
        var y = (displayMetrics.heightPixels / displayMetrics.ydpi).toDouble().pow(2.0)
        return sqrt(x + y)
    }

    fun distanceBetween(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        var point1 = LatLng(lat1, lon1)
        var point2 = LatLng(lat2, lon2)
        return if (point1 == null || point2 == null) {
            0.0
        } else SphericalUtil.computeDistanceBetween(point1, point2)
    }

    fun getCurrentDateTime(): String {
        var currentTime: String? = null
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        currentTime = dateFormat.format(Date())
        return currentTime.toString()
    }

    fun getTimeZoneName(): String {
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        val zone = calendar.timeZone
        return zone.id
    }

    fun getUtcOffsetSec(): String {
        return System.currentTimeMillis().toString()
    }

    fun getGmtOffsetSec(): String {
        var cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"))
        var time = cal.timeInMillis
        return time.toString()
    }

    fun getGmt(): String {
        var calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault())
        var timeZone = SimpleDateFormat("Z").format(calendar.time)
        return timeZone.substring(0, 3) + ":" + timeZone.substring(3, 5)
    }

    fun getUtc(): String {
        var calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"), Locale.getDefault())
        var timeZone = SimpleDateFormat("Z").format(calendar.time)
        return timeZone.substring(0, 3) + ":" + timeZone.substring(3, 5)
    }

    fun convertDate(createdDate: String): String {
        var newDate: String? = null
        var dateFormat: SimpleDateFormat? = null
        dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm")
        val dateFormat2 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        val oldDate = dateFormat.parse(createdDate)
        newDate = dateFormat2.format(oldDate)
        return newDate.toString()
    }

    fun getDate(createdDate: String): String {
        var newDate: String? = null
        var dateFormat: SimpleDateFormat? = null
        dateFormat = if (createdDate.length < 22) {
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        } else {
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        }
        val dateFormat2 = SimpleDateFormat("d MMM yyyy")
        val oldDate = dateFormat.parse(createdDate)
        newDate = dateFormat2.format(oldDate)
        return newDate.toString()
    }

//    var newDate: String? = null
//    var dateFormat: SimpleDateFormat? = null
//    dateFormat = if (createdDate.length < 22) {
//        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
//    } else {
//        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
//    }
//    val dateFormat2 = SimpleDateFormat("dd/MM/yyyy hh:mm a")
//    val oldDate = dateFormat.parse(createdDate)
//    newDate = dateFormat2.format(oldDate)
//    return newDate.toString()

    fun getDateTimeWithTimezone(createdDate: String): String {
        var newDate: String? = null
        var dateFormat: SimpleDateFormat? = null
        dateFormat = if (createdDate.length < 22) {
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        } else {
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        }
        dateFormat.timeZone = TimeZone.getTimeZone("GMT+4")
        val oldDate = dateFormat.parse(createdDate)
        val dateFormat2 = SimpleDateFormat("dd/MM/yyyy hh:mm a")
        dateFormat2.timeZone = TimeZone.getDefault()
        newDate = dateFormat2.format(oldDate)
        return newDate.toString()
    }

    fun getDateTime(createdDate: String): String {
        var newDate: String? = null
        var dateFormat: SimpleDateFormat? = null
        dateFormat = if (createdDate.length < 22) {
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        } else {
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        }
        val oldDate = dateFormat.parse(createdDate)
        val dateFormat2 = SimpleDateFormat("dd/MM/yyyy hh:mm a")
        dateFormat2.timeZone = TimeZone.getDefault()
        newDate = dateFormat2.format(oldDate)
        return newDate.toString()
    }

    fun getHours(date: String): String {
        var newDate: String? = null
        val of = SimpleDateFormat("hh:mm a").parse(date)
        var nf = SimpleDateFormat("HH:mm").format(of)
        val oldDate = SimpleDateFormat("HH").parse(nf)
        newDate = SimpleDateFormat("HH").format(oldDate)
        return newDate.toString()
    }

    fun matchTime(dateStart: String, dateEnd: String): Int {
        var dateFormat = if (dateStart.length < 22) {
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        } else {
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        }
        val dateFormat2 = SimpleDateFormat("hh:mm a")
        dateFormat2.timeZone = TimeZone.getDefault()
        val oldDate = dateFormat.parse(dateStart)
        val oldDate1 = dateFormat.parse(dateEnd)
        var newDate = dateFormat2.format(oldDate)
        var newDate1 = dateFormat2.format(oldDate1)
        var date1 = dateFormat2.parse(newDate)
        var date2 = dateFormat2.parse(newDate1)
        val difference: Long = date2.time - date1.time
        var days = (difference / (1000 * 60 * 60 * 24)).toInt()
        var hours = ((difference - 1000 * 60 * 60 * 24 * days) / (1000 * 60 * 60))
        var min = (difference - 1000 * 60 * 60 * 24 * days - 1000 * 60 * 60 * hours) / (1000 * 60)
        hours = if (hours < 0) -hours else hours
        Log.e("TAG", "matchTime: $days days $hours hours $min min")
        return days.toInt()
    }

    fun matchDay(dateStart: String, dateEnd: String): Int {
        var dateFormat = if (dateStart.length < 22) {
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        } else {
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        }
        val dateFormat2 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        dateFormat2.timeZone = TimeZone.getDefault()
        val oldDate = dateFormat.parse(dateStart)
        val oldDate1 = dateFormat.parse(dateEnd)
        var newDate = dateFormat2.format(oldDate)
        var newDate1 = dateFormat2.format(oldDate1)
        var date1 = dateFormat2.parse(newDate)
        var date2 = dateFormat2.parse(newDate1)
        val difference: Long = date2.time - date1.time
        var days = (difference / (1000 * 60 * 60 * 24)).toInt()
        var hours = ((difference - 1000 * 60 * 60 * 24 * days) / (1000 * 60 * 60))
        var min = (difference - 1000 * 60 * 60 * 24 * days - 1000 * 60 * 60 * hours) / (1000 * 60)
        hours = if (hours < 0) -hours else hours
        Log.e("TAG", "matchTime: $days days $hours hours $min min")
        return days.toInt()
    }

    fun getDatesDiff(firstDate: String): Long {
        var dateFormat = SimpleDateFormat("hh:mm a")
        var fDate = dateFormat.parse(firstDate)
        var formatedDate = dateFormat.format(Date())
        var currentDate = dateFormat.parse(formatedDate)
        var diff = fDate.time - currentDate.time
        var seconds = diff / 1000
        var minutes = seconds / 60
        return minutes
    }

    fun getLatLongFromAddress(address: String, context: Context): LatLng {
        var geoCoder = Geocoder(context)
        var p1: LatLng? = null
        if (!address.isNullOrEmpty()) {
            p1 = try {
                var addressList = geoCoder.getFromLocationName(address, 1)
                if (addressList != null && addressList.size > 0) {
                    var lat = addressList[0].latitude
                    var lang = addressList[0].longitude
                    LatLng(lat, lang)
                } else {
                    LatLng(0.0, 0.0)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                LatLng(0.0, 0.0)
            }
        } else {
            p1 = LatLng(0.0, 0.0)
        }
        return p1!!
    }

    fun getMobileIPAddress(): String? {
        try {
            val interfaces: List<NetworkInterface> =
                Collections.list(NetworkInterface.getNetworkInterfaces())
            for (intf in interfaces) {
                val addrs: List<InetAddress> = Collections.list(intf.inetAddresses)
                for (addr in addrs) {
                    if (!addr.isLoopbackAddress) {
                        return addr.hostAddress
                    }
                }
            }
        } catch (ex: Exception) {
        } // for now eat exceptions
        return ""
    }

    fun clickStream(
        context: Context,
        userId: String,
        clickStreamTargetId: String,
        targetEntityId: String,
        clickStreamSourceId: String,
        sourceEntityId: String,
        note: String
    ) {
        val jsonObject = JSONObject()
        jsonObject.put("UserId", userId)
        jsonObject.put("ClickStreamTargetId", clickStreamTargetId)
        jsonObject.put("TargetEntityId", targetEntityId)
        jsonObject.put("ClickStreamSourceId", clickStreamSourceId)
        jsonObject.put("SourceEntityId", sourceEntityId)
        jsonObject.put("Note", note)
        val request = JsonObjectRequest(Request.Method.POST, Urls.UPDATE_STREAM, jsonObject,
            { response ->
            },
            {
            })
        request.retryPolicy = DefaultRetryPolicy(10000, 0, 1f)
        VolleySingleton.getInstance(context).addToRequestQueue(request)
    }

    fun updateItemInCart(context: Context, cartArrayList: ArrayList<CartItemsList>) {
        var sharedPreferences = context!!.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        var editor = sharedPreferences.edit()
        var json = Gson().toJson(cartArrayList)
        editor.putString("cart", json)
        editor.apply()
    }

    fun getItemsFromCart(context: Context): ArrayList<CartItemsList> {
        var sharedPreferences = context!!.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        var json = sharedPreferences.getString("cart", null)
        var cartArrayList: ArrayList<CartItemsList> = ArrayList()
        if (json != null) {
            val type: Type = object : TypeToken<ArrayList<CartItemsList?>?>() {}.type
            cartArrayList = Gson().fromJson(json, type)
            if (cartArrayList == null) {
                cartArrayList = ArrayList()
            }
        }
        return cartArrayList
    }

    fun updateRestaurantInCart(context: Context, cartArrayList: ArrayList<RestaurantCartList>) {
        var sharedPreferences = context!!.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        var editor = sharedPreferences.edit()
        var json = Gson().toJson(cartArrayList)
        editor.putString("RestaurantCart", json)
        editor.apply()
    }

    fun getRestaurantFromCart(context: Context): ArrayList<RestaurantCartList> {
        var sharedPreferences = context!!.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        var json = sharedPreferences.getString("RestaurantCart", null)
        var cartArrayList: ArrayList<RestaurantCartList> = ArrayList()
        if (json != null) {
            val type: Type = object : TypeToken<ArrayList<RestaurantCartList?>?>() {}.type
            cartArrayList = Gson().fromJson(json, type)
            if (cartArrayList == null) {
                cartArrayList = ArrayList()
            }
        }
        return cartArrayList
    }

    fun updateRestaurantItemInServerCart(
        context: Context,
        userId: String,
        cartQty: Int,
        variantId: String,
        businessShoppingItemId: String,
        businessId: String
    ) {
        val jsonObject = JSONObject()
        jsonObject.put("UserId", userId)
        jsonObject.put("PartnerItemId", "0")
        jsonObject.put("Quantity", cartQty)
        jsonObject.put("VariantId", variantId)
        jsonObject.put("BusinessShoppingItemId", businessShoppingItemId)
        jsonObject.put("BusinessId", businessId)
        jsonObject.put("BusinessCategoryId", Urls.ID_RESTAURANT_CATEGORY)
        val request = JsonObjectRequest(
            Request.Method.POST,
            Urls.UPDATE_SHOPPING_CART,
            jsonObject,
            { response ->
                Log.e("TAG", "updateRestaurantItemInServerCart: $response")
            },
            {
                Log.e("TAG", "updateRestaurantItemInServerCart: $it")
            })
        request.retryPolicy = DefaultRetryPolicy(10000, 0, 1f)
        VolleySingleton.getInstance(context).addToRequestQueue(request)
    }

    fun updateLog(
        context: Context,
        userId: String,
        exceptionTime: String,
        exceptionHeading: String,
        exceptionDesc: String
    ) {
        val jsonObject = JSONObject()
        jsonObject.put("EntityType", 4)
        jsonObject.put("EntityId", userId)
        jsonObject.put("ExeptionOn", exceptionTime)
        jsonObject.put("ExeptionHeading", exceptionHeading)
        jsonObject.put("ExeptionDesc", exceptionDesc)
        val request =
            JsonObjectRequest(Request.Method.POST, Urls.UPDATE_LOG, jsonObject, { response ->
//                Log.e("TAG", "updateLog: $response")
            },
                {
//                    Log.e("TAG", "updateLog: $it")
                })
        request.retryPolicy = DefaultRetryPolicy(10000, 0, 1f)
        VolleySingleton.getInstance(context).addToRequestQueue(request)
    }

    fun addToWishlist(context: Context, userId: Int, partnerItemId: Int) {
        val jsonObject = JSONObject()
        jsonObject.put("UserId", userId)
        jsonObject.put("PartnerItemId", partnerItemId)
        val request = JsonObjectRequest(Request.Method.POST, Urls.ADD_TO_WISHLIST, jsonObject,
            { response ->
                Log.e("TAG", "addToWishlist: $response")
            },
            {
                Log.e("TAG", "addToWishlist: $it")
            })
        request.retryPolicy = DefaultRetryPolicy(10000, 0, 1f)
        VolleySingleton.getInstance(context).addToRequestQueue(request)
    }

    fun getQueryString(url: String?, tag: String?): String? {
        try {
            val uri: Uri = Uri.parse(url)
            return uri.getQueryParameter(tag)
        } catch (e: java.lang.Exception) {
            Log.e("TAG", "getQueryString() " + e.message)
        }
        return ""
    }

    fun appShare(context: Context, fromScreen: String, msg: String) {
        var progressDialog = Utils().progressDialog(context)
        progressDialog.show()
        var sharedPreferences = context.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        Utils().clickStream(
            context,
            sharedPreferences.getString("USER_ID", "0")!!,
            "0",
            "0",
            "48",
            "0",
            "From: $fromScreen"
        )
        val dynamicLink = Firebase.dynamicLinks.dynamicLink {
            link = Uri.parse("https://play.google.com/store/apps/details?id=com.app.pepens&refer=${MainActivity.login!!.loginData!!.referralCode}")
            domainUriPrefix = "https://dl.a2zsearch.net"
            androidParameters {
                "com.app.pepens"
            }
            socialMetaTagParameters {
                title = "Pepens"
                description = if (msg.isEmpty()) {
                    "${sharedPreferences.getString(
                            "NAME",
                            null
                        )
                    } invited you to Download Pepens. Using this link you get 50 Reward Points(worth Rs. 5)."
                } else {
                    "$msg\n${
                        sharedPreferences.getString(
                            "NAME",
                            null
                        )
                    } invited you to Download Pepens. Using this link you get 50 Reward Points(worth Rs. 5)."
                }
                imageUrl =
                    Uri.parse("https://www.a2zsearch.net/assets/images/a2z-search-logo-top.png")
            }
            googleAnalyticsParameters {
                source = "Pepens"
                medium = "$fromScreen"
                campaign = "${MainActivity.login!!.loginData!!.referralCode}"
            }
        }
        val dynamicLinkUri = dynamicLink.uri
        Log.e("TAG", "shareLink: $dynamicLinkUri")
        val shortLinkTask = Firebase.dynamicLinks.shortLinkAsync {
            longLink = dynamicLinkUri
        }.addOnSuccessListener { (shortLink, flowchartLink) ->
            progressDialog.dismiss()
            Log.e("TAG", "onClick: $shortLink")
            var intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name))
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "${
                    sharedPreferences.getString(
                        "NAME",
                        null
                    )
                } invited you to Download Pepens. Using this link you get 50 Reward Points(worth Rs. 5).\n$shortLink"
            )
            context.startActivity(Intent.createChooser(intent, "Pepens"))
        }.addOnFailureListener {
            progressDialog.dismiss()
            Log.e("TAG", "onClick: ${it.message}")
        }
    }

    fun capitalizeString(str: String): String {
        var retStr = str
        try {
            retStr = str.substring(0, 1).toUpperCase() + str.substring(1)
        } catch (e: Exception) {
        }
        return retStr
    }

    fun distance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val theta = lon1 - lon2
        var dist =
            (sin(deg2rad(lat1)) * sin(deg2rad(lat2)) + (cos(deg2rad(lat1)) * cos(deg2rad(lat2)) * cos(
                deg2rad(theta)
            )))
        dist = acos(dist)
        dist = rad2deg(dist)
        dist *= 60 * 1.1515
        dist *= 1.168
        return dist.toBigDecimal().setScale(2, RoundingMode.HALF_UP).toDouble()
    }

    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    private fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }

    fun getDirectionsUrl(origin: LatLng, dest: LatLng): String {
        val str_origin = "origin=" + origin.latitude + "," + origin.longitude
        val str_dest = "destination=" + dest.latitude + "," + dest.longitude
        val sensor = "sensor=false"
        val mode = "mode=driving"
        val parameters =
            "$str_origin&$str_dest&$sensor&$mode&key=AIzaSyBCgM9tjvcQGAYZCLY0H451bjS5lopnNIc"
        val output = "json"
        return "https://maps.googleapis.com/maps/api/directions/$output?$parameters"
    }

    fun volleyError(it: VolleyError?, context: Context) {
        if (context != null) {
            when (it) {
                is NetworkError -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.no_internet),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NoConnectionError -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.no_internet),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is TimeoutError -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.no_internet),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    Toast.makeText(
                        context,
                        "Something went wrong, please try again.($it)",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    fun restartAppAtCrash(context: Context, simpleName: String) {
        var onRuntimeError: Thread.UncaughtExceptionHandler =
            Thread.UncaughtExceptionHandler { t, e ->
                Log.e(simpleName, "Exception: $e")
                var intent = Intent(context, SplashActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish()
                Runtime.getRuntime().exit(0)
            }
        Thread.setDefaultUncaughtExceptionHandler(onRuntimeError)
    }

    fun updateViewPagerAds(viewPager: ViewPager2, context: Context, size: Int) {
        viewPager.offscreenPageLimit = 1
        var nextItemVisiblePx = context.resources.getDimension(R.dimen.fourteen_dp)
        var currentItemHorizontalMarginPx = context.resources.getDimension(R.dimen.fourteen_dp)
        var itemDecoration = HorizontalMarginItemDecoration(context, R.dimen.fourteen_dp)
        if (size > 1) {
            nextItemVisiblePx = context.resources.getDimension(R.dimen.eight_dp)
            currentItemHorizontalMarginPx = context.resources.getDimension(R.dimen.fourteen_dp)
            itemDecoration = HorizontalMarginItemDecoration(context, R.dimen.fourteen_dp)
        } else {
            nextItemVisiblePx = context.resources.getDimension(R.dimen.four_dp)
            currentItemHorizontalMarginPx = context.resources.getDimension(R.dimen.eight_dp)
            itemDecoration = HorizontalMarginItemDecoration(context, R.dimen.eight_dp)
        }
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
//            page.scaleY = 1 - (0.06f * abs(position))
        }
        viewPager.setPageTransformer(pageTransformer)
        for (i in 0 until viewPager.itemDecorationCount) {
            viewPager.removeItemDecorationAt(i)
        }
        viewPager.addItemDecoration(itemDecoration)
    }

    fun getHeight(context: Context): Int {
        var displayMetrics = DisplayMetrics()
        (context as FragmentActivity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        var width = displayMetrics.widthPixels
        return (width / 2.33).toInt()
    }

    fun updateProductInFirebase(id: String, name: String, context: Context) {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id)
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name)
        FirebaseAnalytics.getInstance(context)
            .logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }

    fun updatePagesLog(context: Context, logData: LogData) {
        var sharedPreferences = context.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        val jsonObject = JSONObject()
        jsonObject.put("AreaId", logData.areaId)
        jsonObject.put("OSType", "1")
        jsonObject.put("PageId", logData.pageId)
        jsonObject.put("EntityType", "4")
        jsonObject.put("EntityId", sharedPreferences.getString("USER_ID", "0"))
        jsonObject.put("Title", logData.title)
        jsonObject.put("EventName", logData.eventName)
        jsonObject.put("PageEntityId", logData.pageEntityId)
        jsonObject.put("Info", logData.info)
        jsonObject.put("Note", logData.note)
        Log.e("TAG", "updatePagesLog: $jsonObject")
//        val request = JsonObjectRequest(
//            Request.Method.POST,
//            Urls.ADD_UPDATE_APP_LOG,
//            jsonObject,
//            { response ->
////                Log.e("TAG", "updatePagesLog: $response")
//            }, {
////                Log.e("TAG", "updatePagesLog: $it")
//            })
//        request.retryPolicy = DefaultRetryPolicy(10000, 0, 1f)
//        VolleySingleton.getInstance(context).addToRequestQueue(request)
    }

    fun clearCart(context: Context, businessCategoryId: String, businessId: String) {
        var sharedPreferences = context.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        val request = StringRequest(
            Request.Method.GET,
            "${
                Urls.CLEAR_CART + sharedPreferences.getString(
                    "USER_ID",
                    "0"
                )
            }${Urls.BUSINESS_CATEGORY_ID}$businessCategoryId${Urls.BUSINESS_ID}$businessId",
            { response ->
//                Log.e("TAG", "clearCart: $response")
                when (businessCategoryId) {
                    "0" -> {
                        var cartArrayList: ArrayList<CartItemsList> = ArrayList()
                        cartArrayList.clear()
                        Utils().updateItemInCart(context, cartArrayList)
                        var restaurantCartList: ArrayList<RestaurantCartList> = ArrayList()
                        restaurantCartList.clear()
                        Utils().updateRestaurantInCart(context, restaurantCartList)
                    }
                    "${Urls.ID_GROCERY_CATEGORY}" -> {
                        var cartArrayList: ArrayList<CartItemsList> = ArrayList()
                        cartArrayList.clear()
                        Utils().updateItemInCart(context, cartArrayList)
                    }
                    "${Urls.ID_RESTAURANT_CATEGORY}" -> {
                        var restaurantCartList: ArrayList<RestaurantCartList> = ArrayList()
                        restaurantCartList.clear()
                        Utils().updateRestaurantInCart(context, restaurantCartList)
                    }
                }
            },
            {
//                Log.e("TAG", "clearCart: $it")
            })
        request.retryPolicy = DefaultRetryPolicy(10000, 2, 1f)
        VolleySingleton.getInstance(context).addToRequestQueue(request)
    }

//    fun encrypt(strToEncrypt: String, secret_key: String): String? {
//        Security.addProvider(BouncyCastleProvider())
//        var keyBytes: ByteArray
//
//        try {
//            keyBytes = secret_key.toByteArray(charset("UTF8"))
//            val skey = SecretKeySpec(keyBytes, "AES")
//            val input = strToEncrypt.toByteArray(charset("UTF8"))
//
//            synchronized(Cipher::class.java) {
//                val cipher = Cipher.getInstance("PKCS7Padding")//AES/ECB/PKCS7Padding
//                cipher.init(Cipher.ENCRYPT_MODE, skey)
//
//                val cipherText = ByteArray(cipher.getOutputSize(input.size))
//                var ctLength = cipher.update(
//                    input, 0, input.size,
//                    cipherText, 0
//                )
//                ctLength += cipher.doFinal(cipherText, ctLength)
//                return String(org.bouncycastle.util.encoders.Base64.encode(cipherText))
//            }
//        } catch (uee: UnsupportedEncodingException) {
//            uee.printStackTrace()
//        } catch (ibse: IllegalBlockSizeException) {
//            ibse.printStackTrace()
//        } catch (bpe: BadPaddingException) {
//            bpe.printStackTrace()
//        } catch (ike: InvalidKeyException) {
//            ike.printStackTrace()
//        } catch (nspe: NoSuchPaddingException) {
//            nspe.printStackTrace()
//        } catch (nsae: NoSuchAlgorithmException) {
//            nsae.printStackTrace()
//        } catch (e: ShortBufferException) {
//            e.printStackTrace()
//        }
//
//        return null
//    }

//    fun decryptWithAES(key: String, strToDecrypt: String?): String? {
//        Security.addProvider(BouncyCastleProvider())
//        var keyBytes: ByteArray
//
//        try {
//            keyBytes = key.toByteArray(charset("UTF8"))
//            val skey = SecretKeySpec(keyBytes, "AES")
//            val input = org.bouncycastle.util.encoders.Base64
//                .decode(strToDecrypt?.trim { it <= ' ' }?.toByteArray(charset("UTF8")))
//
//            synchronized(Cipher::class.java) {
//                val cipher = Cipher.getInstance("AES/ECB/PKCS7Padding")//AES/ECB/PKCS7Padding
//                cipher.init(Cipher.DECRYPT_MODE, skey)
//
//                val plainText = ByteArray(cipher.getOutputSize(input.size))
//                var ptLength = cipher.update(input, 0, input.size, plainText, 0)
//                ptLength += cipher.doFinal(plainText, ptLength)
//                val decryptedString = String(plainText)
//                return decryptedString.trim { it <= ' ' }
//            }
//        } catch (uee: UnsupportedEncodingException) {
//            uee.printStackTrace()
//        } catch (ibse: IllegalBlockSizeException) {
//            ibse.printStackTrace()
//        } catch (bpe: BadPaddingException) {
//            bpe.printStackTrace()
//        } catch (ike: InvalidKeyException) {
//            ike.printStackTrace()
//        } catch (nspe: NoSuchPaddingException) {
//            nspe.printStackTrace()
//        } catch (nsae: NoSuchAlgorithmException) {
//            nsae.printStackTrace()
//        } catch (e: ShortBufferException) {
//            e.printStackTrace()
//        }
//
//        return null
//    }


    private val ENCODING = "UTF-8"
    private val TRANSFORMATION = "AES/CBC/PKCS5Padding"
    private val AES = "AES"


    fun doEncryptedAES(msj: String, key: String?): String? {
        var msjEncrypted: String? = "error_encrypted"
        var msjEncryptedbyte: ByteArray? = null
        var keyByte: ByteArray? = null
        val cp: Cipher
        var sks: SecretKeySpec? = null
        var ips: IvParameterSpec? = null
        try {
            msjEncryptedbyte = msj.toByteArray(StandardCharsets.UTF_8)
            keyByte = getKeyBytes(key!!)
        } catch (e: NullPointerException) {
            println(e.message)
            return msjEncrypted
        } catch (e: UnsupportedEncodingException) {
            println(e.message)
            return msjEncrypted
        }
        sks = SecretKeySpec(keyByte, AES)
        ips = IvParameterSpec(keyByte)
        return try {
            cp = Cipher.getInstance(TRANSFORMATION)
            cp.init(Cipher.ENCRYPT_MODE, sks, ips)
            msjEncryptedbyte = cp.doFinal(msjEncryptedbyte)
            msjEncrypted =
                String(android.util.Base64.encode(msjEncryptedbyte, android.util.Base64.DEFAULT))
            msjEncrypted
        } catch (e: Exception) {
            Log.e("TAG", "doEncryptedAES: ${e.message}")
            msjEncrypted
        }
    }

    fun doDecryptedAES(msjEncrypted: String, key: String?): String? {
        var msjDecrypted = "error_decrypted"
        var msjEncryptedByte: ByteArray?
        val keyByte: ByteArray
        try {
            msjEncryptedByte = android.util.Base64.decode(
                msjEncrypted.toByteArray(StandardCharsets.UTF_8),
                android.util.Base64.DEFAULT
            )
            keyByte = getKeyBytes(key!!)
        } catch (e: Exception) {
            println(e.message)
            return msjDecrypted
        }
        val sks = SecretKeySpec(keyByte, AES)
        val ips = IvParameterSpec(keyByte)
        return try {
            val cp = Cipher.getInstance(TRANSFORMATION)
            cp.init(Cipher.DECRYPT_MODE, sks, ips)
            msjEncryptedByte = cp.doFinal(msjEncryptedByte)
            msjDecrypted = String(msjEncryptedByte, StandardCharsets.UTF_8)
            msjDecrypted
        } catch (e: Exception) {
            println(e.message)
            msjDecrypted
        }
    }

    private fun getKeyBytes(key: String): ByteArray {
        val keyBytes = ByteArray(16)
        try {
            val parameterKeyBytes: ByteArray = key.toByteArray(StandardCharsets.UTF_8)
            System.arraycopy(
                parameterKeyBytes,
                0,
                keyBytes,
                0,
                Math.min(parameterKeyBytes.size, keyBytes.size)
            )
        } catch (e: UnsupportedEncodingException) {
            println("[Error][AES][getKeyBytes][0]: " + e.message)
        }
        return keyBytes
    }

    fun createShareDialog(context: Context, businessId: Int, businessName: String, businessImageUrl: String, shareLink: String) {
        var sharedPreferences = context.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        val pm: PackageManager = context.packageManager
        val mainIntent = Intent(Intent.ACTION_SEND, null)
        mainIntent.type = "text/plain"
        val resolveInfo = pm.queryIntentActivities(mainIntent, 0).distinctBy { item -> item.activityInfo.packageName }.toArrayList()
        var appsList: ArrayList<ResolveInfo?> = ArrayList()
        var sampleList = arrayOf("whatsapp", "messaging", "instagram", "facebook", "orca", "twitter", "telegram", "snapchat", "gm")
        for (i in sampleList.indices) {
            var position = resolveInfo.indexOfFirst { item -> item.activityInfo.packageName.contains("${sampleList[i]}", ignoreCase = true) }
            if (position > -1) {
                appsList.add(resolveInfo[position])
                resolveInfo.removeAt(position)
            }
        }
        appsList.addAll(resolveInfo)
        appsList.add(0, null)
        var shareDialog = Dialog(context)
        shareDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        shareDialog.setContentView(R.layout.dialog_share)
        shareDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        shareDialog.window!!.setGravity(Gravity.BOTTOM)
        shareDialog.window!!.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        var imageView = shareDialog.findViewById(R.id.imageView) as ImageView
        var ivClose = shareDialog.findViewById(R.id.ivClose) as ImageView
        var tvTitle = shareDialog.findViewById(R.id.tvTitle) as TextView
        var tvDescription = shareDialog.findViewById(R.id.tvDescription) as TextView
        var recyclerViewShare = shareDialog.findViewById(R.id.recyclerViewShare) as RecyclerView
        tvTitle.text = "$businessName"
        tvDescription.text = "$shareLink"
        ivClose.setOnClickListener {
            shareDialog.dismiss()
        }
        Glide.with(context)
            .load(businessImageUrl)
            .into(imageView)
        recyclerViewShare.layoutManager = GridLayoutManager(context, 4)
        var shareAdapter = ShareAdapter(context, appsList)
        recyclerViewShare.adapter = shareAdapter
        shareAdapter.onItemClick = { it ->
            shareDialog.dismiss()
            if (it == null) {
                Toast.makeText(context, "Text copied.", Toast.LENGTH_SHORT).show()
                val clipboard: ClipboardManager = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("label", "${sharedPreferences.getString("NAME", null)} requested you to explore $businessName on Pepens\n$shareLink")
                clipboard.setPrimaryClip(clip)
            } else {
                var intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_SUBJECT, "$businessName")
                intent.putExtra(
                    Intent.EXTRA_TEXT, "${sharedPreferences.getString("NAME", null)} requested you to explore $businessName on Pepens\n$shareLink"
                )
                intent.setPackage("${it.activityInfo.packageName}")
                context.startActivity(intent)
            }
        }
        shareDialog.show()
    }

    private fun <T> List<T>.toArrayList(): ArrayList<T> {
        return ArrayList(this)
    }

}