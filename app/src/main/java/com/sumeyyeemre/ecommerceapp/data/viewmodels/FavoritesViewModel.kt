package com.sumeyyeemre.ecommerceapp.data.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sumeyyeemre.ecommerceapp.data.repository.ProductRepository
import com.sumeyyeemre.ecommerceapp.data.room.ProductsFavoritesRoomModel

class FavoritesViewModel(context: Context) {
    private val favoriteRepo= ProductRepository(context)

    private var _isProductAddedFavorite = MutableLiveData<Boolean>()
    val isProductAddedFavorite: LiveData<Boolean>
        get() = _isProductAddedFavorite

    private var _productsFavoriteList = MutableLiveData<List<ProductsFavoritesRoomModel>>()
    val productsFavoriteList: LiveData<List<ProductsFavoritesRoomModel>>
        get() = _productsFavoriteList


    init {
        getProductsFavorites()
        _isProductAddedFavorite=favoriteRepo.isProductAddedFavorite
    }


    private fun getProductsFavorites(){
        favoriteRepo.ProductsFavorites()
        _productsFavoriteList=favoriteRepo.productsFavoriteList
    }

    fun addProducttoFavorite(productModel: ProductsFavoritesRoomModel){
        favoriteRepo.addProducttoFavorite(productModel)
    }





}