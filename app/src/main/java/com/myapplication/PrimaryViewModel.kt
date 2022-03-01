package com.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PrimaryViewModel : ViewModel() {
    val selection: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}