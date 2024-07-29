package com.calmmycode.bootcounter.presentation.feature.boot

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.calmmycode.bootcounter.data.feature.boot.BootRecordEntity
import com.calmmycode.bootcounter.domain.feature.boot.repo.BootRecordRepo
import com.calmmycode.bootcounter.presentation.feature.main.MainActivity
import com.calmmycode.bootcounter.presentation.feature.permission.PermissionActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@AndroidEntryPoint
class BootService : Service() {

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    @Inject
    lateinit var bootRecordRepo: BootRecordRepo

    override fun onBind(intent: Intent): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand $intent")

        val result = scope.launch {
            bootRecordRepo.add(BootRecordEntity(
                timestamp = System.currentTimeMillis())
            )
        }
        checkNotificationPermission(
            doOnHasPermissions = {
                scope.launch {
                    val times = bootRecordRepo.getAll()
                    withContext(Dispatchers.Main){
                        showNotification(this@BootService, "Test title", "Times $times", Intent(this@BootService, MainActivity::class.java), 1234)
                    }
                }
            },
            doOnNoPermissions = {
                // need to improve
                startActivity(Intent(this, PermissionActivity::class.java).addFlags(FLAG_ACTIVITY_NEW_TASK))
            }
        )

        return super.onStartCommand(intent, flags, startId)
    }


    fun showNotification(context: Context, title: String?, message: String?, intent: Intent?, reqCode: Int) {

        val pendingIntent = PendingIntent.getActivity(context, reqCode, intent, PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)
        val CHANNEL_ID = "channel_name" // The id of the channel.
        val notificationBuilder: NotificationCompat.Builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setContentIntent(pendingIntent)
        val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "Channel Name" // The user-visible name of the channel.
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            notificationManager.createNotificationChannel(mChannel)
        }
        notificationManager.notify(reqCode, notificationBuilder.build()) // 0 is the request code, it should be unique id

        Log.d("showNotification", "showNotification: $reqCode")
    }
    
    private fun checkNotificationPermission(
        doOnHasPermissions:() -> Unit,
        doOnNoPermissions:() -> Unit,
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val hasNotificationPermission = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS,
            ) == PackageManager.PERMISSION_GRANTED
            if (!hasNotificationPermission){
                doOnNoPermissions()
                return
            }
        }
        doOnHasPermissions()
    }

    companion object {
        const val TAG = "BootService"
    }
}