package android.rezkyauliapratama.com.cermatiproject.usecases

import android.rezkyauliapratama.com.cermatiproject.data.DataManager
import android.rezkyauliapratama.com.cermatiproject.data.network.ApiObservable
import android.rezkyauliapratama.com.cermatiproject.data.network.schema.UserSchema
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class SearchUserUseCase @Inject constructor(private val dataManager: DataManager) : ApiObservable<SearchUserUseCase.Listener>() {

    interface Listener {
        fun onFetchTodoSuccess(response: MutableList<UserSchema>)
        fun onFetchTodoFailure(message: String)
    }

    fun FetchApiAndNotify(s : String){
        compositeDisposable.add(dataManager.api
            .searchApi
            .getSearchUser(s)
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
            }) { throwable ->
                notifyFailure(throwable.localizedMessage)
            })

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
