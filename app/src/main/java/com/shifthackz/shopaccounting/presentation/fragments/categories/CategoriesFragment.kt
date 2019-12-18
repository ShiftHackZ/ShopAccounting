package com.shifthackz.shopaccounting.presentation.fragments.categories

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.shifthackz.shopaccounting.R
import com.shifthackz.shopaccounting.di.component.ViewModelComponent
import com.shifthackz.shopaccounting.domain.CategoriesViewModel
import com.shifthackz.shopaccounting.presentation.activities.detail.CategoryDetailActivity
import com.shifthackz.shopaccounting.presentation.adapter.CategoriesAdapter
import com.shifthackz.shopaccounting.presentation.base.BaseFragment
import com.shifthackz.shopaccounting.presentation.item.ICategoryItemClickListener
import com.shifthackz.shopaccounting.repository.database.entity.CategoryEntity
import kotlinx.android.synthetic.main.fragment_categories.*
import javax.inject.Inject

class CategoriesFragment : BaseFragment() {

    var viewModel: CategoriesViewModel? = null
        @Inject set

    private val itemClickListener = object : ICategoryItemClickListener<CategoryEntity> {
        override fun openDetail(entity: CategoryEntity) {
            openItemDetail(entity.id, entity.name)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    private fun initViewModel() {
        viewModel?.getAllItems()
        val observer: Observer<List<CategoryEntity>> = Observer {
            initRecyclerView(it!!)
        }
        viewModel?.getLiveDataItems()?.observeForever(observer)
    }

    private fun initRecyclerView(categories: List<CategoryEntity>) {
        val manager = LinearLayoutManager(this.requireContext())
        val categoriesAdapter = CategoriesAdapter(this.requireContext(), categories, itemClickListener)
        categoriesAdapter.setItemClickListener(itemClickListener)
        rvCategories.layoutManager = manager
        rvCategories.adapter = categoriesAdapter
    }

    private fun openItemDetail(id: Int, name: String) {
        this.startActivity(CategoryDetailActivity.newInstance(this.requireContext(), id, name))
    }

    companion object {
        @JvmStatic
        fun newInstance(): CategoriesFragment {
            return CategoriesFragment()
        }
    }
}
