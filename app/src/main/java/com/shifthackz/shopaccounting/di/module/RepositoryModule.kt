package com.shifthackz.shopaccounting.di.module

import com.shifthackz.shopaccounting.di.scope.RepositoryScope
import com.shifthackz.shopaccounting.repository.AppRepository
import com.shifthackz.shopaccounting.repository.database.AppDatabase
import com.shifthackz.shopaccounting.repository.server.ServerCommunicator
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @RepositoryScope
    @Provides
    internal fun providesRepository(communicator: ServerCommunicator, database: AppDatabase): AppRepository {
        return AppRepository(communicator, database)
    }
}