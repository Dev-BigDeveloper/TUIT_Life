package com.example.tuitlife.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyViewMode : ViewModel() {
    data class ViewState(
        val someUIProperty: String = "",
        val someOtherUIProperty: Int = 1,
    )

    private val _viewState = MutableStateFlow<ViewState>(ViewState())
    val viewState = _viewState.asStateFlow()
}