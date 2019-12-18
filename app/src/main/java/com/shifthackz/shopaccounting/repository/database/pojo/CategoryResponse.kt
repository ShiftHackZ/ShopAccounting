package com.shifthackz.shopaccounting.repository.database.pojo

import com.google.gson.annotations.SerializedName
import com.shifthackz.shopaccounting.repository.database.entity.CategoryEntity

class CategoryResponse {
    @SerializedName("records")
    var records: List<CategoryEntity>? = null
}