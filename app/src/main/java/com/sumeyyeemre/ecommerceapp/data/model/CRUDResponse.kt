package com.sumeyyeemre.ecommerceapp.data.model


import com.google.gson.annotations.SerializedName

data class CRUDResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
)