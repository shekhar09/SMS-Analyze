package com.example.smscategory.di.component

import com.example.smscategory.di.FragmentScope
import com.example.smscategory.di.module.FragmentModule

import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {

//    fun inject(fragment: HomeFragment)



}