package kr.co.devstory.mvvm.base

import android.app.Application
import kr.co.devstory.mvvm.di.diModule
import org.koin.android.ext.android.startKoin

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, diModule)
    }
}