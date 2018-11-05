package android.rezkyauliapratama.com.cermatiproject.screens.dashboard

import android.rezkyauliapratama.com.cermatiproject.screens.common.views.ObservableView


interface  MainView : ObservableView<MainView.Listener> {

    interface Listener {
        fun onSearch()
    }


    fun showProgressIndication()

    fun hideProgressIndication()

    fun showStatusIndication()

    fun hideStatusIndication()

}