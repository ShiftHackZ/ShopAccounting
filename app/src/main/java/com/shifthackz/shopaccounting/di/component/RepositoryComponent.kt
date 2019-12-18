package com.shifthackz.shopaccounting.di.component

import com.shifthackz.shopaccounting.di.module.RepositoryModule
import com.shifthackz.shopaccounting.di.scope.RepositoryScope
import com.shifthackz.shopaccounting.repository.AppRepository
import dagger.Component

@RepositoryScope
@Component(modules = [RepositoryModule::class], dependencies = [ApiComponent::class, DatabaseComponent::class])
interface RepositoryComponent {
    val repository: AppRepository
}