package android.rezkyauliapratama.com.cermatiproject.di.application

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rezkyaulia.android.light_optimization_data.NetworkClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton



/**
 * Created by Rezky Aulia Pratama on 6/8/18.
 */
@Module
class NetworkModule{

    @Provides
    fun providesGson(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    internal fun provideHttpClient(@ApplicationContext context: Context): NetworkClient {
        return NetworkClient(context)
    }
}