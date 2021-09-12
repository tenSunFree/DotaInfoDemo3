package com.example.dotainfodemo3.bill.model

import com.example.dotainfodemo3.common.model.Queue
import com.example.dotainfodemo3.common.model.QueueState
import com.example.dotainfodemo3.common.model.LoadingState
import com.example.dotainfodemo3.hero_datasource.local.bill.BillPhotoBean

data class BillState(
    val loading: LoadingState = LoadingState.Idle,
    val photos: List<BillPhotoBean> = listOf(),
    val errorQueue: Queue<QueueState> = Queue(mutableListOf())
)
