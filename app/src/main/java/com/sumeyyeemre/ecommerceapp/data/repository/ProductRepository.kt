package com.sumeyyeemre.ecommerceapp.data.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.sumeyyeemre.ecommerceapp.data.model.CRUDResponse
import com.sumeyyeemre.ecommerceapp.data.model.ProductModel
import com.sumeyyeemre.ecommerceapp.data.retrofit.ProductInterface
import com.sumeyyeemre.ecommerceapp.data.room.ProductsFavoritesDAOInterface
import com.sumeyyeemre.ecommerceapp.data.room.ProductsFavoritesRoomDatabase
import com.sumeyyeemre.ecommerceapp.data.room.ProductsFavoritesRoomModel
import com.sumeyyeemre.ecommerceapp.utils.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository(context: Context){
    var productList= MutableLiveData<List<ProductModel>>()

    var categoryList=MutableLiveData<List<String>>()

    var bagProductList=MutableLiveData<List<ProductModel>>()

    var productsFavoriteList = MutableLiveData<List<ProductsFavoritesRoomModel>>()

    var isProductAddedFavorite = MutableLiveData<Boolean>()

    private val retrofitInstance: ProductInterface = ApiUtils.getProductsInterface()

    private val productsFavoritesDAOInterface:ProductsFavoritesDAOInterface?=
        ProductsFavoritesRoomDatabase.productsFavoritesRoomDatabase(context)?.productsFavoritesDAOInterface()


    fun products(){
        retrofitInstance.getProductsData().enqueue(object : Callback<List<ProductModel>?> {
            override fun onResponse(
                call: Call<List<ProductModel>?>,
                response: Response<List<ProductModel>?>
            ) {
                response.body()?.let {
                    productList.value=it
                }
            }

            override fun onFailure(call: Call<List<ProductModel>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun addToBag(user:String,title:String,price:Double,description:String,category:String,image:String,rate:Float,count:Int,sale_state:Int){
        retrofitInstance.addToBag(user,title,price,description,category,image,rate,count,sale_state).enqueue(object : Callback<CRUDResponse?> {
            override fun onResponse(call: Call<CRUDResponse?>, response: Response<CRUDResponse?>) {
                response.body()?.let {
                    Log.v("productrepo",it.message)
                }
            }

            override fun onFailure(call: Call<CRUDResponse?>, t: Throwable) {
                Log.v("productrepo",t.message.orEmpty())
            }
        })
    }

    fun getBagProducts(user: String){
        retrofitInstance.getBagProducts(user).enqueue(object : Callback<List<ProductModel>?> {
            override fun onResponse(
                call: Call<List<ProductModel>?>,
                response: Response<List<ProductModel>?>
            ) {
                response.body()?.let {
                    bagProductList.value=it
                }
            }

            override fun onFailure(call: Call<List<ProductModel>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun ProductsFavorites(){
        productsFavoritesDAOInterface?.getProductsFavorites()?.let {
            productsFavoriteList.value=it
        }
    }

    fun addProducttoFavorite(productModel:ProductsFavoritesRoomModel){
        productsFavoritesDAOInterface?.getProductsImage()?.let {
            isProductAddedFavorite.value= if (it.contains(productModel.image).not()){
                productsFavoritesDAOInterface.addFavorites(productModel)
                /*Toast.makeText(this@ProductRepository,
                    "Product is successfully added to favorites",
                    Toast.LENGTH_SHORT).show()*/
                true
            }else{
                Log.v("repofavori","false oluştu.")
                false
            }
        }
    }

    fun categories(){
        retrofitInstance.getCategories().enqueue(object : Callback<List<String>?> {
            override fun onResponse(
                call: Call<List<String>?>,
                response: Response<List<String>?>
            ) {
                response.body().let {
                    categoryList.value=it
                }

            }

            override fun onFailure(call: Call<List<String>?>, t: Throwable) {
                Log.v("categories","${t.message}")
            }
        })
    }



}