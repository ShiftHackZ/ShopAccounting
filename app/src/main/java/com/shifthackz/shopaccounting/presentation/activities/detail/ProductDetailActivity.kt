package com.shifthackz.shopaccounting.presentation.activities.detail

import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.shifthackz.shopaccounting.R
import com.shifthackz.shopaccounting.di.component.ViewModelComponent
import com.shifthackz.shopaccounting.domain.SingleProductViewModel
import com.shifthackz.shopaccounting.presentation.activities.update.ProductUpdateActivity
import com.shifthackz.shopaccounting.presentation.activities.update.StatsUpdateActivity
import com.shifthackz.shopaccounting.presentation.base.BaseActivity
import com.shifthackz.shopaccounting.repository.database.entity.ProductEntity
import kotlinx.android.synthetic.main.activity_product_detail.*
import java.util.*
import javax.inject.Inject

const val RESULT_DELETE = "delete_id"

class ProductDetailActivity : BaseActivity() {

    var viewModel: SingleProductViewModel? = null
        @Inject set

    private var productId: Int = 0

    companion object {
        @JvmStatic
        fun newInstance(context: Context, id: Int): Intent {
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra(context.getString(R.string.PRODUCT_ID), id)
            return intent
        }
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        initButton()
        initViewModel()
    }

    private fun initViewModel() {
        productId = intent.getIntExtra(getString(R.string.PRODUCT_ID), 0)
        viewModel?.getItem(productId)
        viewModel?.getLiveDataItem()?.observe(this, Observer { initProduct(it!!) })
    }

    private fun initProduct(product: ProductEntity) {
        Glide.with(this)
            .load(product.image)
            .into(ivProductDetailPreview)

        txtProductDetailName.text = product.name
        txtProductDetailPrice.text = product.price.toString().plus(" грн")
        txtProductDetailCount.text = product.count.toString().plus(" шт")
        txtProductDetailDescription.text = product.description

        initActionBar(product.name)
    }

    private fun initActionBar(title: String) {
        Objects.requireNonNull(supportActionBar)?.title = title
    }

    private fun initButton() {
        btnChangeProduct.setOnClickListener {
            this.startActivity(ProductUpdateActivity.newInstance(this, productId))
        }
        btnChangeStats.setOnClickListener {
            this.startActivity(StatsUpdateActivity.newInstance(this, productId))
        }
        btnDeleteProduct.setOnClickListener {
            viewModel?.deleteItem(productId)
            setResult(Activity.RESULT_OK, Intent().putExtra(RESULT_DELETE, productId))
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
