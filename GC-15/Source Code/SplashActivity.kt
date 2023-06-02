package com.app.pepens.activity

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.pepens.BuildConfig
import com.app.pepens.R
import com.app.pepens.model.BusinessDetails.BusinessDetails
import com.app.pepens.model.BusinessDetails.BusinessDetailsData
import com.app.pepens.model.LogData
import com.app.pepens.model.Login.Login
import com.app.pepens.utils.Urls
import com.app.pepens.utils.Utils
import com.app.expensemanager.util.VolleySingleton
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_splash.*
import org.json.JSONObject
import java.util.*

class SplashActivity : AppCompatActivity() {

    private val TAG = SplashActivity::class.java.simpleName
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var progressDialog: Dialog
    private var userId: String = "0"
    private var notificationEntityType: String? = null
    private var notificationEntityId: String? = null
    private var notificationEntityTarget: String? = null
    private var notificationEntityTargetId: String? = null
    private var loginUser: Login? = null
    private var appUserId: Int = 0
    private val UPDATE_REQUEST_CODE = 1111
    private var openDynamicLink = false

    companion object {
        var checkLoginDetails = false
        var referCode = ""
    }

    private val appUpdateManager by lazy { AppUpdateManagerFactory.create(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)

        sharedPreferences = getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        progressDialog = Utils().progressDialog(applicationContext)

        if (intent != null) {
            if (intent.getStringExtra("NotificationEntityId") != null) {
                notificationEntityType = intent.getStringExtra("NotificationEntityType")
                notificationEntityId = intent.getStringExtra("NotificationEntityId")
                notificationEntityTarget = intent.getStringExtra("NotificationEntityTarget")
                notificationEntityTargetId = intent.getStringExtra("NotificationEntityTargetId")
            } else {
                val bundle = intent.extras
                if (bundle != null) {
                    for (key in intent.extras!!.keySet()) {
                        when (key) {
                            "EntityId" -> {
                                openDynamicLink = true
                                notificationEntityId = intent.extras!![key].toString()
                            }
                            "EntityType" -> {
                                openDynamicLink = true
                                notificationEntityType = intent.extras!![key].toString()
                            }
                            "CustomNotificationEntityType" -> {
                                openDynamicLink = true
                                notificationEntityTarget = intent.extras!![key].toString()
                            }
                            "CustomNotificationEntityId" -> {
                                openDynamicLink = true
                                notificationEntityTargetId = intent.extras!![key].toString()
                            }
                            "app_user_id" -> {
                                openDynamicLink = false
                                appUserId = intent.extras!![key].toString().toInt()
                            }
                        }
                    }
                }
            }
        }

        btnTryAgain.setOnClickListener {
            getData()
        }
    }

    override fun onResume() {
        super.onResume()
        Utils().updatePagesLog(
            this,
            LogData(
                pageId = Urls.SPLASH_PAGE,
                title = "Splash Page",
                eventName = "Page Open",
                note = "${JSONObject().put("note", "App start")}"
            )
        )

        getData()
    }

    private fun getData() {
//        appUserId = 1
//        if (appUserId > 0) {
//            val editor = sharedPreferences.edit()
//            editor.putString("USER_ID", "$appUserId")
//            editor.putString("COUNTRY_CODE0", "+91")
//            editor.putString("MOBILE_NO", "8920807929")
//            editor.putString("TOKEN", "f0845553-a2d5-4f46-8be4-2f6ee06d85bf")
//            editor.apply()
//        }

        userId = sharedPreferences.getString("USER_ID", "0").toString()

        if (Utils().verifyAvailableNetwork(applicationContext!!)) {
            layoutNoConnection.visibility = View.GONE

            Firebase.dynamicLinks.getDynamicLink(intent)
                .addOnSuccessListener(this) { pendingDynamicLinkData ->
                    var deepLink: Uri? = null
                    if (pendingDynamicLinkData != null) {
                        deepLink = pendingDynamicLinkData.link
                        Log.e(TAG, "getData: $deepLink")
                        if (deepLink != null) {
                            try {
                                if (deepLink.toString().contains("id", ignoreCase = true)) {
                                    openDynamicLink = true
                                    if (deepLink.toString().contains("/b/", ignoreCase = true)) {
                                        notificationEntityType = "${Urls.NOTIFICATION_CUSTOM}"
                                        notificationEntityId = "7"
                                        notificationEntityTargetId = Utils().getQueryString(deepLink.toString(), "id")!!
                                    } else if (deepLink.toString().contains("/f/", ignoreCase = true)) {
                                        notificationEntityType = "${Urls.NOTIFICATION_FEED}"
                                        notificationEntityId = Utils().getQueryString(deepLink.toString(), "id")!!
                                    }
                                    Log.e(TAG, "getData: $notificationEntityType    $notificationEntityId   $notificationEntityTargetId")
                                } else if (deepLink.toString().contains("refer", ignoreCase = true)) {
                                    openDynamicLink = false
                                    referCode = Utils().getQueryString(deepLink.toString(), "refer")!!
                                }
                            } catch (e: java.lang.Exception) {
                                Log.e(TAG, "getData: $e")
                                openDynamicLink = false
                            }
                        } else {
                            openDynamicLink = false
                        }
                    } else {
                        openDynamicLink = false
                    }
                }
                .addOnFailureListener(this) { e ->
                    Log.e(TAG, "getData onFailure: $e")
                    openDynamicLink = false
                }

            if (userId.isEmpty()) {
                Handler().postDelayed({
                    checkLoginDetails = true
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }, 2000)
            } else {
                getLoginUser()
            }
        } else {
            Toast.makeText(applicationContext, getString(R.string.no_internet), Toast.LENGTH_SHORT)
                .show()
            layoutNoConnection.visibility = View.VISIBLE
        }
    }

    private fun getLoginUser() {
        val request = StringRequest(
            Request.Method.GET,
            Urls.GET_USER_ON_STARTUP + userId + Urls.TOKEN,
            { response ->
                Log.e(TAG, "getLoginUser: $response")
                if (layoutNoConnection != null) {
                    val jsonObject = JSONObject(response.toString())
                    if (jsonObject.getBoolean("IsSuccess")) {
                        loginUser = Gson().fromJson(response.toString(), Login::class.java)
                        if (BuildConfig.VERSION_CODE < loginUser!!.loginData!!.appVersion!!.toInt()) {
                            if (loginUser!!.loginData!!.appVersionIsMandatory) {
                                appUpdateManager.appUpdateInfo.addOnSuccessListener {
                                    when {
                                        it.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE -> {
                                            appUpdateManager.startUpdateFlowForResult(
                                                it,
                                                AppUpdateType.IMMEDIATE,
                                                this,
                                                UPDATE_REQUEST_CODE
                                            )
                                        }
                                        it.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS -> {
                                            appUpdateManager.startUpdateFlowForResult(
                                                it,
                                                AppUpdateType.IMMEDIATE,
                                                this,
                                                UPDATE_REQUEST_CODE
                                            )
                                        }
                                        it.updateAvailability() == UpdateAvailability.UPDATE_NOT_AVAILABLE -> {
                                            saveUserDetail()
                                        }
                                        it.updateAvailability() == UpdateAvailability.UNKNOWN -> {
                                            saveUserDetail()
                                        }
                                        else -> {
                                            saveUserDetail()
                                        }
                                    }
                                }.addOnFailureListener {
                                    saveUserDetail()
                                }.addOnCompleteListener {
                                    saveUserDetail()
                                }
                                updateEvents()
                            } else {
                                MaterialAlertDialogBuilder(this)
                                    .setTitle("New Version Released")
                                    .setMessage("New version available for update, click on UPDATE to upgrade app.")
                                    .setPositiveButton("UPDATE") { dialog, id ->
                                        dialog.dismiss()
                                        appUpdateManager.appUpdateInfo.addOnSuccessListener {
                                            when {
                                                it.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE -> {
                                                    appUpdateManager.startUpdateFlowForResult(
                                                        it,
                                                        AppUpdateType.IMMEDIATE,
                                                        this,
                                                        UPDATE_REQUEST_CODE
                                                    )
                                                }
                                                it.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS -> {
                                                    appUpdateManager.startUpdateFlowForResult(
                                                        it,
                                                        AppUpdateType.IMMEDIATE,
                                                        this,
                                                        UPDATE_REQUEST_CODE
                                                    )
                                                }
                                                it.updateAvailability() == UpdateAvailability.UPDATE_NOT_AVAILABLE -> {
                                                    saveUserDetail()
                                                }
                                                it.updateAvailability() == UpdateAvailability.UNKNOWN -> {
                                                    saveUserDetail()
                                                }
                                                else -> {
                                                    saveUserDetail()
                                                }
                                            }
                                        }.addOnFailureListener {
                                            saveUserDetail()
                                        }.addOnCompleteListener {
                                            saveUserDetail()
                                        }
                                        updateEvents()
                                    }
                                    .setNegativeButton("No Thanks") { dialog, id ->
                                        dialog.dismiss()
                                        saveUserDetail()
                                    }.show()
                            }
                        } else {
                            saveUserDetail()
                        }
                    } else {
                        if (jsonObject.getString("Message").contains("Record not found")) {
                            checkLoginDetails = true
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(
                                this,
                                jsonObject.getString("Message"),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }, {
                Log.e(TAG, "getLoginUser: $it")
                if (it.toString().contains("NoConnectionError") || it.toString()
                        .contains("TimeoutError")
                ) {
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.no_internet),
                        Toast.LENGTH_SHORT
                    ).show()
                    layoutNoConnection.visibility = View.VISIBLE
                }
            })
        request.retryPolicy = DefaultRetryPolicy(10000, 0, 1f)
        VolleySingleton.getInstance(this).addToRequestQueue(request)
    }

    private fun updateEvents() {
        if (appUpdateManager != null) {
            appUpdateManager.registerListener {
                Log.e(TAG, "onCreate: ${it.installStatus()} $it")
                when {
                    it.installStatus() == InstallStatus.DOWNLOADING -> {

                    }
                    it.installStatus() == InstallStatus.DOWNLOADED -> {

                    }
                    it.installStatus() == InstallStatus.INSTALLING -> {

                    }
                    it.installStatus() == InstallStatus.INSTALLED -> {
                        getData()
                    }
                    it.installStatus() == InstallStatus.CANCELED -> {
                        getData()
                    }
                    it.installStatus() == InstallStatus.FAILED -> {
                        getData()
                    }
                    it.installStatus() == InstallStatus.PENDING -> {
                        getData()
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == UPDATE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            saveUserDetail()
        } else if (requestCode == UPDATE_REQUEST_CODE && resultCode == Activity.RESULT_CANCELED) {
            finish()
        }
    }

    private fun saveUserDetail() {
        val editor = sharedPreferences.edit()
        editor.putString("USER_ID", loginUser!!.loginData!!.userId.toString())
        editor.putString("COUNTRY_CODE", loginUser!!.loginData!!.countryCode)
        editor.putString("MOBILE_NO", loginUser!!.loginData!!.mobileNo)
        editor.putString("TOKEN", loginUser!!.loginData!!.token)
        editor.putString("NAME", loginUser!!.loginData!!.name)
        editor.putString("FIRST_NAME", loginUser!!.loginData!!.firstName)
        editor.putString("LAST_NAME", loginUser!!.loginData!!.lastName)
        editor.putString("EMAIL", loginUser!!.loginData!!.email)
        editor.putString("CURRENT_LOCATION", loginUser!!.loginData!!.currentLocation)
        editor.putString("DEFAULT_PICTURE_URL", loginUser!!.loginData!!.defaultPictureUrl)
        editor.putString("REFERRAL_CODE", loginUser!!.loginData!!.referralCode.toString())
        editor.apply()

        if (openDynamicLink) {
            getBusinessDetail()
        } else {
            goToMain()
        }
    }

    private fun getBusinessDetail() {
        val request = StringRequest(Request.Method.GET, "${Urls.GET_BUSINESS}${sharedPreferences.getString("USER_ID", null)}${Urls.BUSINESS_ID}$notificationEntityTargetId",
            { response ->
                Log.e(TAG, "getBusiness: $response")
                if (layoutNoConnection != null) {
                    val jsonObject = JSONObject(response.toString())
                    if (jsonObject.getBoolean("IsSuccess")) {
                        var businessDetails = Gson().fromJson(response.toString(), BusinessDetails::class.java)
                        notificationEntityId = if (businessDetails.businessDetailsData!!.businessCategoryId == Urls.ID_RESTAURANT_CATEGORY) {
                            "6"
                        } else {
                            "7"
                        }
                        if (loginUser!!.loginData!!.areaId == businessDetails.businessDetailsData!!.areaId.toString()) {
                            goToMain()
                        } else {
                            MaterialAlertDialogBuilder(this)
                                .setTitle("${businessDetails.businessDetailsData!!.businessName}")
                                .setMessage("Need to change your current area to ${businessDetails.businessDetailsData!!.areaName} to explore this business/product. Your current area is ${loginUser!!.loginData!!.areaName}.")
                                .setCancelable(false)
                                .setPositiveButton("Yes") { dialog, id ->
                                    dialog.dismiss()
                                    updatePinCode(businessDetails!!.businessDetailsData!!)
                                }
                                .setNegativeButton("No") { dialog, id ->
                                    dialog.dismiss()
                                    openDynamicLink = false
                                    notificationEntityId = null
                                    notificationEntityType = null
                                    notificationEntityTarget = null
                                    notificationEntityTargetId = null
                                    goToMain()
                                }
                                .show()
                        }
                    } else {
                        goToMain()
                    }
                }
            }, {
                if (layoutNoConnection != null) {
                    goToMain()
                }
            })
        request.retryPolicy = DefaultRetryPolicy(10000, 0, 1f)
        VolleySingleton.getInstance(this).addToRequestQueue(request)
    }

    private fun updatePinCode(businessDetailsData: BusinessDetailsData) {
        var add = ""
        if (!businessDetailsData.address1.isNullOrEmpty())
            add += "${businessDetailsData.address1}, "
        if (!businessDetailsData.address2.isNullOrEmpty())
            add += "${businessDetailsData.address2}"
        if (!businessDetailsData.areaName.isNullOrEmpty())
            add += ",${businessDetailsData.areaName}"
        if (!businessDetailsData.zipcode.isNullOrEmpty())
            add += "(${businessDetailsData.zipcode})"
        if (!businessDetailsData.state.isNullOrEmpty())
            add += ", ${businessDetailsData.state}"
        if (!businessDetailsData.country.isNullOrEmpty())
            add += ", ${businessDetailsData.country}"
        val jsonObject = JSONObject()
        jsonObject.put("UserId", sharedPreferences.getString("USER_ID", null))
        jsonObject.put("PartnerId", businessDetailsData.partnerId)
        jsonObject.put("AreaId", businessDetailsData.areaId)
        jsonObject.put("Locality", businessDetailsData.areaName)
        jsonObject.put("Zipcode", businessDetailsData.zipcode)
        jsonObject.put("City", businessDetailsData.areaName)
        jsonObject.put("State", businessDetailsData.state)
        jsonObject.put("Country", businessDetailsData.country)
        jsonObject.put("Latitude", businessDetailsData.latitude)
        jsonObject.put("Longitude", businessDetailsData.longitude)
        jsonObject.put("CurrentLocation", add)
        jsonObject.put("IsUseCurrentLocation", "false")
        Log.e(TAG, "updateLocation: $jsonObject")
        val request =
            JsonObjectRequest(Request.Method.POST, Urls.UPDATE_SHOPPING, jsonObject, { response ->
                Log.e(TAG, "updateLocation: $response")
                progressDialog.dismiss()
                if (layoutNoConnection != null) {
                    val jsonObject = JSONObject(response.toString())
                    if (jsonObject.getBoolean("IsSuccess")) {
                        try {
                            loginUser!!.loginData!!.zipcode = businessDetailsData.zipcode
                            loginUser!!.loginData!!.latitude = businessDetailsData.latitude.toString()
                            loginUser!!.loginData!!.longitude = businessDetailsData.longitude.toString()
                            loginUser!!.loginData!!.currentLocation = add
                            loginUser!!.loginData!!.isUseCurrentLocation = false
                            loginUser!!.loginData!!.areaId = businessDetailsData.areaId.toString()
                            loginUser!!.loginData!!.partnerId = businessDetailsData.partnerId
                            loginUser!!.loginData!!.locality = businessDetailsData.areaName
                            loginUser!!.loginData!!.areaId = businessDetailsData.areaId.toString()
                            goToMain()
                        } catch (e: Exception) {
                            goToMain()
                        }
                    }
                }
            }, {
                Log.e(TAG, "updateLocation: $it")
                progressDialog.dismiss()
                if (layoutNoConnection != null) {
                    goToMain()
                }
            })
        request.retryPolicy = DefaultRetryPolicy(10000, 0, 1f)
        VolleySingleton.getInstance(this).addToRequestQueue(request)
    }

    private fun goToMain() {
        checkLoginDetails = false
        try {
            FirebaseCrashlytics.getInstance()
                .setCustomKey("MobileNumber", "${loginUser!!.loginData!!.mobileNo}")
            FirebaseCrashlytics.getInstance().setUserId("${loginUser!!.loginData!!.userId}")
            if (!notificationEntityType.isNullOrEmpty()) {
                loginUser!!.loginData!!.notificationEntityType = notificationEntityType!!.toInt()
            }
            if (!notificationEntityId.isNullOrEmpty()) {
                loginUser!!.loginData!!.notificationEntityId = notificationEntityId!!.toInt()
            }
            if (!notificationEntityTarget.isNullOrEmpty()) {
                loginUser!!.loginData!!.notificationEntityId = notificationEntityTarget!!.toInt()
            }
            if (!notificationEntityTargetId.isNullOrEmpty()) {
                loginUser!!.loginData!!.notificationEntityTargetId =
                    notificationEntityTargetId!!.toInt()
            }

            if (loginUser!!.loginData!!.name.isNullOrEmpty() || loginUser!!.loginData!!.firstName.isNullOrEmpty() || loginUser!!.loginData!!.lastName.isNullOrEmpty() || loginUser!!.loginData!!.gender.isNullOrEmpty()) {
                var intent = Intent(applicationContext, UpdateBasicProfileActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.putExtra("loginUser", loginUser)
                startActivity(intent)
                finish()
            } else if (loginUser!!.loginData!!.areaId == "0" || loginUser!!.loginData!!.latitude!!.isNullOrEmpty() || loginUser!!.loginData!!.longitude!!.isNullOrEmpty() || loginUser!!.loginData!!.zipcode.isNullOrEmpty()) {
                var intent = Intent(applicationContext, LocationActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.putExtra("loginUser", loginUser)
                startActivity(intent)
                finish()
            } else {
                var intent = Intent(applicationContext, MainActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.putExtra("loginUser", loginUser)
                startActivity(intent)
                finish()
            }
        } catch (e: Exception) {
            FirebaseCrashlytics.getInstance().recordException(e)
            FirebaseCrashlytics.getInstance().setCustomKey("Exception", "$e")
        }
    }

}