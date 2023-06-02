package com.app.pepens.activity

import android.app.Activity
import android.content.*
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.app.pepens.BuildConfig
import com.app.pepens.R
import com.app.pepens.fragment.AddUpdateAddressFragment
import com.app.pepens.fragment.HomeFragment
import com.app.pepens.fragment.LocationFragment
import com.app.pepens.fragment.OfferFragment
import com.app.pepens.interfaces.DialogItemClick
import com.app.pepens.interfaces.NotificationUpdate
import com.app.pepens.model.CartItemsList
import com.app.pepens.model.Categories.Categories
import com.app.pepens.model.Login.Login
import com.app.pepens.model.RestaurantCartList
import com.app.pepens.utils.AnalyticsApplication
import com.app.pepens.utils.CheckConnectivity
import com.app.pepens.utils.Urls
import com.app.pepens.utils.Utils
import com.app.expensemanager.util.VolleySingleton
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.google.android.gms.analytics.Tracker
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.iid.FirebaseInstanceId
import com.google.gson.Gson
import org.json.JSONObject
import java.util.*
import kotlin.collections.HashMap

open class MainActivity : AppCompatActivity(), NotificationUpdate, DialogItemClick {

    private var TAG = MainActivity::class.java.simpleName
    private lateinit var sharedPreferences: SharedPreferences
    private var mNetworkReceiver: BroadcastReceiver? = null
    private var badgeDrawable: BadgeDrawable? = null
    private var navHostFragment: NavHostFragment? = null

    companion object {
        var mTracker: Tracker? = null
        var id = 0
        var showDialog = false
        var login: Login? = null
        var bottomNavigationView: BottomNavigationView? = null
        var tvConnection: TextView? = null
        var cartArrayList: ArrayList<CartItemsList> = ArrayList()
        var restaurantCartList: ArrayList<RestaurantCartList> = ArrayList()
        var adFromR = false
        var categories: Categories? = null
    }

    fun dialog(value: Boolean) {
        if (showDialog) {
            tvConnection!!.visibility = View.VISIBLE
            if (value) {
                tvConnection!!.setBackgroundColor(Color.parseColor("#09AF00"))
                tvConnection!!.text = "Back Online."
                Handler().postDelayed({
                    tvConnection!!.visibility = View.GONE
                }, 2000)
            } else {
                tvConnection!!.setBackgroundColor(Color.RED)
                tvConnection!!.text = "No connection."
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Utils().restartAppAtCrash(this, MainActivity::class.java.simpleName)

        mNetworkReceiver = CheckConnectivity()
        registerReceiver(mNetworkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

        sharedPreferences = getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)

        if (intent != null) {
            login = intent.getParcelableExtra("loginUser")
        }

        val application: AnalyticsApplication = application as AnalyticsApplication
        mTracker = application.defaultTracker

        if (SplashActivity.checkLoginDetails) {
            getLoginUser()
        }

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        bottomNavigationView!!.setupWithNavController(navHostFragment!!.navController)
        tvConnection = findViewById(R.id.tv_connection)
        bottomNavigationView!!.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    HomeFragment.apiDataReceived = false
                    navHostFragment!!.navController.navigate(R.id.homeFragment)
                }
                R.id.offerFragment -> {
                    OfferFragment.apiDataReceived = false
                    navHostFragment!!.navController.navigate(R.id.offerFragment)
                }
                R.id.mainDialogFragment -> {
                    navHostFragment!!.navController.navigate(R.id.mainDialogFragment)
                }
                R.id.feedFragment -> {
                    navHostFragment!!.navController.navigate(R.id.feedFragment)
                }
                R.id.notificationFragment -> {
                    navHostFragment!!.navController.navigate(R.id.notificationFragment)
                }
            }
            true
        }
        badgeDrawable = bottomNavigationView!!.getOrCreateBadge(R.id.notificationFragment)
        badgeDrawable!!.backgroundColor = Color.RED
        badgeDrawable!!.badgeTextColor = Color.WHITE
        badgeDrawable!!.maxCharacterCount = 2

        getMainCategories()

        Handler().postDelayed({
            showDialog = true
            if (Utils().verifyAvailableNetwork(this)) {
                updateDevice()
            }
        }, 3000)
    }

    override fun updateNotification(visible: Boolean, count: Int) {
        if (visible) {
            badgeDrawable!!.number = count
            badgeDrawable!!.isVisible = true
        } else {
            badgeDrawable!!.number = count
            badgeDrawable!!.isVisible = false
        }
    }

    override fun itemDialogClick(id: Int) {
        when (id) {
            Urls.ID_RESTAURANT_CATEGORY -> {
                navHostFragment!!.navController.navigate(R.id.restaurantsFragment)
            }
            Urls.ID_GROCERY_CATEGORY -> {
                var bundle = Bundle()
                bundle.putInt("BusinessCategoryId", id)
                navHostFragment!!.navController.navigate(R.id.shoppingFragment, bundle)
            }
            Urls.ID_PICK_DROP_CATEGORY -> {
                navHostFragment!!.navController.navigate(R.id.pickAndDropFragment)
            }
            else -> {
                var bundle = Bundle()
                bundle.putInt("BusinessCategoryId", id)
                navHostFragment!!.navController.navigate(R.id.shoppingFragment, bundle)
            }
        }
    }

    private fun getLoginUser() {
        val request = StringRequest(Request.Method.GET,
            Urls.GET_USER_ON_STARTUP + sharedPreferences.getString("USER_ID", "0") + Urls.TOKEN,
            { response ->
                Log.e(TAG, "getLoginUser: $response")
                val jsonObject = JSONObject(response.toString())
                if (jsonObject.getBoolean("IsSuccess")) {
                    login = Gson().fromJson(response.toString(), Login::class.java)
                }
            }, {
                Log.e(TAG, "getLoginUser: $it")
            })
        request.retryPolicy = DefaultRetryPolicy(10000, 0, 1f)
        VolleySingleton.getInstance(this).addToRequestQueue(request)
    }

    private fun updateDevice() {
        val params = HashMap<String, String>()
        params["AppDeviceId"] = "0"
        params["ToId"] = sharedPreferences.getString("USER_ID", "0").toString()
        params["ToType"] = "4"
        params["DeviceId"] = Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)
        params["AppVersionNumber"] = BuildConfig.VERSION_CODE.toString()
        params["AppVersionName"] = BuildConfig.VERSION_NAME
        params["DeviceOS"] = "Android"
        params["DeviceOSVersion"] = Build.VERSION.SDK
        params["DeviceModel"] = Build.MODEL
        params["DeviceManufacturer"] = Build.MANUFACTURER
        params["DeviceName"] = "${Build.MANUFACTURER} - ${Build.MODEL}"
        params["IPAddress"] = Utils().getMobileIPAddress().toString()
        params["FirebaseToken"] = FirebaseInstanceId.getInstance().token.toString()
        val jsonObject = JSONObject(params as Map<*, *>)
        Log.e(TAG, "updateDevice: $jsonObject")
        val request = JsonObjectRequest(
            Request.Method.POST,
            Urls.AddUpdateDevice,
            jsonObject,
            { response ->
                Log.e("TAG", "updateDevice: $response")
            },
            {
                Log.e("TAG", "updateDevice: $it")
            })
        request.retryPolicy = DefaultRetryPolicy(10000, 0, 1f)
        VolleySingleton.getInstance(this).addToRequestQueue(request)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navHostFragment!!.navController, null)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            102 -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        val navHostFragment: Fragment? =
                            supportFragmentManager.primaryNavigationFragment
                        val fragment: Fragment = navHostFragment!!.childFragmentManager.fragments[0]
                        if (fragment is AddUpdateAddressFragment) {
                            (fragment as AddUpdateAddressFragment).gpsEvent(true)
                        } else if (fragment is LocationFragment) {
                            (fragment as LocationFragment).gpsEvent(true)
                        }
//                        val fragment: AddUpdateAddressFragment = supportFragmentManager.findFragmentById(R.id.frameLayout) as AddUpdateAddressFragment
//                        fragment.gpsEvent(true)
                    }
                    else -> {
                        val navHostFragment: Fragment? =
                            supportFragmentManager.primaryNavigationFragment
                        val fragment: Fragment = navHostFragment!!.childFragmentManager.fragments[0]
                        if (fragment is AddUpdateAddressFragment) {
                            (fragment as AddUpdateAddressFragment).gpsEvent(false)
                        } else if (fragment is LocationFragment) {
                            (fragment as LocationFragment).gpsEvent(false)
                        }
//                        val fragment: AddUpdateAddressFragment = supportFragmentManager.findFragmentById(R.id.frameLayout) as AddUpdateAddressFragment
//                        fragment.gpsEvent(false)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterNetworkChanges()
    }

    private fun unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkReceiver)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }

    fun getMainCategories() {
        val request = StringRequest(
            Request.Method.GET,
            Urls.GET_HOME_CATEGORIES + login!!.loginData!!.areaId + Urls.USER_ID + sharedPreferences.getString(
                "USER_ID",
                "0"
            ),
            { response ->
                Log.e(TAG, "getMainCategories: $response")
                if (bottomNavigationView != null) {
                    val jsonObject = JSONObject(response.toString())
                    categories = if (jsonObject.has("IsSuccess") && jsonObject.getBoolean("IsSuccess")) {
                            Gson().fromJson(response.toString(), Categories::class.java)
                        } else {
                            null
                        }
                    categories!!.categoriesData!!.removeAll { item -> item.businessCategoryId != Urls.ID_GROCERY_CATEGORY }
                }
            },
            {
                Log.e(TAG, "getMainCategories: $it")
            })
        request.retryPolicy = DefaultRetryPolicy(10000, 2, 1f)
        VolleySingleton.getInstance(this).addToRequestQueue(request)
    }

}