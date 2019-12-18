package com.shifthackz.shopaccounting.repository.database.pojo

import com.google.gson.annotations.SerializedName
import com.shifthackz.shopaccounting.repository.database.entity.HistoryEntity

class HistoryResponse {
    @SerializedName("records")
    var records: List<HistoryEntity>? = null
}