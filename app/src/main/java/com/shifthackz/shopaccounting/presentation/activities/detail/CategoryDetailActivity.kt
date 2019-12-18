package com.shifthackz.shopaccounting.presentation.activities.detail

import android.arch.lifecycle.Observer
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.shifthackz.shopaccounting.R
import com.shifthackz.shopaccounting.di.component.ViewModelComponent
import com.shifthackz.shopaccounting.domain.ProductsViewModel
import com.shifthackz.shopaccounting.presentation.activities.add.AddProductActivity
import com.shifthackz.shopaccounting.presentation.adapter.ProductsAdapter
import com.shifthackz.shopaccounting.presentation.base.BaseActivity
import com.shifthackz.shopaccounting.presentation.item.IProductItemClickListener
import com.shifthackz.shopaccounting.repository.database.entity.ProductEntity
import kotlinx.android.synthetic.main.activity_detail.*
import java.lang.Exception
import java.util.*
import javax.inject.Inject

const val REQUEST_CODE_PRODUCT_DETAIL = 123

class CategoryDetailActivity : BaseActivity() {

    var viewModel: ProductsViewModel? = null
        @Inject set

    private var categoryId: Int = 0
    lateinit var productsAdapter: ProductsAdapter

    companion object {
        @JvmStatic
        fun newInstance(context: Context, id: Int, name: String): Intent {
            val intent = Intent(context, CategoryDetailActivity::class.java)
            intent.putExtra(context.getString(R.string.CATEGORY_ID), id)
            intent.putExtra(context.getString(R.string.CATEGORY_NAME), name)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setupFab()
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        initViewModel()
    }

    override fun onResume() {
        super.onResume()
        //viewModel?.getAllItems(categoryId)
        initViewModel()
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE_PRODUCT_DETAIL -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        data?.getIntExtra(RESULT_DELETE, 0)?.let {
                            try {
                                productsAdapter.removeItemByProductId(it)
                            } catch(e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setupFab() {
        fabAddProd.setOnClickListener {
            startActivity(AddProductActivity.newInstance(this, categoryId))
        }
    }

    private fun initViewModel() {
        categoryId = intent.getIntExtra(getString(R.string.CATEGORY_ID), 0)
        initActionBar(intent.getStringExtra(getString(R.string.CATEGORY_NAME)))
        viewModel?.getAllItems(categoryId)
        viewModel?.getLiveDataItems()?.apply {
            if (!hasActiveObservers())
                viewModel?.getLiveDataItems()?.observe(this@CategoryDetailActivity, Observer { it?.let { initRecyclerView(it) } })
        }
    }


    private fun initRecyclerView(list: List<ProductEntity>) {
        val manager = LinearLayoutManager(this)
        productsAdapter = ProductsAdapter(this, list, itemClickListener)
        productsAdapter.setItemClickListener(itemClickListener)
        rvProducts.layoutManager = manager
        rvProducts.adapter = productsAdapter
    }

    private fun initActionBar(title: String) {
        Objects.requireNonNull(supportActionBar)?.title = title
    }

    private val itemClickListener = object : IProductItemClickListener<ProductEntity> {
        override fun openDetail(m: ProductEntity) {
            openItemDetail(m.id)
        }
    }

    private fun openItemDetail(id: Int) {
        this.startActivityForResult(ProductDetailActivity.newInstance(this, id), REQUEST_CODE_PRODUCT_DETAIL)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
