package com.shifthackz.shopaccounting.repository.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.shifthackz.shopaccounting.repository.database.entity.HistoryEntity

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history")
    fun getAll(): List<HistoryEntity>

    @Query("SELECT * FROM history WHERE id = :id")
    fun getById(id: Int): HistoryEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<HistoryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: HistoryEntity)
}