package com.target.dealbrowserpoc.dealbrowser.data.entities

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.w3c.dom.Entity

@androidx.room.Entity(tableName = "deals")
data class DealEntity(
        var index: Int,
        @PrimaryKey var id: String,
        var title: String,
        var description: String,
        var originalPrice: String,
        var salePrice: String? = null,
        var image: String,
        var aisle: String)