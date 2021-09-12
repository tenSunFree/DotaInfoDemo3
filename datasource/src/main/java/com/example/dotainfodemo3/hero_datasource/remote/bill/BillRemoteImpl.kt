package com.example.dotainfodemo3.hero_datasource.remote.bill

import com.example.dotainfodemo3.hero_datasource.local.bill.BillPhotoBean
import io.ktor.client.*
import io.ktor.client.request.*

class BillRemoteImpl(
    private val client: HttpClient,
): BillRemote {

    override suspend fun getPhotos(): List<BillPhotoBean> {
        val path = "/photos"
        return client.get<List<BillPhotoDto>> {
            url(BillRemote.baseUrl +path)
        }.map { it.toBean() }
    }
}