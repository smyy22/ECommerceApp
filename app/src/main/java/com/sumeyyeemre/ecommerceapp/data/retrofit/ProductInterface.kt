package com.sumeyyeemre.ecommerceapp.data.retrofit

import com.sumeyyeemre.ecommerceapp.data.model.CRUDResponse
import com.sumeyyeemre.ecommerceapp.data.model.ProductModel
import retrofit2.Call
import retrofit2.http.*

interface ProductInterface {

    @GET("get_products.php")
    fun getProductsData(): Call<List<ProductModel>>

    @GET("get_categories.php")
    fun getCategories(): Call<List<String>>


    @POST("add_to_bag.php")
    @FormUrlEncoded
    fun addToBag(
        @Field("user") user: String,
        @Field("title") title: String,
        @Field("price") price: Double,
        @Field("description") description: String,
        @Field("category") category: String,
        @Field("image") image: String,
        @Field(" rate") rate: Float,
        @Field("count") count: Int,
        @Field("sale_state") sale_state: Int
    ): Call<CRUDResponse>


    @POST("get_bag_products_by_user.php")
    @FormUrlEncoded
    fun getBagProducts(
        @Field("user") user: String
    ): Call<List<ProductModel>>

}