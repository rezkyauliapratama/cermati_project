package android.rezkyauliapratama.com.cermatiproject.di.presenter

import android.rezkyauliapratama.com.cermatiproject.di.application.ApplicationComponent
import android.rezkyauliapratama.com.cermatiproject.screens.MainActivity
import dagger.Component


/**
 * Created by Rezky Aulia Pratama on 15/8/18.
 */
@PerController
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class, MvcWrapperModule::class, ControllerModule::class, UseCaseModule::class])
interface ControllerComponent{

    fun inject(mainActivity: MainActivity)
}
