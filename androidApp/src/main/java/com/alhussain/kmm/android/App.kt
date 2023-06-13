package com.alhussain.kmm.android

import android.app.Application
import com.alhussain.kmm.KMM
import com.alhussain.kmm.create
import com.alhussain.kmm.data.LoginRepository
import com.alhussain.kmm.data.SessionManager
import com.alhussain.kmm.di.AppModule
import com.alhussain.kmm.getDataStore
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidApplication

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()

        Napier.base(DebugAntilog())


    }

    private val androidModule = module {
        viewModel {
            LoginViewModel(get())
        }

        single{
            SessionManager(getDataStore(androidContext()))
        }


    }


    private fun initKoin() {
        startKoin {
            if (BuildConfig.DEBUG) androidLogger(Level.ERROR)
            androidContext(this@App)

            modules(AppModule + androidModule)


        }
    }

}