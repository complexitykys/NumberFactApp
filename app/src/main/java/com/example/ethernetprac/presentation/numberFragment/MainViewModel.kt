package com.example.ethernetprac.presentation.numberFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ethernetprac.presentation.data.NumberApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val numberApiService = NumberApiService()
    private val _numberFact = MutableStateFlow("")
    val numberFact: StateFlow<String> = _numberFact

    fun fetchNumberFact(number: Long) {
        viewModelScope.launch {
            try {
                val fact = numberApiService.fetchNumberFact(number)
                _numberFact.value = fact
            } catch (e: Exception) {
                // exception
                _numberFact.value = "Error: ${e.message}"
            }
        }
    }
}