package com.shifthackz.shopaccounting.repository.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.shifthackz.shopaccounting.repository.database.dao.CategoryDao
import com.shifthackz.shopaccounting.repository.database.dao.HistoryDao
import com.shifthackz.shopaccounting.repository.database.dao.ProductDao
import com.shifthackz.shopaccounting.repository.database.entity.CategoryEntity
import com.shifthackz.shopaccounting.repository.database.entity.HistoryEntity
import com.shifthackz.shopaccounting.repository.database.entity.ProductEntity

@Database(entities = [CategoryEntity::class, ProductEntity::class, HistoryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
    abstract fun historyDao(): HistoryDao
}