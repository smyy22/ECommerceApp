package com.sumeyyeemre.ecommerceapp.utils

import com.sumeyyeemre.ecommerceapp.data.retrofit.ProductInterface
import com.sumeyyeemre.ecommerceapp.data.retrofit.RetrofitInstance


class ApiUtils {
    companion object{
        fun getProductsInterface(): ProductInterface {
            return RetrofitInstance.getClient().create(ProductInterface::class.java)
        }
    }
}