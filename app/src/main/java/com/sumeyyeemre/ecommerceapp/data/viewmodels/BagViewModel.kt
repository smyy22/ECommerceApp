package com.sumeyyeemre.ecommerceapp.data.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sumeyyeemre.ecommerceapp.data.model.ProductModel
import com.sumeyyeemre.ecommerceapp.data.repository.ProductRepository
import com.sumeyyeemre.ecommerceapp.data.room.ProductsFavoritesRoomModel

class BagViewModel(context: Context):ViewModel() {
    private var bagRepo=ProductRepository(context)

    private var _bagList=MutableLiveData<List<ProductModel>>()
    val bagList:LiveData<List<ProductModel>>
    get()=_bagList





    init {
        _bagList=bagRepo.bagProductList

    }

    fun getBagProducts(user:String){
        bagRepo.getBagProducts(user)
        _bagList=bagRepo.bagProductList
    }



}