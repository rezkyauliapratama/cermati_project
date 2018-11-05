package android.rezkyauliapratama.com.cermatiproject.di.presenter

import android.rezkyauliapratama.com.cermatiproject.screens.common.screennavigator.ScreensNavigator
import android.rezkyauliapratama.com.cermatiproject.screens.dashboard.MainController
import android.rezkyauliapratama.com.cermatiproject.usecases.SearchUserUseCase
import dagger.Module
import dagger.Provides

@Module
class ControllerModule {

    @Provides
    fun getMainController(screensNavigator: ScreensNavigator, searchUserUseCase: SearchUserUseCase) : MainController {
        return MainController(screensNavigator, searchUserUseCase)
    }

}