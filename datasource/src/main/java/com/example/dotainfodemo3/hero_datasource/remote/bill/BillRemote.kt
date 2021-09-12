package com.example.dotainfodemo3.hero_datasource.remote.bill

import com.example.dotainfodemo3.hero_datasource.local.bill.BillPhotoBean
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*

interface BillRemote {

    suspend fun getPhotos(): List<BillPhotoBean>

    companion object Factory {

        var baseUrl = "https://jsonplaceholder.typicode.com"

        fun build(): BillRemote {
            return BillRemoteImpl(
                client = HttpClient(Android) {
                    install(JsonFeature) {
                        serializer = KotlinxSerializer(
                            kotlinx.serialization.json.Json {
                                ignoreUnknownKeys = true
                            }
                        )
                    }
                }
            )
        }
    }
}