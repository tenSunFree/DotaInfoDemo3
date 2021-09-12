package com.example.dotainfodemo3.common.model

sealed class DataState<T> {

    data class Error<T>(
        val queueState: QueueState
    ): DataState<T>()

    data class Success<T>(
        val data: T? = null
    ): DataState<T>()

    data class Loading<T>(
        val loadingState: LoadingState = LoadingState.Idle
    ): DataState<T>()
}