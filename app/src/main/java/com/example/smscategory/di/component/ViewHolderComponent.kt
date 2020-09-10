package com.example.smscategory.di.component


import com.example.smscategory.di.ViewModelScope
import com.example.smscategory.di.module.ViewHolderModule
import dagger.Component

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

//    fun inject(viewHolder: DummyItemViewHolder)
}