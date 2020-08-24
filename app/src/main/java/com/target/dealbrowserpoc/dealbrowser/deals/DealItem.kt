package com.target.dealbrowserpoc.dealbrowser.deals

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DealItem(

        @SerializedName("index")
        var index: Int,

        @SerializedName("guid")
        var id: String,

        @SerializedName("title")
        var title: String,

        @SerializedName("description")
        var description: String,

        @SerializedName("price")
        var originalPrice: String,

        @SerializedName("salePrice")
        var salePrice: String,

        @SerializedName("image")
        var image: Int,

        @SerializedName("aisle")
        var aisle: String): Parcelable{

    fun getProductImage(context: Context): Bitmap {
        return BitmapFactory.decodeResource(context.resources, image)
    }

    override fun toString(): String {
        return title
    }

}