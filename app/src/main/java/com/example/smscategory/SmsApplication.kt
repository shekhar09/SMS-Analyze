package com.example.smscategory

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Base64
import android.util.Log
import com.example.smscategory.di.component.ApplicationComponent
import com.example.smscategory.di.component.DaggerApplicationComponent
import com.example.smscategory.di.module.ApplicationModule
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class SmsApplication : Application() {


    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        injectDependencies()

    }



    companion object
    {
        private var mInstance: SmsApplication? = null
        const val notificationChannelID = "UploadChannel"
    }


    @Synchronized
    fun  getInstance(): SmsApplication? {
        return mInstance
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

    // Needed to replace the component with a test specific one
    fun setComponent(applicationComponent: ApplicationComponent) {
        this.applicationComponent = applicationComponent
    }






}