package android.rezkyauliapratama.com.cermatiproject.di.presenter

import android.content.Context
import android.rezkyauliapratama.com.cermatiproject.di.presenter.ActivityContext
import androidx.fragment.app.FragmentActivity
import dagger.Module
import dagger.Provides
/**
 * Created by Rezky Aulia Pratama on 15/8/18.
 */
@Module
class ActivityModule(private val activity: FragmentActivity){

    @Provides
    @ActivityContext
    fun providesContext(): Context = activity

    @Provides
    fun provideActivity(): FragmentActivity = activity


}