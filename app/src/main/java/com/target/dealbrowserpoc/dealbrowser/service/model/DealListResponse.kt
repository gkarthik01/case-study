package com.target.dealbrowserpoc.dealbrowser.service.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DealListResponse (
        @SerializedName("data")
        var deals: List<DealItem>? = null,

        @SerializedName("id")
        var id: String? = null,

        @SerializedName("type")
        var type: String? = null) : Parcelable