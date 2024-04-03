package com.example.ethernetprac.presentation.base

import com.example.ethernetprac.presentation.base.navigation.NavigatorCommand
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

interface ViewModelNavigator {
    val navigation: Flow<NavigatorCommand>

    fun navigate(navCommand: NavigatorCommand)
}

class ViewModelNavigatorImpl : ViewModelNavigator {

    private val channel: Channel<NavigatorCommand> = Channel()

    override val navigation: Flow<NavigatorCommand> = channel.receiveAsFlow()

    override fun navigate(navCommand: NavigatorCommand) {
        channel.trySend(navCommand)
    }

    fun close() {
        channel.close()
    }
}

fun ViewModelNavigator.navigateBack() {
    navigate(NavigatorCommand.ToBack)
}

