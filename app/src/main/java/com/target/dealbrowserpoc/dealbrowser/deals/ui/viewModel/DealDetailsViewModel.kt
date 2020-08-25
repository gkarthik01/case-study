package com.target.dealbrowserpoc.dealbrowser.deals.ui.viewModel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.room.PrimaryKey
import com.target.dealbrowserpoc.dealbrowser.data.Repository
import com.target.dealbrowserpoc.dealbrowser.data.entities.DealEntity
import com.target.dealbrowserpoc.dealbrowser.service.model.DealItem
import com.target.dealbrowserpoc.dealbrowser.utils.RxScheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import kotlin.IllegalArgumentException

const val Extra_Id = "Extra_Id"

class DealDetailsViewModel @ViewModelInject constructor(
        @Assisted private val savedStateHandle: SavedStateHandle,
        private val repository: Repository,
        private val rxScheduler: RxScheduler) : BaseViewModel() {

    val index = MutableLiveData<Int>()
    val id = MutableLiveData<String>()
    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val originalPrice = MutableLiveData<String>()
    val salePrice= MutableLiveData<String>()
    val image = MutableLiveData<String>()
    val aisle =  MutableLiveData<String>()

    private val dealId: String = savedStateHandle.get<String>(Extra_Id)
            ?: throw IllegalArgumentException("Valid Id is required to shoe details")

    init {
        println("initing")
        getDealDetailsFromRepo().subscribeOn(rxScheduler.io())
                .observeOn(rxScheduler.mainThread())
                .subscribe(Consumer {
                    println(it)
                    println("got from db")
                    it?.let {
                        index.postValue(it.index)
                        id.postValue(it.id)
                        title.postValue(it.title)
                        description.postValue(it.description)
                        originalPrice.postValue(it.originalPrice)
                        salePrice.postValue(it.salePrice)
                        image.postValue(it.image)
                        aisle.postValue(it.aisle)
                    }
                }, Consumer{t -> {
                    println(t.message)
                    error.postValue(Error)
                } })
    }

    private fun getDealDetailsFromRepo() : Single<DealEntity?> {
        println("calling")
        return repository.getDetails(dealId)
    }
}