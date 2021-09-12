package com.example.dotainfodemo3.bill.model

import com.example.dotainfodemo3.hero_datasource.local.bill.BillLocal
import com.example.dotainfodemo3.hero_datasource.remote.bill.BillRemote
import com.squareup.sqldelight.db.SqlDriver

data class BillInteractors(
    val repository: BillRepository
) {
    companion object Factory {
        fun build(sqlDriver: SqlDriver): BillInteractors {
            val remote = BillRemote.build()
            val local = BillLocal.build(sqlDriver)
            return BillInteractors(
                repository = BillRepository(
                    local = local,
                    remote = remote,
                ),
            )
        }
    }
}









