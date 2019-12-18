package com.shifthackz.shopaccounting.presentation.activities.add

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.shifthackz.shopaccounting.R
import com.shifthackz.shopaccounting.di.component.ViewModelComponent
import com.shifthackz.shopaccounting.domain.SingleProductViewModel
import com.shifthackz.shopaccounting.presentation.base.BaseActivity
import com.shifthackz.shopaccounting.repository.database.entity.ProductEntity
import kotlinx.android.synthetic.main.activity_add_product.*
import javax.inject.Inject

class AddProductActivity : BaseActivity() {

    private var catId: Int = 0

    var viewModel: SingleProductViewModel? = null
        @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
        catId = intent.getIntExtra(getString(R.string.CATEGORY_ID), 0)
        initAppBar()
        Log.d("CAT", catId.toString())
        setupButton()
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    private fun setupButton() {
        btnAddProduct.setOnClickListener {
            viewModel?.addProduct(getNewProduct())
            finish()
        }
    }

    private fun initAppBar() {
        supportActionBar?.title = "Добавить товар"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getNewProduct() = ProductEntity(99,
            etEditAddName.text.toString(),
            etEditAddPrice.text.toString().toFloat(),
            etEditAddImg.text.toString(),
            etEditAddDesc.text.toString(),
            catId,
            etEditAddCount.text.toString().toInt()
    )

    companion object {
        @JvmStatic
        fun newInstance(context: Context, catId: Int): Intent {
            val intent = Intent(context, AddProductActivity::class.java)
            intent.putExtra(context.getString(R.string.CATEGORY_ID), catId)
            return intent
        }
    }
}
