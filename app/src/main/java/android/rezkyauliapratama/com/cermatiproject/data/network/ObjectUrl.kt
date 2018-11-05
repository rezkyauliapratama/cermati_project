package android.rezkyauliapratama.com.cermatiproject.data.network

import android.net.Uri
import android.rezkyauliapratama.com.cermatiproject.BuildConfig
import org.jetbrains.anko.AnkoLogger

//class yang berisi url untuk API
object ObjectUrl : AnkoLogger {


    fun searchGithub(s : String): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("search")
                .appendPath("users")
                .appendQueryParameter("q",s)
                .build()
                .toString()
    }

}