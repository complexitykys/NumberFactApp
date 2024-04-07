package com.example.ethernetprac.presentation.detail

import androidx.lifecycle.ViewModel
import com.example.ethernetprac.presentation.base.ViewModelNavigator
import com.example.ethernetprac.presentation.base.ViewModelNavigatorImpl
import com.example.ethernetprac.presentation.base.navigateBack
import com.example.ethernetprac.presentation.model.NumberFactUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val viewModelNavigator: ViewModelNavigatorImpl
) : ViewModel(), ViewModelNavigator by viewModelNavigator {
    private val _numberDetail = MutableStateFlow<NumberFactUi?>(null)
    val numberDetail: StateFlow<NumberFactUi?> = _numberDetail

    fun setNumber(numberFactUi: NumberFactUi) {
        _numberDetail.value = numberFactUi
    }

    fun onBackPressed() {
        navigateBack()
    }
}