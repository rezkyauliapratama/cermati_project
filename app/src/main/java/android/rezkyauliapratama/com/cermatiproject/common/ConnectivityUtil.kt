package android.rezkyauliapratama.com.cermatiproject.common

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat
import javax.inject.Inject
import androidx.core.content.ContextCompat.getSystemService



class ConnectivityUtil constructor (val context: Context){

    fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null
    }

}