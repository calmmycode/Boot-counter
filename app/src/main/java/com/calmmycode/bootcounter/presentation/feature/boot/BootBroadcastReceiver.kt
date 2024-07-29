package com.calmmycode.bootcounter.presentation.feature.boot

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BootBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        intent.action?.let {  action ->
            if (action == Intent.ACTION_BOOT_COMPLETED) {
                Log.i(TAG, action)
                val i = Intent(context, BootService::class.java)
                context.startService(i)
            }
        }
    }

    companion object {
        const val TAG = "BootBroadcastReceiver"
    }
}