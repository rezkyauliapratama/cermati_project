package android.rezkyauliapratama.com.cermatiproject.screens.common.controller

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.rezkyauliapratama.com.cermatiproject.BaseApplication
import android.rezkyauliapratama.com.cermatiproject.di.presenter.ActivityModule
import android.rezkyauliapratama.com.cermatiproject.di.presenter.ControllerComponent
import android.rezkyauliapratama.com.cermatiproject.di.presenter.DaggerControllerComponent
import android.rezkyauliapratama.com.cermatiproject.screens.common.ViewFactory
import android.rezkyauliapratama.com.cermatiproject.screens.common.views.ViewPattern
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import org.jetbrains.anko.AnkoLogger
import javax.inject.Inject

abstract class BaseFragment<CONTROLLER : BaseController, VIEW_MVC : ViewPattern, DATA_BINDING : ViewDataBinding>  : Fragment(), AnkoLogger{


    @Inject
    lateinit var viewFactory: ViewFactory

    @Inject
    lateinit var mController: CONTROLLER

    lateinit var mViewMvc: VIEW_MVC

    lateinit var mDataBinding: DATA_BINDING

    abstract fun inject()
    abstract fun initView(container: ViewGroup?)
    abstract fun initDataBinding()

    val controllerComponent: ControllerComponent by lazy {
        DaggerControllerComponent.builder()
                .applicationComponent(BaseApplication.component)
                .activityModule(ActivityModule(requireActivity()))
                .build()
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Activity){
            inject()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        initView(container)
        initDataBinding()
        return mViewMvc.dataBinding?.root
    }
}