package com.calmmycode.bootcounter.domain.feature.boot.repo

import com.calmmycode.bootcounter.data.feature.boot.BootRecordEntity

interface BootRecordRepo {
    fun add(item: BootRecordEntity)
    fun getAll() : List<BootRecordEntity>
}