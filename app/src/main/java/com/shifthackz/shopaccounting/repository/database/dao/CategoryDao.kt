package com.shifthackz.shopaccounting.repository.database.dao

import android.arch.persistence.room.*
import com.shifthackz.shopaccounting.repository.database.entity.CategoryEntity

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    fun getAll(): List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<CategoryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(categoryEntity: CategoryEntity)

    @Update
    fun updateAll(list: List<CategoryEntity>)

}