package com.example.smscategory.ui.base

import androidx.lifecycle.MutableLiveData
import com.example.smscategory.utils.rx.SchedulerProvider

import io.reactivex.disposables.CompositeDisposable


abstract class BaseItemViewModel<T : Any>(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable
) : BaseViewModel(schedulerProvider, compositeDisposable) {

    val data: MutableLiveData<T> = MutableLiveData()

    fun onManualCleared() = onCleared()

    fun updateData(data: T) {
        this.data.postValue(data)
    }
}