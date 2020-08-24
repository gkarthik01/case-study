package com.target.dealbrowserpoc.dealbrowser.ui.viewModel

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlin.IllegalArgumentException

const val Extra_Id = "Extra_Id"

class DealsViewModel @ViewModelInject constructor(
        @Assisted private val savedStateHandle: SavedStateHandle) : ViewModel() {

    var dealId: String = savedStateHandle.get<String>(Extra_Id)
            ?: throw IllegalArgumentException("Valid Id is required to shoe details")

    init {

    }
}