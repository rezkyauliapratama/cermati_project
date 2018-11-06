package android.rezkyauliapratama.com.cermatiproject.screens.dashboard

import android.rezkyauliapratama.com.cermatiproject.data.network.schema.UserSchema
import android.rezkyauliapratama.com.cermatiproject.screens.common.views.ObservableView


interface  MainView : ObservableView<MainView.Listener> {

    interface Listener {
        fun onSearch(s: String)
        fun onFetchPage()
    }

    fun bindSearchItems(response: MutableList<UserSchema>)

    fun showProgressIndication()

    fun hideProgressIndication()

    fun showStatusIndication(message: String)

    fun hideStatusIndication()
    fun bindFetchItems(response: MutableList<UserSchema>)

}