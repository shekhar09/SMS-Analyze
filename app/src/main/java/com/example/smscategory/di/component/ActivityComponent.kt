package com.example.smscategory.di.component


import com.example.smscategory.ui.main.MainActivity
import com.example.smscategory.di.ActivityScope
import com.example.smscategory.di.module.ActivityModule

import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity: MainActivity)


}