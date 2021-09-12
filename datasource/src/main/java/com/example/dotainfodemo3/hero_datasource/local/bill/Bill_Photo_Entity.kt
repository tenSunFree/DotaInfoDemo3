package com.example.dotainfodemo3.hero_datasource.local.bill

import comexampledotainfodemo3herodatasource.Bill_Photo_Entity

fun Bill_Photo_Entity.toBean(): BillPhotoBean {
    return BillPhotoBean(
        id = id.toInt(),
        albumId = albumId.toInt(),
        title = title.toString(),
        url = url.toString(),
        thumbnailUrl = thumbnailUrl.toString()
    )
}












