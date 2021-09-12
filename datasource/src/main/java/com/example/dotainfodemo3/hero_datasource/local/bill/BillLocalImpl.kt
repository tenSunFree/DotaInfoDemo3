package com.example.dotainfodemo3.hero_datasource.local.bill

import com.example.dotainfodemo3.datasource.BaseDb
import comexampledotainfodemo3herodatasource.BillDbQueries

class BillLocalImpl(
    db: BaseDb,
) : BillLocal {

    private var billDbQueries: BillDbQueries = db.billDbQueries

    override suspend fun selectBillPhotoAll(): List<BillPhotoBean> {
        return billDbQueries.selectBillPhotoAll()
            .executeAsList().map { it.toBean() }
    }

    override suspend fun insertBillPhoto(bean: BillPhotoBean) {
        return bean.run {
            billDbQueries.insertBillPhoto(
                id = id.toLong(),
                albumId = 1,
                title = title,
                url = url,
                thumbnailUrl = thumbnailUrl
            )
        }
    }

    override suspend fun insertBillPhotoAll(beanList: List<BillPhotoBean>) {
        for (bean in beanList) {
            try {
                insertBillPhoto(bean)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}














