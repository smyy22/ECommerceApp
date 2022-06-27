package com.sumeyyeemre.ecommerceapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sumeyyeemre.ecommerceapp.data.model.ProductModel
import com.sumeyyeemre.ecommerceapp.databinding.ProductLayoutBinding
import com.sumeyyeemre.ecommerceapp.ui.HomeFragmentDirections

class ProductAdapter():RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private val productlist=ArrayList<ProductModel>()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductAdapter.ProductViewHolder {
        val productsBinding=ProductLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductViewHolder(productsBinding)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ProductViewHolder, position: Int) {
        holder.bind(productlist[position])
    }

    override fun getItemCount(): Int =productlist.size

    inner class ProductViewHolder(private var productsBinding:ProductLayoutBinding):RecyclerView.ViewHolder(productsBinding.root){

        fun bind(productmodel:ProductModel){
            productsBinding.apply {
                productmodel.image.let {
                    Picasso.get().load(it).into(imageView)
                }
                itemproducts=productmodel

                imageView.setOnClickListener {
                    val action= HomeFragmentDirections.actionHomeToCardDetailFragment2(productmodel)
                        it.findNavController().navigate(action)
                }
            }
        }

    }

    fun updateList(list: List<ProductModel>){
        productlist.clear()
        productlist.addAll(list)
    }


}