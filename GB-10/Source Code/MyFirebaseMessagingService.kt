package com.app.pepens.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.app.pepens.R
import com.app.pepens.activity.SplashActivity
import com.bumptech.glide.Glide
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.*

/**
 * Created by Vikas Kumar Singh on 05/08/20.
 */

class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val TAG = MyFirebaseMessagingService::class.java.simpleName
    private val CHANNEL_ID = "A2ZSEARCH"
    private var notId = 0
    private var broadcaster: LocalBroadcastManager? = null

    companion object {
        var notificationTitle: String? = null
        var notificationMessage: String? = null
        var notificationEntityType: String? = null
        var notificationEntityId: String? = null
        var notificationFromType: String? = null
        var notificationEntityTarget: String? = null
        var notificationEntityTargetId: String? = null
        var notificationFromId: String? = null
        var notificationItemRequestId: String? = null
        var notificationFileUrl: String? = null
    }

    override fun onCreate() {
        super.onCreate()

        broadcaster = LocalBroadcastManager.getInstance(this)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.e(TAG, "onMessageReceived: ${remoteMessage.data}")
        var generator = Random()
        notId = generator.nextInt()
        if (remoteMessage != null) {
            notificationEntityType = remoteMessage.data["EntityType"]
            notificationEntityId = remoteMessage.data["EntityId"]
            notificationFromType = remoteMessage.data["FromType"]
            notificationFromId = remoteMessage.data["FromId"]
            notificationEntityTarget = remoteMessage.data["CustomNotificationEntityType"]
            notificationEntityTargetId = remoteMessage.data["CustomNotificationEntityId"]
            notificationItemRequestId = remoteMessage.data["ItemRequestId"]
            notificationFileUrl = remoteMessage.data["FileUrl"]
        }

        if (remoteMessage.notification != null) {
            notificationTitle = remoteMessage.notification!!.title
            notificationMessage = remoteMessage.notification!!.body

            showNotification(remoteMessage.notification!!.title, remoteMessage.notification!!.body)
        }
    }

    private fun showNotification(title: String?, message: String?) {
        val intent = Intent(this, SplashActivity::class.java)
        intent.putExtra("NotificationEntityType", notificationEntityType)
        intent.putExtra("NotificationEntityId", notificationEntityId)
        intent.putExtra("NotificationEntityTarget", notificationEntityTarget)
        intent.putExtra("NotificationEntityTargetId", notificationEntityTargetId)
        val pendingIntent = PendingIntent.getActivity(this, notId, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_ONE_SHOT)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        var notificationBuilder: NotificationCompat.Builder? = null
        if (!notificationFileUrl.isNullOrEmpty()) {
            val futureTarget = Glide.with(this).asBitmap().load(notificationFileUrl).submit()
            val bitmap = futureTarget.get()
            notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_notification_icon)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
                .setContentTitle(title)
                .setStyle(NotificationCompat.BigTextStyle().bigText(message))
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setLargeIcon(bitmap)
                .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null))
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent)
            Glide.with(this).clear(futureTarget)
        } else {
            notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_notification_icon)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
                .setContentTitle(title)
                .setStyle(NotificationCompat.BigTextStyle().bigText(message))
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent)
        }

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, "Channel human readable title", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(notId, notificationBuilder.build())
    }

}