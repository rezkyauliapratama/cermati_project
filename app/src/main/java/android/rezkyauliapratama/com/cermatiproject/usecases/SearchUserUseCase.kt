package android.rezkyauliapratama.com.cermatiproject.usecases

import android.rezkyauliapratama.com.cermatiproject.data.DataManager
import android.rezkyauliapratama.com.cermatiproject.data.network.ApiObservable
import android.rezkyauliapratama.com.cermatiproject.data.network.schema.SearchResponseSchema
import android.rezkyauliapratama.com.cermatiproject.data.network.schema.UserSchema
import com.google.gson.Gson
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SearchUserUseCase @Inject constructor(private val dataManager: DataManager) : ApiObservable<SearchUserUseCase.Listener>() {

    val subject = PublishSubject.create<String>()

    interface Listener {
        fun onFetchTodoSuccess(response: MutableList<UserSchema>)
        fun onFetchTodoFailure(message: String)
    }

    init {
        compositeDisposable = CompositeDisposable()

        compositeDisposable.add(
            subject.
                debounce(300, TimeUnit.MILLISECONDS)
                .filter(Predicate { it: String ->
                    return@Predicate it.isNotEmpty()
                })
                .distinctUntilChanged()

                .switchMap(Function<String, ObservableSource<SearchResponseSchema>> { it ->
                    return@Function dataManager.api
                        .searchApi
                        .getSearchUser(it)
                })
                .map { it ->
                    val users = mutableListOf<UserSchema>()

                    if (it.total_count > 0){
                        for (user in it.items){
                            users.add(user)
                        }
                    }

                    users
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    notifySuccess(response)

                }, { throwable ->
                    notifyFailure(throwable.localizedMessage)

                })

        )

    }
    fun FetchApiAndNotify(s : String){
        subject.onNext(s)
    }


    private fun notifyFailure(message: String) {
        for (listener in listeners) {
            listener.onFetchTodoFailure(message)
        }
    }

    private fun notifySuccess(response : MutableList<UserSchema>) {

        for (listener in listeners) {
            listener.onFetchTodoSuccess(response)
        }
    }

}
