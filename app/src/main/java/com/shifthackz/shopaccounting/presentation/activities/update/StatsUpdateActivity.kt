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
import kotlinx.android.synthetic.main.activity_stats_update.*
import java.util.*
import javax.inject.Inject

class StatsUpdateActivity : BaseActivity() {

    var viewModel: SingleProductViewModel? = null
        @Inject set

    private var productId: Int = 0
    private var price = 0f
    private var desc = ""

    companion object {
        @JvmStatic
        fun newInstance(context: Context, id: Int): Intent {
            val intent = Intent(context, StatsUpdateActivity::class.java)
            intent.putExtra(context.getString(R.string.PRODUCT_ID), id)
            return intent
        }
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
        etStatsName.setText(product.name)
        etStatsCount.setText(product.count.toString())
        initActionBar(product.name)
        desc = product.description
        price = product.price
    }

    private fun initButton() {
        btnSaveStats.setOnClickListener {
            var name = etStatsName.text.toString()
            var count = etStatsCount.text.toString().toInt()
            var reason = etStatsReason.text.toString()
            viewModel?.updateItem(productId, name, price, desc, count, reason)
            finish()
        }
    }

    private fun initActionBar(title: String) {
        Objects.requireNonNull(supportActionBar)?.title = title
        Objects.requireNonNull(supportActionBar)?.setHomeAsUpIndicator(R.drawable.ic_baseline_close_24px)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats_update)
        initButton()
        initViewModel()
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
