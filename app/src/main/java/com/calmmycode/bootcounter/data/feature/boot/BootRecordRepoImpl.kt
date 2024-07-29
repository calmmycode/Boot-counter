package com.calmmycode.bootcounter.data.feature.boot

import com.calmmycode.bootcounter.domain.feature.boot.repo.BootRecordRepo
import javax.inject.Inject

class BootRecordRepoImpl @Inject constructor(
    private val bootRecordDao: BootRecordDao
) : BootRecordRepo {
    override fun add(item: BootRecordEntity) {
        bootRecordDao.add(item)
    }

    override fun getAll(): List<BootRecordEntity> {
        return bootRecordDao.getAll()
    }
}