package com.calmmycode.bootcounter.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.calmmycode.bootcounter.data.feature.LocalDatabaseConstants
import com.calmmycode.bootcounter.data.feature.boot.BootRecordDao
import com.calmmycode.bootcounter.data.feature.boot.BootRecordEntity

@Database(
    entities = [
        BootRecordEntity::class
    ],
    version = LocalDatabaseConstants.dbVersion,
    exportSchema = false,
)

abstract class LocalDatabase : RoomDatabase() {
    abstract fun bootRecord(): BootRecordDao
}