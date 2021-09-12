package com.example.dotainfodemo3.bill.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dotainfodemo3.common.model.Queue
import com.example.dotainfodemo3.bill.model.BillEvent
import com.example.dotainfodemo3.bill.model.BillRepository
import com.example.dotainfodemo3.bill.model.BillState
import com.example.dotainfodemo3.common.model.DataState
import com.example.dotainfodemo3.common.model.QueueState
import com.example.dotainfodemo3.common.util.Loggerg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BillViewModel
@Inject
constructor(
    private val billRepository: BillRepository
) : ViewModel() {

    val state: MutableState<BillState> = mutableStateOf(BillState())

    init {
        onTriggerEvent(BillEvent.GetPhotos)
    }

    fun onTriggerEvent(event: BillEvent) {
        when (event) {
            is BillEvent.GetPhotos -> {
                getPhotos()
            }
            is BillEvent.OnRemoveHeadFromQueue -> {
                removeHeadMessage()
            }
            is BillEvent.OnError -> {
                appendToMessageQueue(event.state)
            }
        }
    }

    private fun getPhotos() {
        billRepository.getPhotos().onEach { state ->
            when (state) {
                is DataState.Loading -> {
                    this.state.value = this.state.value.copy(loading = state.loadingState)
                }
                is DataState.Success -> {
                    this.state.value = this.state.value.copy(photos = state.data ?: listOf())
                }
                is DataState.Error -> {
                    appendToMessageQueue(state.queueState)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun appendToMessageQueue(queueState: QueueState) {
        val queue = state.value.errorQueue
        queue.add(queueState)
        state.value = state.value.copy(errorQueue = Queue(mutableListOf())) // force recompose
        state.value = state.value.copy(errorQueue = queue)
    }

    private fun removeHeadMessage() {
        try {
            val queue = state.value.errorQueue
            queue.remove()
            state.value = state.value.copy(errorQueue = Queue(mutableListOf())) // force recompose
            state.value = state.value.copy(errorQueue = queue)
        } catch (e: Exception) {
            Loggerg.d("BillViewModel, removeHeadMessage, e: ${e.message}")
        }
    }
}