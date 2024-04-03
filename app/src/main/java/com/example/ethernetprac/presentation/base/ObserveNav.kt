package com.example.ethernetprac.presentation.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.ethernetprac.presentation.base.navigation.NavigatorCommand
import kotlinx.coroutines.launch

fun ViewModelNavigator.observeNavigation(lifecycleOwner: LifecycleOwner, fragment: Fragment) {
    val navController = fragment.findNavController()
    lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            navigation.collect {
                when (it) {
                    is NavigatorCommand.ToDirection -> navController.navigate(it.direction)
                    is NavigatorCommand.ToBack -> navController.navigateUp()
                }
            }
        }
    }
}