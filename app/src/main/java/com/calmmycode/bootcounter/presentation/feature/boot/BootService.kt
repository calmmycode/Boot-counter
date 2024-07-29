package com.calmmycode.bootcounter.presentation.feature.boot

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class BootService : Service() {
    override fun onBind(intent: Intent): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Toast.makeText(this, "yes", Toast.LENGTH_LONG).show()
        Log.d(TAG, "onStartCommand $intent")
        return super.onStartCommand(intent, flags, startId)
    }

    companion object {
        const val TAG = "BootService"
    }
}