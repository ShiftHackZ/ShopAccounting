package com.shifthackz.shopaccounting.repository.database.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("price")
    var price: Float,
    @SerializedName("images")
    var image: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("category_id")
    var catId: Int,
    @SerializedName("count")
    var count: Int
)