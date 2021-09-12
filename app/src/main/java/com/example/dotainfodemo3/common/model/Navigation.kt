package com.example.dotainfodemo3.common.model

import androidx.navigation.compose.NamedNavArgument

sealed class Navigation(
    val destination: String, val arguments: List<NamedNavArgument>
) {

    object BillNavigation : Navigation(
        destination = "BillNavigation",
        arguments = emptyList()
    )
}

