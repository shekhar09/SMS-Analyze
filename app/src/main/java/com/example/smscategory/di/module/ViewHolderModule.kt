package com.example.smscategory.di.module

import androidx.lifecycle.LifecycleRegistry
import com.example.smscategory.di.ViewModelScope
import com.example.smscategory.ui.base.BaseItemViewHolder
import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*, *>) {

    @Provides
    @ViewModelScope
    fun provideLifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)
}