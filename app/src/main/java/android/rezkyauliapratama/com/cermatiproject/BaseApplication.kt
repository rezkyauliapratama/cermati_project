package android.rezkyauliapratama.com.cermatiproject

import android.app.Application
import android.rezkyauliapratama.com.cermatiproject.di.application.ApplicationComponent
import android.rezkyauliapratama.com.cermatiproject.di.application.ApplicationModule
import android.rezkyauliapratama.com.cermatiproject.di.application.DaggerApplicationComponent
import android.rezkyauliapratama.com.cermatiproject.di.application.NetworkModule


class BaseApplication : Application() {

    companion object {
        lateinit var component : ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = initDagger(this)
        component.inject(this)

    }

    private fun initDagger(app: BaseApplication): ApplicationComponent =
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(app))
            .networkModule(NetworkModule())
            .build()



}