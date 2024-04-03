package com.example.ethernetprac.presentation.base.navigation

import androidx.navigation.NavDirections

sealed interface NavigatorCommand {
    class ToDirection(val direction: NavDirections) : NavigatorCommand
    data object ToBack: NavigatorCommand
}