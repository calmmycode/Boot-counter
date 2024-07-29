package com.calmmycode.bootcounter.data.feature.boot

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BootRecordDao {
    @Insert
    fun add(vararg bootRecords: BootRecordEntity)

    @Query("SELECT * FROM BootRecordEntity")
    fun getAll(): List<BootRecordEntity>
}