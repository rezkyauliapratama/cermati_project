package android.rezkyauliapratama.com.cermatiproject.screens.dashboard

import android.rezkyauliapratama.com.cermatiproject.R
import android.rezkyauliapratama.com.cermatiproject.databinding.ActivityMainBinding
import android.rezkyauliapratama.com.cermatiproject.screens.common.ViewFactory
import android.rezkyauliapratama.com.cermatiproject.screens.common.views.BaseObservableView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil


class MainViewImpl(inflater: LayoutInflater, parent: ViewGroup?, viewFactory: ViewFactory) :
    BaseObservableView<MainView.Listener>(), MainView {

    var binding: ActivityMainBinding = DataBindingUtil.inflate(inflater, R.layout.activity_main, parent, false)

    init {
        dataBinding = binding

        /*for(listener in listeners){
            listener.onButtonClicked()
        }
    */

    }

    override fun showProgressIndication() {

    }

    override fun hideProgressIndication() {

    }

    override fun showStatusIndication() {

    }

    override fun hideStatusIndication() {

    }
}

