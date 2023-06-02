package com.app.pepens.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.util.Log
import com.app.pepens.BuildConfig
import com.app.expensemanager.util.VolleySingleton
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.google.firebase.iid.FirebaseInstanceId
import org.json.JSONObject
import java.util.*

/**
 * Created by Vikas Kumar Singh on 17/12/20.
 */

class BootUpReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e("TAG", "onReceive: ------ ------ ------>")
        if (Objects.equals(intent!!.action, Intent.ACTION_BOOT_COMPLETED)) {
            var sharedPreferences = context!!.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
            val params = HashMap<String, String>()
            params["AppDeviceId"] = "0"
            params["ToId"] = sharedPreferences.getString("USER_ID", "0").toString()
            params["ToType"] = "4"
            params["DeviceId"] = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
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
            Log.e("TAG", "updateDevice: $jsonObject")
            val request = JsonObjectRequest(Request.Method.POST, Urls.AddUpdateDevice, jsonObject,
                Response.Listener { response ->
                    Log.e("TAG", "updateDevice: $response")
                },
                Response.ErrorListener {
                    Log.e("TAG", "updateDevice: $it")
                })
            request.retryPolicy = DefaultRetryPolicy(10000, 0, 1f)
            VolleySingleton.getInstance(context).addToRequestQueue(request)
        }
    }

}