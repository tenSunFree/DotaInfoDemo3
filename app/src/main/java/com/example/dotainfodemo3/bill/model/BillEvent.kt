package com.example.dotainfodemo3.bill.model

import com.example.dotainfodemo3.common.model.QueueState

sealed class BillEvent {

    object GetPhotos : BillEvent()

    object OnRemoveHeadFromQueue : BillEvent()

    data class OnError(val state: QueueState) : BillEvent()
}
