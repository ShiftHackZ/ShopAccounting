package com.shifthackz.shopaccounting.domain

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.LiveData
import com.shifthackz.shopaccounting.presentation.widget.SingleLiveEvent
import com.shifthackz.shopaccounting.repository.AppRepository
import com.shifthackz.shopaccounting.repository.database.entity.ProductEntity

class ProductsViewModel(application: Application, private val repository: AppRepository)
    : BaseViewModel(application) {

    private val liveDataItems = SingleLiveEvent<List<ProductEntity>>()

    @SuppressLint("CheckResult")
    fun getAllItems(catId: Int) {
        repository.getProductsByCategoryId(catId)?.subscribe { products -> liveDataItems.postValue(products) }
    }

    fun getLiveDataItems(): LiveData<List<ProductEntity>> {
        return liveDataItems
    }
}