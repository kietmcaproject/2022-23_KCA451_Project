package com.app.pepens.utils

import android.app.ActivityManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import com.app.pepens.activity.LocationActivity
import com.app.pepens.activity.MainActivity

/**
 * Created by Vikas Kumar Singh on 14/09/20.
 */

class CheckConnectivity : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        try {
            if (context != null) {
                val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
                val cn = am.getRunningTasks(1)[0].topActivity
                if (cn != null) {
                    Log.e("TAG", "onReceive: ${cn!!.shortClassName}")
                    if (isOnline(context)) {
                        Log.e("TAG", "Connected")
                        if (cn!!.shortClassName == ".activity.MainActivity") {
                            MainActivity().dialog(true)
                        } else if (cn!!.shortClassName == ".activity.ShoppingActivity") {
                            LocationActivity().dialog(true)
                        }
                    } else {
                        Log.e("TAG", "No Connection")
                        if (cn!!.shortClassName == ".activity.MainActivity") {
                            MainActivity().dialog(false)
                        } else if (cn!!.shortClassName == ".activity.ShoppingActivity") {
                            LocationActivity().dialog(false)
                        }
                    }
                }
            }
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }

    private fun isOnline(context: Context): Boolean {
        return try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            //should check null because in airplane mode it will be null
            netInfo != null && netInfo.isConnected
        } catch (e: NullPointerException) {
            e.printStackTrace()
            false
        }
    }

}