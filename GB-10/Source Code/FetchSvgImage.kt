package com.app.pepens.utils

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.app.pepens.R
import com.pixplicity.sharp.Sharp
import okhttp3.*
import java.io.IOException
import java.io.InputStream

/**
 * Created by Vikas Kumar Singh on 6/4/21.
 */

class FetchSvgImage {

    private var httpClient: OkHttpClient? = null

    fun fetchSvg(context: Context, url: String?, target: ImageView) {
        if (httpClient == null) {
            httpClient = OkHttpClient.Builder()
                .cache(Cache(context.cacheDir, 5 * 1024 * 1014))
                .build()
        }

        // here we are making HTTP call to fetch data from URL.
        val request: Request = Request.Builder().url(url).build()
        httpClient!!.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                // we are adding a default image if we gets any error.
                target.setImageResource(R.mipmap.ic_notification_icon)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call?, response: Response) {
                val stream: InputStream = response.body()!!.byteStream()
                try {
                    Sharp.loadInputStream(stream).into(target)
                } catch (e: Exception) {
                    Log.e("TAG", "onResponse: $e")
                }
                stream.close()
            }
        })
    }

}