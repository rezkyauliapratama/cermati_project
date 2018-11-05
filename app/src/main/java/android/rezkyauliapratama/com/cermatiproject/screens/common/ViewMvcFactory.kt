package android.rezkyauliapratama.com.cermatiproject.screens.common

import android.rezkyauliapratama.com.cermatiproject.screens.dashboard.MainView
import android.rezkyauliapratama.com.cermatiproject.screens.dashboard.MainViewImpl
import android.rezkyauliapratama.com.cermatiproject.screens.listitem.ListItemView
import android.rezkyauliapratama.com.cermatiproject.screens.listitem.ListItemViewImpl
import android.view.LayoutInflater
import android.view.ViewGroup

class ViewMvcFactory(private val mLayoutInflater: LayoutInflater) {
    fun getListItemView(parent: ViewGroup): ListItemView {
        return ListItemViewImpl(mLayoutInflater,parent,this)
    }

    fun getMainView(parent: ViewGroup?): MainView {
        return MainViewImpl(mLayoutInflater, parent, this)
    }

}
