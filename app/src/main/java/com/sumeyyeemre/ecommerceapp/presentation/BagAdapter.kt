package com.sumeyyeemre.ecommerceapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sumeyyeemre.ecommerceapp.data.model.ProductModel
import com.sumeyyeemre.ecommerceapp.databinding.BagLayoutBinding

class BagAdapter():RecyclerView.Adapter<BagAdapter.BagViewHolder>() {
    private val bagList=ArrayList<ProductModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BagViewHolder {
        val bagBinding=BagLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BagViewHolder(bagBinding)
    }

    override fun onBindViewHolder(holder: BagViewHolder, position: Int) {
        holder.bind((bagList[position]))
    }

    override fun getItemCount(): Int {
       return bagList.size
    }

    inner class BagViewHolder(private var bagBinding:BagLayoutBinding):RecyclerView.ViewHolder(bagBinding.root){
        fun bind(bagModel: ProductModel){
            bagBinding.apply {
                bagModel.image.let {
                    Picasso.get().load(it).into(imageBag)
                }
                itembag=bagModel
            }
        }
    }

    fun bagupdateList(list: List<ProductModel>){
        bagList.clear()
        bagList.addAll(list)
    }



}