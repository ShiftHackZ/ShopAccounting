package com.shifthackz.shopaccounting.repository.database.pojo

import com.google.gson.annotations.SerializedName
import com.shifthackz.shopaccounting.repository.database.entity.ProductEntity

class ProductsResponse {
    @SerializedName("records")
    var records: List<ProductEntity>? = null
}