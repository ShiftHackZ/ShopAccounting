package com.shifthackz.shopaccounting.repository.server

import android.util.Log
import com.shifthackz.shopaccounting.repository.database.entity.HistoryEntity
import com.shifthackz.shopaccounting.repository.database.entity.ProductEntity
import com.shifthackz.shopaccounting.repository.database.pojo.CategoryResponse
import com.shifthackz.shopaccounting.repository.database.pojo.HistoryResponse
import com.shifthackz.shopaccounting.repository.database.pojo.ProductsResponse
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.util.concurrent.TimeUnit

class ServerCommunicator(private val apiService: ApiService) {

    companion object {
        private const val DEFAULT_TIMEOUT = 10
        private const val DEFAULT_RETRY_ATTEMPTS = 4L
    }

    fun getHistory(): Single<Response<HistoryResponse>> {
        return apiService.getHistory()
            .compose(singleTransformer())
            .doOnError { t: Throwable -> Log.d("ServerCommunicator", t.message) }
    }

    fun getCategories(): Single<Response<CategoryResponse>> {
        return apiService.getCategories()
            .compose(singleTransformer())
            .doOnError { t: Throwable -> Log.d("ServerCommunicator", t.message) }
    }

    fun getProductsByCategoryId(catId: Int): Single<Response<ProductsResponse>> {
        val filter = "category_id,eq,$catId"
        return apiService.getProducts(filter)
            .compose(singleTransformer())
            .doOnError { t: Throwable -> Log.d("ServerCommunicator", t.message) }
    }

    fun getProductById(id: Int): Single<ProductEntity> {
        return apiService.getProductById(id)
            .compose(singleTransformer())
            .doOnError { t: Throwable -> Log.d("ServerCommunicator", t.message) }
    }

    fun deleteProductById(id: Int): Single<Response<String>> {
        return apiService.deleteProductById(id)
            .compose(singleTransformer())
            .doOnError { it.printStackTrace() }
    }

    fun addProductItem(productEntity: ProductEntity): Single<Response<String>> {
        return apiService.addProductItem(
            productEntity.name,
            productEntity.image,
            productEntity.price,
            productEntity.description,
            productEntity.catId,
            productEntity.count)
            .compose(singleTransformer())
            .doOnError { it.printStackTrace() }
    }

    fun updateProductById(productEntity: ProductEntity): Single<Response<String>> {
        return apiService.updateProductById(
            productEntity.id,
            productEntity.name,
            productEntity.price,
            productEntity.image,
            productEntity.description,
            productEntity.catId,
            productEntity.count)
            .compose(singleTransformer())
            .doOnError { t: Throwable -> Log.d("ServerCommunicator", t.message)  }
    }

    fun addHistoryItem(historyEntity: HistoryEntity): Single<Response<String>> {
        return apiService.addHistoryItem(
            historyEntity.time,
            historyEntity.productId,
            historyEntity.oldPrice,
            historyEntity.oldCount,
            historyEntity.reason,
            historyEntity.name)
            .compose(singleTransformer())
            .doOnError { t: Throwable -> Log.d("ServerCommunicator", t.message) }
    }

    private fun <T> singleTransformer(): SingleTransformer<T, T> = SingleTransformer {
        it.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .timeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .retry(DEFAULT_RETRY_ATTEMPTS)
    }

    private fun <T> observableTransformer(): ObservableTransformer<T, T> = ObservableTransformer {
        it.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .timeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .retry(DEFAULT_RETRY_ATTEMPTS)
    }
}
