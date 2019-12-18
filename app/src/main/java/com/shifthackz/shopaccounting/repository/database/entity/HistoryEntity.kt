package com.shifthackz.shopaccounting.repository.database.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "history")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    var id: Int,
    @SerializedName("time")
    var time: Long,
    @SerializedName("product_id")
    var productId: Int,
    @SerializedName("old_price")
    var oldPrice: Float,
    @SerializedName("old_count")
    var oldCount: Int,
    @SerializedName("reason")
    var reason: String,
    @SerializedName("current_name")
    var name: String
)