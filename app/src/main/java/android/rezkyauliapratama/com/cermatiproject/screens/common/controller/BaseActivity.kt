package android.rezkyauliapratama.com.cermatiproject.screens.common.controller

import android.os.Bundle
import android.rezkyauliapratama.com.cermatiproject.BaseApplication
import android.rezkyauliapratama.com.cermatiproject.di.presenter.ActivityModule
import android.rezkyauliapratama.com.cermatiproject.di.presenter.ControllerComponent
import android.rezkyauliapratama.com.cermatiproject.di.presenter.DaggerControllerComponent
import android.rezkyauliapratama.com.cermatiproject.di.presenter.MvcWrapperModule
import android.rezkyauliapratama.com.cermatiproject.screens.common.ViewMvcFactory
import android.rezkyauliapratama.com.cermatiproject.screens.common.views.ViewPattern
import androidx.appcompat.app.AppCompatActivity

import androidx.databinding.ViewDataBinding
import org.jetbrains.anko.AnkoLogger
import javax.inject.Inject


abstract class BaseActivity<T : BaseController, U : ViewPattern, V : ViewDataBinding>  : AppCompatActivity(), AnkoLogger{

    @Inject
    lateinit var viewMvcFactory: ViewMvcFactory

    @Inject
    lateinit var mController: T

    lateinit var mViewMvc: U

    lateinit var mDataBinding: V

    abstract fun inject()
    abstract fun initView()
    abstract fun initDataBinding()

    val controllerComponent: ControllerComponent by lazy {
        DaggerControllerComponent.builder()
                .applicationComponent(BaseApplication.component)
                .activityModule(ActivityModule(this))
                .mvcWrapperModule(MvcWrapperModule())
                .build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        initView()
        initDataBinding()

        super.onCreate(savedInstanceState)

        setContentView(mViewMvc.dataBinding.root)
    }



}