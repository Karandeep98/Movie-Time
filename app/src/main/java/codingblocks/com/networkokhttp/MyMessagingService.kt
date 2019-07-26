package codingblocks.com.networkokhttp

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remotemessage: RemoteMessage?) {
        super.onMessageReceived(remotemessage)
        shownotification(remotemessage?.notification?.title.toString(),
            remotemessage?.notification?.body.toString()
        )
    }
    fun shownotification( title:String, message:String) {


        val i = Intent(this, MainActivity::class.java)
        val pi = PendingIntent.getActivity(this, 1, i, PendingIntent.FLAG_UPDATE_CURRENT)
        val simplenotification = NotificationCompat.Builder(this, "first")
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pi)
            .setAutoCancel(true)
            .build()
        val manager = NotificationManagerCompat.from(this)
        manager.notify(System.currentTimeMillis().toInt(), simplenotification)
    }

}