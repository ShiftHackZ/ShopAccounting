package com.shifthackz.shopaccounting.di.component

import com.shifthackz.shopaccounting.di.module.DatabaseModule
import com.shifthackz.shopaccounting.repository.database.AppDatabase
import dagger.Component

@Component(modules = [DatabaseModule::class])
interface DatabaseComponent {
    val database: AppDatabase
}
