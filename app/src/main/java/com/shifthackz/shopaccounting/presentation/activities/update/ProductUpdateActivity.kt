package com.shifthackz.shopaccounting.presentation.activities.update

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.shifthackz.shopaccounting.R
import com.shifthackz.shopaccounting.di.component.ViewModelComponent
import com.shifthackz.shopaccounting.domain.SingleProductViewModel
import com.shifthackz.shopaccounting.presentation.base.BaseActivity
import com.shifthackz.shopaccounting.repository.database.entity.ProductEntity
import kotlinx.android.synthetic.main.activity_product_update.*
import java.util.*
import javax.inject.Inject

class ProductUpdateActivity : BaseActivity() {

    var viewModel: SingleProductViewModel? = null
        @Inject set

    private var productId: Int = 0

    companion object {
        @JvmStatic
        fun newInstance(context: Context, id: Int): Intent {
            val intent = Intent(context, ProductUpdateActivity::class.java)
            intent.putExtra(context.getString(R.string.PRODUCT_ID), id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_update)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_close_24px)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initButton()
        initViewModel()
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    private fun initViewModel() {
        productId = intent.getIntExtra(getString(R.string.PRODUCT_ID), 0)
        viewModel?.getItem(productId)
        val observer: Observer<ProductEntity> = Observer {
            initProduct(it!!)
        }
        viewModel?.getLiveDataItem()?.observeForever(observer)
    }

    private fun initProduct(product: ProductEntity) {
        etEditName.setText(product.name)
        etEditCount.setText(product.count.toString())
        etEditPrice.setText(product.price.toString())
        etEditDesc.setText(product.description)
        initActionBar(product.name)
    }

    private fun initActionBar(title: String) {
        Objects.requireNonNull(supportActionBar)?.title = title
    }

    private fun initButton() {
        btnSaveProduct.setOnClickListener {
            var name = etEditName.text.toString()
            var price = etEditPrice.text.toString().toFloat()
            var desc = etEditDesc.text.toString()
            var count = etEditCount.text.toString().toInt()
            viewModel?.updateItem(productId, name, price, desc, count, "Редактирование карточки товара")
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
