package com.shifthackz.shopaccounting

import android.app.Application
import android.arch.persistence.room.Room
import com.shifthackz.shopaccounting.di.component.*
import com.shifthackz.shopaccounting.di.module.ApiModule
import com.shifthackz.shopaccounting.di.module.DatabaseModule
import com.shifthackz.shopaccounting.di.module.RepositoryModule
import com.shifthackz.shopaccounting.di.module.ViewModelModule
import com.shifthackz.shopaccounting.repository.database.AppDatabase

class App: Application() {

    private var viewModelComponent: ViewModelComponent? = null
    private var database: AppDatabase? = null

    override fun onCreate() {
        super.onCreate()
        initRoom()
        initDagger()
    }

    private fun initRoom() {
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()
    }

    private fun initDagger() {
        val apiComponent = DaggerApiComponent.builder()
            .apiModule(ApiModule())
            .build()

        val databaseComponent = DaggerDatabaseComponent.builder()
            .databaseModule(DatabaseModule(this!!.database!!))
            .build()

        val repositoryComponent = DaggerRepositoryComponent.builder()
            .apiComponent(apiComponent)
            .databaseComponent(databaseComponent)
            .repositoryModule(RepositoryModule())
            .build()

        viewModelComponent = DaggerViewModelComponent.builder()
            .repositoryComponent(repositoryComponent)
            .viewModelModule(ViewModelModule(this))
            .build()
    }

    fun getViewModelComponent(): ViewModelComponent {
        return this!!.viewModelComponent!!
    }
}