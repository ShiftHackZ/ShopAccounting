package com.shifthackz.shopaccounting.di.module

import android.app.Application
import com.shifthackz.shopaccounting.App
import com.shifthackz.shopaccounting.di.scope.ViewModelScope
import com.shifthackz.shopaccounting.domain.CategoriesViewModel
import com.shifthackz.shopaccounting.domain.HistoryViewModel
import com.shifthackz.shopaccounting.domain.ProductsViewModel
import com.shifthackz.shopaccounting.domain.SingleProductViewModel
import com.shifthackz.shopaccounting.repository.AppRepository
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule(app: App) {

    internal var app: Application = app

    @ViewModelScope
    @Provides
    internal fun providesProductsViewModel(repository: AppRepository): ProductsViewModel {
        return ProductsViewModel(app, repository)
    }

    @ViewModelScope
    @Provides
    internal fun providesCategoriesViewModel(repository: AppRepository): CategoriesViewModel {
        return CategoriesViewModel(app, repository)
    }

    @ViewModelScope
    @Provides
    internal fun providesSingleProductViewModel(repository: AppRepository): SingleProductViewModel {
        return SingleProductViewModel(app, repository)
    }

    @ViewModelScope
    @Provides
    internal fun providesHistoryViewModel(repository: AppRepository): HistoryViewModel {
        return HistoryViewModel(app, repository)
    }
}