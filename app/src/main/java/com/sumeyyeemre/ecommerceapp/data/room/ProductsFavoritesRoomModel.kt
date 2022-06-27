package com.sumeyyeemre.ecommerceapp.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productsfavoritesdatabase")
data class ProductsFavoritesRoomModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var productsId: Int=0,

    @ColumnInfo(name = "category")
    var category:String?,

    @ColumnInfo(name = "count")
    var count:Int?,

    @ColumnInfo(name = "description")
    var description:String?,

    @ColumnInfo(name = "image")
    var image:String?,

    @ColumnInfo(name = "price")
    var price:Double?,

    @ColumnInfo(name = "rate")
    var rate:Float?,

    @ColumnInfo(name = "sale_state")
    var sale_state:Int?,

    @ColumnInfo(name = "title")
    var title:String?,

    @ColumnInfo(name = "user")
    var user:String?,
)
