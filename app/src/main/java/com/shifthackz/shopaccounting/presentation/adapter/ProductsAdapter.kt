package com.shifthackz.shopaccounting.presentation.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.shifthackz.shopaccounting.R
import com.shifthackz.shopaccounting.presentation.base.BaseAdapterListener
import com.shifthackz.shopaccounting.presentation.item.IProductItemClickListener
import com.shifthackz.shopaccounting.presentation.item.ProductViewHolder
import com.shifthackz.shopaccounting.repository.database.entity.ProductEntity

class ProductsAdapter(private val context: Activity, private val products: List<ProductEntity>, private val listener: IProductItemClickListener<ProductEntity>)
    : BaseAdapterListener<ProductViewHolder, ProductEntity, IProductItemClickListener<ProductEntity>>(products as MutableList<ProductEntity>, listener) {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product, p0, false))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(products[position], listener)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun removeItemByProductId(id: Int) {
        products.forEach {
            if (it.id == id) remove(it)
        }
    }
}