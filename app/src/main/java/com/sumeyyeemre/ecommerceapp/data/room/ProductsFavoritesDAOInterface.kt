package com.sumeyyeemre.ecommerceapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductsFavoritesDAOInterface {
    @Insert
    fun addFavorites(productsFavoritesRoomModel: ProductsFavoritesRoomModel)

    @Query("SELECT * FROM productsfavoritesdatabase")
    fun getProductsFavorites(): List<ProductsFavoritesRoomModel>?

    @Query("SELECT image FROM productsfavoritesdatabase")
    fun getProductsImage(): List<String>?


}