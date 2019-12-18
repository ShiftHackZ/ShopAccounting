package com.shifthackz.shopaccounting.presentation.item

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.shifthackz.shopaccounting.repository.database.entity.CategoryEntity
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private var categoryEntity: CategoryEntity? = null
    private var listener: ICategoryItemClickListener<CategoryEntity>? = null
    private val itemDetail = View.OnClickListener { listener!!.openDetail(this!!.categoryEntity!!) }

    fun bind(category: CategoryEntity, listener: ICategoryItemClickListener<CategoryEntity>) {
        categoryEntity = category
        this.listener = listener
        setupItem()
    }

    private fun setupItem() {
        Glide.with(view)
            .load(categoryEntity?.image)
            .into(view.ivCategoryPreview)

        view.txtCategoryName.text = categoryEntity?.name
        view.setOnClickListener(itemDetail)
    }

}
