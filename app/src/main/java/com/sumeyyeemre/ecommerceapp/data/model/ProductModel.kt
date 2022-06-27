package com.sumeyyeemre.ecommerceapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductModel(
    @SerializedName("category")
    @Expose
    val category: String,
    @SerializedName("count")
    @Expose
    val count: Int,
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("image")
    @Expose
    val image: String,
    @SerializedName("price")
    @Expose
    val price: Double,
    @SerializedName("rate")
    @Expose
    val rate: Float,
    @SerializedName("sale_state")
    @Expose
    val sale_state: Int,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("user")
    @Expose
    val user: String
):Parcelable