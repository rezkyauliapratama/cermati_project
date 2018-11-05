package android.rezkyauliapratama.com.cermatiproject.screens.dashboard

import android.rezkyauliapratama.com.cermatiproject.data.network.schema.UserSchema
import android.rezkyauliapratama.com.cermatiproject.screens.common.views.ObservableView


interface  MainView : ObservableView<MainView.Listener> {

    interface Listener {
        fun onSearch(s: String)
    }

    fun bindListUsers(users: List<UserSchema>)

    fun showProgressIndication()

    fun hideProgressIndication()

    fun showStatusIndication()

    fun hideStatusIndication()

}