package com.target.dealbrowserpoc.dealbrowser.deals.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    companion object {
        const val Error = "Error"
    }
}