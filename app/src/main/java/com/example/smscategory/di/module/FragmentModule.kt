package com.example.smscategory.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.smscategory.ui.base.BaseFragment
import com.example.smscategory.utils.rx.SchedulerProvider

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)
//
//    @Provides
//    fun provideSettingsViewModel(
//        schedulerProvider: SchedulerProvider,
//        compositeDisposable: CompositeDisposable/*,
//            tokenRepository: TokenRepository*/): SettingsViewModel =
//        ViewModelProviders.of(fragment,
//            ViewModelProviderFactory(SettingsViewModel::class) {
//                SettingsViewModel(schedulerProvider, compositeDisposable)
//            }
//        ).get(SettingsViewModel::class.java)

}