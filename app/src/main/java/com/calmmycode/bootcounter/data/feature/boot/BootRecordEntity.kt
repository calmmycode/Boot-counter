package com.calmmycode.bootcounter.data.feature.boot

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BootRecordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val timestamp: Long,
)