package android.rezkyauliapratama.com.cermatiproject.data

import android.rezkyauliapratama.com.cermatiproject.data.network.ApiRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DataManager @Inject constructor(){

    @Inject
    lateinit var api: ApiRepository

}