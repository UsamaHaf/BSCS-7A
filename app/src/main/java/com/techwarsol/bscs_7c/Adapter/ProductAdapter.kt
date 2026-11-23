package com.techwarsol.bscs_7c.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.techwarsol.bscs_7c.Model.Product
import com.techwarsol.bscs_7c.databinding.ProductListItemBinding

class ProductAdapter (private val productList: ArrayList<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
         val binding = ProductListItemBinding.inflate(LayoutInflater.from(parent.context), parent , false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int
    ) {
        val product = productList[position]
        holder.binding.apply {
            tvItemName.text = product.proName
            tvItemPrice.text = "PKR. " + product.proPrice
        }
    }

    override fun getItemCount(): Int {
         return productList.size
    }


    class ProductViewHolder(val binding: ProductListItemBinding) :
            RecyclerView.ViewHolder(binding.root)

}