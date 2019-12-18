package com.shifthackz.shopaccounting.presentation.base

import android.app.ActionBar
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment
import android.view.MenuItem
import android.view.View
import com.shifthackz.shopaccounting.App
import com.shifthackz.shopaccounting.di.component.ViewModelComponent
import com.shifthackz.shopaccounting.utils.hideKeyboardEx
import com.shifthackz.shopaccounting.utils.showSnack
import com.shifthackz.shopaccounting.utils.showToast

abstract class BaseFragment : Fragment() {
    private val appBar: ActionBar? = activity?.actionBar
    protected fun disableHomeAsUp() = appBar?.setDisplayHomeAsUpEnabled(false)

    protected fun initializeNavigationBar(title: String, showBackButton: Boolean, @DrawableRes resId: Int) {
        appBar?.apply {
            this.setDisplayHomeAsUpEnabled(showBackButton)
            this.setHomeAsUpIndicator(resId)
            this.elevation = 4f
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createDaggerDependencies()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createDaggerDependencies()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> fragmentManager?.popBackStackImmediate()
        }
        return super.onOptionsItemSelected(item)
    }

    protected abstract fun injectDependency(component: ViewModelComponent)

    private fun createDaggerDependencies() {
        injectDependency((requireActivity().application as App).getViewModelComponent())
    }

    protected fun showToast(text: String) = activity?.showToast(text)
    protected fun showSnack(text: String) = activity?.showSnack(text)
    protected fun hideKeyboard() = activity?.hideKeyboardEx()
}