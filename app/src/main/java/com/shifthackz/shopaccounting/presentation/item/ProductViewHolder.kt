package com.shifthackz.shopaccounting.presentation.item

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.shifthackz.shopaccounting.repository.database.entity.ProductEntity
import kotlinx.android.synthetic.main.item_product.view.*

class ProductViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private var productEntity: ProductEntity? = null
    private var listener: IProductItemClickListener<ProductEntity>? = null
    private val itemDetail = View.OnClickListener { listener!!.openDetail(this!!.productEntity!!) }

    fun bind(productEntity: ProductEntity, listener: IProductItemClickListener<ProductEntity>) {
        this.productEntity = productEntity
        this.listener = listener
        setupItem()
    }

    private fun setupItem() {
        Glide.with(view)
            .load(productEntity?.image)
            .into(view.ivProductPreview)

        view.txtProductName.text = productEntity?.name
        view.txtProductPrice.text = productEntity?.price.toString().plus(" грн")
        view.setOnClickListener(itemDetail)
    }

}