package com.example.tuitlife.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel :ViewModel() {
    val message:MutableLiveData<Int> by lazy{
        MutableLiveData<Int>()
    }
}