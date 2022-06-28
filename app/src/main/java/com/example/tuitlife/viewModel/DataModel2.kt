package com.example.tuitlife.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class DataModel2 :ViewModel() {
    val message:LiveData<Int> = MutableLiveData()
    init {

    }

    private suspend fun getData() {
        return withContext(Dispatchers.IO) {

        }
    }
}