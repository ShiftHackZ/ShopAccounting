package com.shifthackz.shopaccounting.di.component

import com.shifthackz.shopaccounting.di.module.ViewModelModule
import com.shifthackz.shopaccounting.di.scope.ViewModelScope
import com.shifthackz.shopaccounting.presentation.activities.add.AddProductActivity
import com.shifthackz.shopaccounting.presentation.activities.detail.CategoryDetailActivity
import com.shifthackz.shopaccounting.presentation.activities.detail.ProductDetailActivity
import com.shifthackz.shopaccounting.presentation.activities.main.MainActivity
import com.shifthackz.shopaccounting.presentation.activities.update.ProductUpdateActivity
import com.shifthackz.shopaccounting.presentation.activities.update.StatsUpdateActivity
import com.shifthackz.shopaccounting.presentation.fragments.categories.CategoriesFragment
import com.shifthackz.shopaccounting.presentation.fragments.history.HistoryFragment
import dagger.Component

@ViewModelScope
@Component(modules = [ViewModelModule::class], dependencies = [RepositoryComponent::class])
interface ViewModelComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: CategoryDetailActivity)
    fun inject(activity: ProductDetailActivity)
    fun inject(activity: ProductUpdateActivity)
    fun inject(activity: StatsUpdateActivity)
    fun inject(activity: AddProductActivity)
    fun inject(fragment: CategoriesFragment)
    fun inject(fragment: HistoryFragment)
}