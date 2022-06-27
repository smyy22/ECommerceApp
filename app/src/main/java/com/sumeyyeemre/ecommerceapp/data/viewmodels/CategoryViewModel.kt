package com.sumeyyeemre.ecommerceapp.data.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sumeyyeemre.ecommerceapp.data.model.ProductModel
import com.sumeyyeemre.ecommerceapp.data.repository.ProductRepository

class CategoryViewModel(context: Context):ViewModel() {
    private var productsRepo= ProductRepository(context)

    private var _categoryList= MutableLiveData<List<String>>()
    val categoryList: LiveData<List<String>>
        get()=_categoryList

    init {
        getCategories()
    }

    private fun getCategories(){
        productsRepo.categories()
        _categoryList=productsRepo.categoryList
    }
}