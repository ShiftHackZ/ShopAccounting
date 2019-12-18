package com.shifthackz.shopaccounting.domain

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.LiveData
import android.util.Log
import com.shifthackz.shopaccounting.presentation.widget.SingleLiveEvent
import com.shifthackz.shopaccounting.repository.AppRepository
import com.shifthackz.shopaccounting.repository.database.entity.CategoryEntity

class CategoriesViewModel(application: Application, private val repository: AppRepository)
    : BaseViewModel(application) {

    private val liveDataItems = SingleLiveEvent<List<CategoryEntity>>()

    @SuppressLint("CheckResult")
    fun getAllItems() {
        repository.getCategories()?.subscribe { list -> liveDataItems.value = list }
    }

    fun getLiveDataItems(): LiveData<List<CategoryEntity>> {
        Log.d("ACT", liveDataItems.value.toString())
        return liveDataItems
    }
}

