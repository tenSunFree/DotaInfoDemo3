package com.example.dotainfodemo3.hero_datasource.remote.bill

import com.example.dotainfodemo3.hero_datasource.local.bill.BillPhotoBean
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class BillPhotoDto(
    @SerialName("albumId")
    val albumId: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("thumbnailUrl")
    val thumbnailUrl: String,
    @SerialName("title")
    val title: String,
    @SerialName("url")
    val url: String
)

fun BillPhotoDto.toBean(): BillPhotoBean {
    return BillPhotoBean(
        albumId = albumId, id = id, thumbnailUrl = thumbnailUrl,
        title = title, url = url
    )
}
