package com.target.dealbrowserpoc.dealbrowser.service

import com.target.dealbrowserpoc.dealbrowser.service.model.DealListResponse
import io.reactivex.Single
import retrofit2.http.GET

interface DealService {

    @GET("deals")
    fun getDeals(): Single<DealListResponse>
}