package com.example.smscategory.di.component

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.smscategory.SmsApplication

import com.example.smscategory.di.ApplicationContext
import com.example.smscategory.di.module.ApplicationModule
import com.example.smscategory.utils.rx.SchedulerProvider


import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: SmsApplication)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context



    fun getSchedulerProvider(): SchedulerProvider

    fun getCompositeDisposable(): CompositeDisposable
}