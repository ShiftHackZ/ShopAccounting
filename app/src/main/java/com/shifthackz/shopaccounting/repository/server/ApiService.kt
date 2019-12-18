package com.shifthackz.shopaccounting.repository.server

import com.shifthackz.shopaccounting.repository.database.entity.ProductEntity
import com.shifthackz.shopaccounting.repository.database.pojo.CategoryResponse
import com.shifthackz.shopaccounting.repository.database.pojo.HistoryResponse
import com.shifthackz.shopaccounting.repository.database.pojo.ProductsResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("/rest/api-v2.php/records/categories")
    fun getCategories(): Single<Response<CategoryResponse>>

    @GET("/rest/api-v2.php/records/products")
    fun getProducts(@Query("filter") filter: String): Single<Response<ProductsResponse>>

    @GET("/rest/api-v2.php/records/products/{id}")
    fun getProductById(@Path("id") id: Int): Single<ProductEntity>

    @DELETE("/rest/api-v2.php/records/products/{id}")
    fun deleteProductById(@Path("id") id: Int): Single<Response<String>>

    @FormUrlEncoded
    @PUT("/rest/api-v1.php/products/{id}")
    fun updateProductById(
        @Path("id") id: Int,
        @Field("name") name: String,
        @Field("price") price: Float,
        @Field("images") image: String,
        @Field("description") desc: String,
        @Field("category_id") catId: Int,
        @Field("count") count: Int
    ): Single<Response<String>>

    @FormUrlEncoded
    @POST("/rest/api-v1.php/products")
    fun addProductItem(
        @Field("name") name: String,
        @Field("images") imageUrl: String,
        @Field("price") price: Float,
        @Field("description") desc: String,
        @Field("category_id") catId: Int,
        @Field("count") count: Int
    ): Single<Response<String>>

    @FormUrlEncoded
    @POST("/rest/api-v1.php/history")
    fun addHistoryItem(
        @Field("time") time: Long,
        @Field("product_id") id: Int,
        @Field("old_price") oldPrice: Float,
        @Field("old_count") oldCount: Int,
        @Field("reason") reason: String,
        @Field("current_name") name: String
    ): Single<Response<String>>

    @GET("/rest/api-v2.php/records/history")
    fun getHistory(): Single<Response<HistoryResponse>>
}
