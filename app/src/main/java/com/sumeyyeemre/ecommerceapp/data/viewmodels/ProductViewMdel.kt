package com.sumeyyeemre.ecommerceapp.data.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sumeyyeemre.ecommerceapp.data.model.ProductModel
import com.sumeyyeemre.ecommerceapp.data.repository.ProductRepository
import retrofit2.Callback

class ProductViewModel(context: Context): ViewModel() {

    private var productsRepo= ProductRepository(context)

    private var _productsList= MutableLiveData<List<ProductModel>>()
    val productsList: LiveData<List<ProductModel>>
        get()=_productsList

    private var _bagProduct=MutableLiveData<List<ProductModel>>()
    val bagProduct:LiveData<List<ProductModel>>
    get()=_bagProduct

    init {
        getProducts()
        _bagProduct=productsRepo.productList
    }


    private fun getProducts(){
        productsRepo.products()
        _productsList=productsRepo.productList
    }

     fun addToBag(
         user:String,
         title:String,
         price:Double,
         description:String,
         category:String,
         image:String,
         rate: Float,
         count:Int,
         sale_state:Int){
        productsRepo.addToBag(user,title,price,description,category,image,rate,count,sale_state)
     }


}