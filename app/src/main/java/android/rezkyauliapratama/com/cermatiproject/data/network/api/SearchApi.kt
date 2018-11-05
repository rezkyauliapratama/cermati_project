package android.rezkyauliapratama.com.cermatiproject.data.network.api

import android.rezkyauliapratama.com.cermatiproject.data.network.ObjectUrl
import android.rezkyauliapratama.com.cermatiproject.data.network.schema.SearchResponseSchema
import com.rezkyaulia.android.light_optimization_data.NetworkClient
import io.reactivex.Single
import org.jetbrains.anko.AnkoLogger
import javax.inject.Inject


class SearchApi  @Inject constructor(private val networkClient: NetworkClient) : AnkoLogger {
    val TAG : String  = SearchApi::class.java.simpleName

    fun getSearchUser(s : String) : Single<SearchResponseSchema> {
        return Single.create<SearchResponseSchema> { emitter ->
            try {
                searchUser(s)
                    .apply { this?.let { emitter.onSuccess(it) } }
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }

    }


    private fun searchUser(s : String) : SearchResponseSchema?
    {

        try
        {
            return with(networkClient){
                cancelByTag(TAG)
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