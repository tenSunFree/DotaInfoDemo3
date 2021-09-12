package com.example.dotainfodemo3.common.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.dotainfodemo3.common.model.Queue
import com.example.dotainfodemo3.common.model.LoadingState
import com.example.dotainfodemo3.common.model.QueueState

@Composable
fun QueueContent(
    queue: Queue<QueueState> = Queue(mutableListOf()),
    onRemoveHeadFromQueue: () -> Unit,
    state: LoadingState = LoadingState.Idle,
    content: @Composable () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            content()
            if (!queue.isEmpty()) {
                queue.peek()?.let { state ->
                    ErrorQueueDialog(
                        modifier = Modifier
                            .fillMaxWidth(0.9f),
                        title = state.title,
                        description = state.description,
                        onRemoveHeadFromQueue = onRemoveHeadFromQueue
                    )
                }
            }
            if (state is LoadingState.Loading) CircularIndeterminateProgressBar()
        }
    }
}











