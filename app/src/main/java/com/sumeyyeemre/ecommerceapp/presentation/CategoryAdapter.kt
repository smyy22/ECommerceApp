package com.sumeyyeemre.ecommerceapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sumeyyeemre.ecommerceapp.data.model.ProductModel
import com.sumeyyeemre.ecommerceapp.databinding.ItemCategoryBinding

class CategoryAdapter(): RecyclerView.Adapter<CategoryAdapter.CathegoryViewHolder>() {
    private val categoryList=ArrayList<String>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.CathegoryViewHolder {
        val cathegoryBinding=ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CathegoryViewHolder(cathegoryBinding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.CathegoryViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
    inner class CathegoryViewHolder(private var cathegoryBinding:ItemCategoryBinding):RecyclerView.ViewHolder(cathegoryBinding.root){
            fun bind(productModel: String){
                cathegoryBinding.category.text=productModel
        }
    }

    fun updateList(list: List<String>){
        categoryList.clear()
        categoryList.addAll(list)
    }
}