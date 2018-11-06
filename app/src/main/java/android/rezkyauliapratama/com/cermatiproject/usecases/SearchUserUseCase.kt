package android.rezkyauliapratama.com.cermatiproject.usecases

import android.accounts.NetworkErrorException
import android.rezkyauliapratama.com.cermatiproject.data.DataManager
import android.rezkyauliapratama.com.cermatiproject.data.network.ApiObservable
import android.rezkyauliapratama.com.cermatiproject.data.network.schema.SearchResponseSchema
import android.rezkyauliapratama.com.cermatiproject.data.network.schema.UserSchema
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import android.content.Context.CONNECTIVITY_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.net.ConnectivityManager
import android.rezkyauliapratama.com.cermatiproject.common.ConnectivityUtil
import org.jetbrains.anko.error
import java.io.IOException
import java.lang.Exception


class SearchUserUseCase (private val dataManager: DataManager) : ApiObservable<SearchUserUseCase.Listener>() {

    var subject = PublishSubject.create<String>()
    var page : Int = 1
    val messageError = "Ups sorry, our server to busy. Please try again"
    interface Listener {
        fun onSearchSuccess(response: MutableList<UserSchema>)
        fun onSearchFailure(message: String)
        fun onFetchSuccess(response: MutableList<UserSchema>)
        fun onLoad()
    }

    init {
        compositeDisposable = CompositeDisposable()

        initialize()
    }

    private var searchText: String = ""

    fun SearchAndNotify(s : String){
        this.searchText = s

        subject.onNext(s)


        if (dataManager.connectivityUtil.isNetworkAvailable()){
            notifyLoad()
        }



    }

    private fun initialize(){
        compositeDisposable.add(
            subject.
                debounce(300, TimeUnit.MILLISECONDS)
                .filter(Predicate { it: String ->
                    error { "it $it" }
                    return@Predicate it.isNotEmpty()
                })
                .distinctUntilChanged()
                .switchMap(Function<String, ObservableSource<SearchResponseSchema>> { it ->
                    page = 1
                    return@Function dataManager.api
                        .searchApi
                        .getSearchUser(it,page)
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.message != null){
                        notifyFailure(response.message)
                    }else{
                        if (response.items != null){
                            notifySearchSuccess(response.items as MutableList<UserSchema>)
                        }else{
                            notifyFailure("Empty result")
                        }

                    }

                }, { throwable ->
                    initialize()
                    if (throwable is IOException ){
                        notifyFailure(messageError)
                    }else{
                        notifyFailure(throwable.localizedMessage)
                    }

                })

        )
    }

    fun fetchAndNotify(){
        page++
        notifyLoad()
        compositeDisposable.add(
            dataManager.api
                .searchApi
                .fetchUsers(searchText,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.message != null){
                        notifyFailure(response.message)
                    }else{
                        if (response.items != null && response.items.isNotEmpty()){
                            notifyFetchSuccess(response.items as MutableList<UserSchema>)
                        }else{
                            notifyFailure("Empty result")
                        }

                    }
                }, { throwable ->
                    if (throwable is IOException ){
                        notifyFailure(messageError)
                    }else{
                        notifyFailure(throwable.localizedMessage)
                    }
                })
        )

    }



    private fun notifyFailure(message: String) {
        for (listener in listeners) {
            listener.onSearchFailure(message)
        }
    }


    private fun notifyLoad() {
        for (listener in listeners) {
            listener.onLoad()
        }
    }
    private fun notifySearchSuccess(response : MutableList<UserSchema>) {

        for (listener in listeners) {
            listener.onSearchSuccess(response)
        }
    }

    private fun notifyFetchSuccess(response : MutableList<UserSchema>) {

        for (listener in listeners) {
            listener.onFetchSuccess(response)
        }
    }

}
