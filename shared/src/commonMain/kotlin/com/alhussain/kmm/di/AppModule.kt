package com.alhussain.kmm.di

import com.alhussain.kmm.data.LoginDataSource
import com.alhussain.kmm.data.LoginRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val AppModule = module {
    singleOf(::LoginDataSource)

    single {
        LoginRepository(get())
    }
}