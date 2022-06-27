package com.sumeyyeemre.ecommerceapp.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sumeyyeemre.ecommerceapp.data.room.ProductsFavoritesRoomModel
import com.sumeyyeemre.ecommerceapp.databinding.ItemFavoritesBinding

class FavoritesAdapter : RecyclerView.Adapter<FavoritesAdapter.FavoritesItem>() {

    private val favoritesproductList= ArrayList<ProductsFavoritesRoomModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritesAdapter.FavoritesItem {
        val itemFavoritesBinding=ItemFavoritesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavoritesItem(itemFavoritesBinding)
    }

    override fun onBindViewHolder(holder: FavoritesAdapter.FavoritesItem, position: Int) {
        holder.bind(favoritesproductList[position])
    }

    override fun getItemCount(): Int {
        return favoritesproductList.size
    }

    inner class FavoritesItem(private var itemFavoritesBinding: ItemFavoritesBinding):RecyclerView.ViewHolder(itemFavoritesBinding.root){
        fun bind(productsFavoritesRoomModel: ProductsFavoritesRoomModel){
            itemFavoritesBinding.apply {

                productsFavoritesRoomModel.image.let {
                    Picasso.get().load(it).into(imageFavorite)
                }
                itemfavorites=productsFavoritesRoomModel
            }
        }

    }
    fun updateList(list: List<ProductsFavoritesRoomModel>){
        favoritesproductList.clear()
        favoritesproductList.addAll(list)
    }

}