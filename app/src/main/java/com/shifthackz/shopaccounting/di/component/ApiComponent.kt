package com.shifthackz.shopaccounting.di.component

import com.shifthackz.shopaccounting.di.module.ApiModule
import com.shifthackz.shopaccounting.di.scope.ApiScope
import com.shifthackz.shopaccounting.repository.server.ServerCommunicator
import dagger.Component

@ApiScope
@Component(modules = [ApiModule::class])
interface ApiComponent {
    val serverCommunicator: ServerCommunicator
}
