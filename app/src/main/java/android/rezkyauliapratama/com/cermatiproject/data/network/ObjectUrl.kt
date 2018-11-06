package android.rezkyauliapratama.com.cermatiproject.data.network

import android.net.Uri
import android.rezkyauliapratama.com.cermatiproject.BuildConfig
import org.jetbrains.anko.AnkoLogger

//class yang berisi url untuk API
object ObjectUrl : AnkoLogger {

    val total_row = 20

    fun searchGithub(s : String, page : Int): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("search")
                .appendPath("users")
                .appendQueryParameter("q",s)
                .appendQueryParameter("page", page.toString())
                .appendQueryParameter("per_page", total_row.toString())
                .build()
                .toString()
    }

}