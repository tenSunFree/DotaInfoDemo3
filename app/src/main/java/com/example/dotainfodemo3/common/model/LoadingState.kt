package com.example.dotainfodemo3.common.model

sealed class LoadingState{

    object Loading: LoadingState()

    object Idle: LoadingState()
}