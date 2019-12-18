package com.shifthackz.shopaccounting.domain

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.LiveData
import com.shifthackz.shopaccounting.presentation.widget.SingleLiveEvent
import com.shifthackz.shopaccounting.repository.AppRepository
import com.shifthackz.shopaccounting.repository.database.entity.ProductEntity

class SingleProductViewModel(application: Application, private val repository: AppRepository) : BaseViewModel(application) {

    private val liveDataItem = SingleLiveEvent<ProductEntity>()

    @SuppressLint("CheckResult")
    fun getItem(id: Int) {
        repository.getProductById(id)?.subscribe { entity -> liveDataItem.value = entity }
    }

    @SuppressLint("CheckResult")
    fun updateItem(id: Int, name: String, price: Float, desc: String, count: Int, reason: String) {
        repository.updateProductById(id, name, price, desc, count, reason)?.subscribe { entity -> liveDataItem.value = entity}
    }

    fun deleteItem(id: Int) {
        repository.deleteProductById(id).subscribe()
    }

    fun addProduct(productEntity: ProductEntity) {
        repository.addProduct(productEntity).subscribe()
    }

    fun getLiveDataItem(): LiveData<ProductEntity> {
        return liveDataItem
    }
}