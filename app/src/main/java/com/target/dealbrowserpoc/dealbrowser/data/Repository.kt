package com.target.dealbrowserpoc.dealbrowser.data

import com.target.dealbrowserpoc.dealbrowser.data.entities.DealEntity
import com.target.dealbrowserpoc.dealbrowser.service.model.DealItem
import io.reactivex.Single
import javax.inject.Inject

class Repository @Inject constructor(
        private val dealDatabase: DealDatabase
) {

    fun getDetails(dealId: String) : Single<DealEntity?> {
        return dealDatabase.dealDao().getDealDetails(dealId)
    }
}