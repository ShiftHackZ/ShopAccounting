package com.shifthackz.shopaccounting.repository

import com.shifthackz.shopaccounting.repository.database.AppDatabase
import com.shifthackz.shopaccounting.repository.database.entity.CategoryEntity
import com.shifthackz.shopaccounting.repository.database.entity.HistoryEntity
import com.shifthackz.shopaccounting.repository.database.entity.ProductEntity
import com.shifthackz.shopaccounting.repository.server.ServerCommunicator
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppRepository(private val serverCommunicator: ServerCommunicator, private val mainDatabase: AppDatabase) {

    fun getHistory(): Single<List<HistoryEntity>>? {
        return serverCommunicator.getHistory()
            .flatMap { history ->
                history.body()?.records?.let { mainDatabase.historyDao().insertAll(it) }
                Single.just(mainDatabase.historyDao().getAll())
            }
            .doOnError { it.printStackTrace() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getCategories(): Single<List<CategoryEntity>>? {
        return serverCommunicator.getCategories()
            .flatMap { list ->
                list.body()?.records?.let {
                    mainDatabase.categoryDao().insertAll(it)
                }
                Single.just(mainDatabase.categoryDao().getAll())
            }
            .doOnError { it.printStackTrace() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getProductsByCategoryId(catId: Int): Single<List<ProductEntity>>? {
        return serverCommunicator.getProductsByCategoryId(catId)
            .flatMap { products ->
                products.body()?.records?.let {
                    mainDatabase.productDao().insertAll(it)
                }
                Single.just(mainDatabase.productDao().getByCatId(catId))
            }
            .doOnError { it.printStackTrace() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getProductById(id: Int): Single<ProductEntity>? {
        return serverCommunicator.getProductById(id)
            .map {
                mainDatabase.productDao().insert(it)
                val product = mainDatabase.productDao().getById(id)
                product
            }
            .doOnError { it.printStackTrace() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun addProduct(productEntity: ProductEntity): Single<ProductEntity> {
        return serverCommunicator.addProductItem(productEntity)
            .map {
                if (it.isSuccessful) {
                    it.body()?.toInt()?.let {
                        productEntity.id = it
                        val history = HistoryEntity(0,
                            System.currentTimeMillis(),
                            productEntity.id,
                            0f,
                            0,
                            "Добавление нового товара",
                            productEntity.name)
                        addHistoryItem(history)?.subscribe()
                    }
                    mainDatabase.productDao().insert(productEntity)
                }
                mainDatabase.productDao().getById(it.body()!!.toInt())
            }
            .doOnError { it.printStackTrace() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun updateProductById(id: Int, name: String, price: Float, desc: String, count: Int, reason: String): Single<ProductEntity>? {
        var productEntity = mainDatabase.productDao().getById(id)
        var oldPrice = productEntity.price
        val oldCount = productEntity.count
        productEntity.name = name
        productEntity.price = price
        productEntity.description = desc
        productEntity.count = count
        return serverCommunicator.updateProductById(productEntity)
            .map {
                if (it.isSuccessful && it.body() == "1") {
                    mainDatabase.productDao().update(productEntity)
                    val history = HistoryEntity(0,
                        System.currentTimeMillis(),
                        productEntity.id,
                        oldPrice,
                        oldCount,
                        reason,
                        productEntity.name)
                    addHistoryItem(history)?.subscribe()
                }
                val product = mainDatabase.productDao().getById(productEntity.id)
                product
            }
            .doOnError { it.printStackTrace() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun addHistoryItem(history: HistoryEntity): Single<HistoryEntity>? {
        return serverCommunicator.addHistoryItem(history)
            .map {
                if (it.isSuccessful) {
                    it.body()?.toInt()?.let {
                        history.id = it
                    }
                    mainDatabase.historyDao().insert(history)
                }
                val item = mainDatabase.historyDao().getById(it.body()!!.toInt())
                item
            }
            .doOnError { it.printStackTrace() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteProductById(id: Int): Single<String> {
        return serverCommunicator.deleteProductById(id)
            .map {
                if (it.isSuccessful) {
                    it.body()?.toInt()?.let {
                        mainDatabase.productDao().getById(id).apply {
                            val history = HistoryEntity(0,
                                System.currentTimeMillis(),
                                id,
                                price,
                                count,
                                "Удаление карточки товара",
                                name)
                            addHistoryItem(history)?.subscribe()
                        }
                        //mainDatabase.productDao().deleteItem(id)
                    }
                }
                it.body().toString()
            }
            .doOnError { it.printStackTrace() }
            .subscribeOn(Schedulers.io())
            .doOnSuccess { mainDatabase.productDao().deleteItem(id) }
            .observeOn(AndroidSchedulers.mainThread())
    }
}