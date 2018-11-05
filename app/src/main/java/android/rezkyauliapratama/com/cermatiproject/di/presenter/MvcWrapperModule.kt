package android.rezkyauliapratama.com.cermatiproject.di.presenter

import android.content.Context
import android.rezkyauliapratama.com.cermatiproject.screens.common.ViewFactory
import android.rezkyauliapratama.com.cermatiproject.screens.common.screennavigator.ScreensNavigator
import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import dagger.Module
import dagger.Provides

@Module
class MvcWrapperModule{

    @Provides
    fun getViewMvcFactory(@ActivityContext context: Context): ViewFactory {
        return ViewFactory(LayoutInflater.from(context))
    }

    @Provides
    fun getScreensNavigator(activity: FragmentActivity): ScreensNavigator {
        return ScreensNavigator( activity)
    }

}