package com.calmmycode.bootcounter.di.feature

import android.content.Context
import androidx.room.Room
import com.calmmycode.bootcounter.data.LocalDatabase
import com.calmmycode.bootcounter.data.feature.LocalDatabaseConstants
import com.calmmycode.bootcounter.data.feature.boot.BootRecordDao
import com.calmmycode.bootcounter.data.feature.boot.BootRecordRepoImpl
import com.calmmycode.bootcounter.domain.feature.boot.repo.BootRecordRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideBootRecordDao(appDatabase: LocalDatabase): BootRecordDao {
        return appDatabase.bootRecord()
    }

    @Provides
    fun provideBootRecordRepo(bootRecordDao: BootRecordDao): BootRecordRepo {
        return BootRecordRepoImpl(bootRecordDao)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): LocalDatabase {
        return Room.databaseBuilder(
            appContext,
            LocalDatabase::class.java,
            LocalDatabaseConstants.dbName
        ).build()
    }
}