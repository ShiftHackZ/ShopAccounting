package com.shifthackz.shopaccounting.domain

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.LiveData
import com.shifthackz.shopaccounting.presentation.widget.SingleLiveEvent
import com.shifthackz.shopaccounting.repository.AppRepository
import com.shifthackz.shopaccounting.repository.database.entity.HistoryEntity

class HistoryViewModel(application: Application, private val repository: AppRepository) : BaseViewModel(application) {
    private val liveDataItems = SingleLiveEvent<List<HistoryEntity>>()

    @SuppressLint("CheckResult")
    fun getAllItems() {
        repository.getHistory()?.subscribe { history -> liveDataItems.value = history }
    }

    fun getLiveDataItems(): LiveData<List<HistoryEntity>> {
        return liveDataItems
    }
}