package com.target.dealbrowserpoc.dealbrowser.deals.ui.viewModel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.target.dealbrowserpoc.dealbrowser.data.DealDatabase
import com.target.dealbrowserpoc.dealbrowser.data.entities.DealEntity
import com.target.dealbrowserpoc.dealbrowser.service.model.DealItem
import com.target.dealbrowserpoc.dealbrowser.service.DealService
import com.target.dealbrowserpoc.dealbrowser.utils.RxScheduler
import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

class DealListViewModel @ViewModelInject constructor(
        private val dealService: DealService,
        private val rxScheduler: RxScheduler,
        private val dealDatabase: DealDatabase) : BaseViewModel() {
    val deals: MutableLiveData<List<DealItem>> = MutableLiveData()
    private val compositeDisposable = CompositeDisposable()

    fun getDeals() {
        dealService.getDeals()
                .doOnSubscribe({
                    isLoading.postValue(true)
                    compositeDisposable.add(it)
                })
                .flatMapCompletable {
                    deals.postValue(it.deals)
                    addToDatabase(it.deals)
                }
                .doOnSubscribe({compositeDisposable.add(it)})
                .subscribeOn(rxScheduler.io())
                .observeOn(rxScheduler.mainThread())
                .subscribe(
                        { isLoading.postValue(false) },
                        {
                            isLoading.postValue(false)
                            error.postValue(Error)
                        })
    }

    private fun addToDatabase(deals: List<DealItem>?): Completable {
        val entities = deals?.map { dealItem: DealItem ->
            DealEntity(dealItem.index, dealItem.id,
                    dealItem.title, dealItem.description, dealItem.originalPrice,
                    dealItem.salePrice, dealItem.image, dealItem.aisle)
        }
        return dealDatabase.dealDao().insertAll(entities)
    }

    override fun onCleared() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}