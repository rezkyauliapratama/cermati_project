package android.rezkyauliapratama.com.cermatiproject.data.network.api

import android.accounts.NetworkErrorException
import android.rezkyauliapratama.com.cermatiproject.common.ConnectivityUtil
import android.rezkyauliapratama.com.cermatiproject.data.network.ObjectUrl
import android.rezkyauliapratama.com.cermatiproject.data.network.schema.SearchResponseSchema
import android.util.Log
import com.google.gson.Gson
import com.rezkyaulia.android.light_optimization_data.NetworkClient
import io.reactivex.ObservableSource
import io.reactivex.Single
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject


class SearchApi  @Inject constructor(private val networkClient: NetworkClient, private val connectivityUtil: ConnectivityUtil) : AnkoLogger {
    val TAG : String  = SearchApi::class.java.simpleName

    fun getSearchUser(s : String, page : Int) : ObservableSource<SearchResponseSchema> {
        return ObservableSource{ emitter ->
            try {
                searchUser(s, page)
                    .apply {  emitter.onNext(this) }
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }

    }

    fun fetchUsers(s : String, page : Int) : Single<SearchResponseSchema> {

        return Single.create<SearchResponseSchema>{ emitter ->
            try {
                searchUser(s, page)
                    .apply {
                        emitter.onSuccess(this)
                    }
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }

    }

    private fun searchUser(s : String, page : Int) : SearchResponseSchema
    {

           if (connectivityUtil.isNetworkAvailable()){

               try {
                   return with(networkClient) {
                       withUrl(ObjectUrl.searchGithub(s, page))
                           .initAs(SearchResponseSchema::class.java)
                           .setTag(TAG)
                           .syncFuture
                   }
               }catch (e: IOException) {
                   throw e
               }

           }else{
               throw Exception("No Network")
           }



        }



}