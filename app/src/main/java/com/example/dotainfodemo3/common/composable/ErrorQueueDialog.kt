package com.example.dotainfodemo3.common.composable

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ErrorQueueDialog(
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    onRemoveHeadFromQueue: () -> Unit,
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onRemoveHeadFromQueue() },
        title = { Text(text = title) },
        text = { if (description != null) Text(text = description) },
        buttons = {}
    )
}