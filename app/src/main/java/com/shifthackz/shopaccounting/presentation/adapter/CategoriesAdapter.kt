package com.shifthackz.shopaccounting.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.shifthackz.shopaccounting.R
import com.shifthackz.shopaccounting.presentation.base.BaseAdapterListener
import com.shifthackz.shopaccounting.presentation.item.ICategoryItemClickListener
import com.shifthackz.shopaccounting.presentation.item.CategoryViewHolder
import com.shifthackz.shopaccounting.repository.database.entity.CategoryEntity

class CategoriesAdapter(private val context: Context, private val categories: List<CategoryEntity>, private val listener: ICategoryItemClickListener<CategoryEntity>) :
    BaseAdapterListener<CategoryViewHolder, CategoryEntity, ICategoryItemClickListener<CategoryEntity>>(categories as MutableList<CategoryEntity>, listener) {

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_category, parent, false))
    }

    override fun onBindViewHolder(categoryViewHolder: CategoryViewHolder, i: Int) {
        super.onBindViewHolder(categoryViewHolder, i)
        categoryViewHolder.bind(categories[i], listener)
    }
}
