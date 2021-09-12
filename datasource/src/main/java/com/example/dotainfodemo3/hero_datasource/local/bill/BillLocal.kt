package com.example.dotainfodemo3.hero_datasource.local.bill

import com.example.dotainfodemo3.datasource.BaseDb
import com.squareup.sqldelight.db.SqlDriver

interface BillLocal {

    suspend fun selectBillPhotoAll(): List<BillPhotoBean>

    suspend fun insertBillPhoto(bean: BillPhotoBean)

    suspend fun insertBillPhotoAll(beanList: List<BillPhotoBean>)

    companion object Factory {
        fun build(sqlDriver: SqlDriver): BillLocal {
            return BillLocalImpl(BaseDb(sqlDriver))
        }
        val schema: SqlDriver.Schema = BaseDb.Schema

        const val dbName: String = "base.db"
    }
}



















