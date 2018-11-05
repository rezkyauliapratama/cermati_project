package android.rezkyauliapratama.com.cermatiproject.di.application

import android.rezkyauliapratama.com.cermatiproject.BaseApplication
import android.rezkyauliapratama.com.cermatiproject.data.DataManager
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent{

    fun getDataManager(): DataManager
    fun inject(baseApplication: BaseApplication)

}