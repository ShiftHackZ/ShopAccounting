package com.shifthackz.shopaccounting.presentation.activities.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.shifthackz.shopaccounting.R
import com.shifthackz.shopaccounting.di.component.ViewModelComponent
import com.shifthackz.shopaccounting.presentation.base.BaseActivity
import com.shifthackz.shopaccounting.presentation.adapter.TabPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPages()
    }


    private fun initPages() {
        val fragmentAdapter = TabPagerAdapter(supportFragmentManager)
        viewpagerMain.adapter = fragmentAdapter
        tabsMain.setupWithViewPager(viewpagerMain)
        supportActionBar!!.elevation = 0f
    }

    companion object {
        @JvmStatic
        fun newInstance(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            return intent
        }
    }

    override fun injectDependency(component: ViewModelComponent) {
        //component.inject(this)
    }

}
