package android.rezkyauliapratama.com.cermatiproject.data.network.api

import android.rezkyauliapratama.com.cermatiproject.data.network.ObjectUrl
import android.rezkyauliapratama.com.cermatiproject.data.network.schema.SearchResponseSchema
import com.rezkyaulia.android.light_optimization_data.NetworkClient
import io.reactivex.ObservableSource
import io.reactivex.Single
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import javax.inject.Inject


class SearchApi  @Inject constructor(private val networkClient: NetworkClient) : AnkoLogger {
    val TAG : String  = SearchApi::class.java.simpleName

    fun getSearchUser(s : String) : ObservableSource<SearchResponseSchema> {
        error { "getsearchuser s : "+s }
        return ObservableSource{ emitter ->
            try {
                searchUser(s)
                    .apply { this?.let { emitter.onNext(it) } }
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }

    }


    private fun searchUser(s : String) : SearchResponseSchema?
    {

        try
        {
            error { "url : "+ObjectUrl.searchGithub(s) }
            return with(networkClient){
                withUrl(ObjectUrl.searchGithub(s))
                    .initAs(SearchResponseSchema::class.java)
                    .setTag(TAG)
                    .syncFuture
            }
        } catch (e: Exception) {
            throw e
        }

    }

}