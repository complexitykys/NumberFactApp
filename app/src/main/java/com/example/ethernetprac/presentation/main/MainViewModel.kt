package com.example.ethernetprac.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ethernetprac.domain.model.NumberData
import com.example.ethernetprac.domain.repository.GetRandomNumberSpec
import com.example.ethernetprac.domain.repository.NumberRepository
import com.example.ethernetprac.domain.repository.ObserveNumberFactSpec
import com.example.ethernetprac.domain.repository.RetrieveNumberFactSpec
import com.example.ethernetprac.presentation.base.ViewModelNavigator
import com.example.ethernetprac.presentation.base.ViewModelNavigatorImpl
import com.example.ethernetprac.presentation.base.navigation.NavigatorCommand
import com.example.ethernetprac.presentation.model.NumberFactUi
import com.example.ethernetprac.utils.ExecutionResult
import com.example.ethernetprac.utils.Failure
import com.example.ethernetprac.utils.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: NumberRepository,
    private val viewModelNavigator: ViewModelNavigatorImpl
) : ViewModel(), ViewModelNavigator by viewModelNavigator {

    private val _numberFact = Channel<ExecutionResult<NumberData, String>>()

    val numberFact: StateFlow<ExecutionResult<NumberData, String>?> =
        _numberFact.receiveAsFlow().stateIn(viewModelScope, SharingStarted.Eagerly, null)

    val facts = repository.observe(ObserveNumberFactSpec())
        .map { list -> list.map { NumberFactUi(it.number, it.fact) } }

    fun fetchNumberFact(number: Long) {
        viewModelScope.launch {
            val result: ExecutionResult<NumberData, String> = try {
                val fact = repository.retrieve(RetrieveNumberFactSpec(number.toString()))
                Success(fact)
            } catch (e: Exception) {
                Failure(error = "Error: ${e.message}")
            }
            _numberFact.send(result)
        }
    }

    fun fetchRandomNumberFact() {
        viewModelScope.launch {
            val result: ExecutionResult<NumberData, String> = try {
                val fact = repository.retrieve(GetRandomNumberSpec())
                Success(fact)
            } catch (e: Exception) {
                Failure(error = "Error: ${e.message}")
            }
            _numberFact.send(result)
        }
    }

    fun onNumberFactClicked(number: NumberFactUi) {
        val command = MainFragmentDirections.actionMainFragmentToDetailFragment(number)
        navigate(NavigatorCommand.ToDirection(command))
    }

    override fun onCleared() {
        super.onCleared()
        viewModelNavigator.close()
        _numberFact.close()
    }
}
