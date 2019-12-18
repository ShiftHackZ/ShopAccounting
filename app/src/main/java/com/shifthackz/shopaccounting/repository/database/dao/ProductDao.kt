package com.shifthackz.shopaccounting.repository.database.dao

import android.arch.persistence.room.*
import com.shifthackz.shopaccounting.repository.database.entity.ProductEntity

@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    fun getAll(): List<ProductEntity>

    @Query("SELECT * FROM products WHERE catId = :catId")
    fun getByCatId(catId: Int): List<ProductEntity>

    @Query("SELECT * FROM products WHERE id = :id")
    fun getById(id: Int): ProductEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<ProductEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(categoryEntity: ProductEntity)

    @Update
    fun updateAll(list: List<ProductEntity>)

    @Query("DELETE FROM products WHERE id = :id")
    fun deleteItem(id: Int)

}