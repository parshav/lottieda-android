package com.pv.pvbase

import android.app.Application
import com.google.firebase.FirebaseApp
import com.pv.base.activityHelperModule
import com.pv.base.navigatorModule
import com.pv.base.resourceHelperModule
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)

        startKoin(
            this,
            listOf(
                activityHelperModule,
                navigatorModule,
                resourceHelperModule
            )
        )
    }
}