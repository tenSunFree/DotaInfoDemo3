package com.example.dotainfodemo3.common.di

import android.app.Application
import com.example.dotainfodemo3.bill.model.BillRepository
import com.example.dotainfodemo3.bill.model.BillInteractors
import com.example.dotainfodemo3.hero_datasource.local.bill.BillLocal
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BillModule {

    @Provides
    @Singleton
    @Named("BillAndroidSqlDriver") // in case you had another SQL Delight db
    fun provideAndroidDriver(app: Application): SqlDriver {
        return AndroidSqliteDriver(
            schema = BillLocal.schema,
            context = app,
            name = BillLocal.dbName
        )
    }

    @Provides
    @Singleton
    fun provideHeroInteractors(
        @Named("BillAndroidSqlDriver") driver: SqlDriver,
    ): BillInteractors {
        return BillInteractors.build(driver)
    }

    @Provides
    @Singleton
    fun provideBillRepository(
        interactors: BillInteractors
    ): BillRepository {
        return interactors.repository
    }
}