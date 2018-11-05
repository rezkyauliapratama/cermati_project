package android.rezkyauliapratama.com.cermatiproject.screens.dashboard

import android.rezkyauliapratama.com.cermatiproject.databinding.ActivityMainBinding
import android.rezkyauliapratama.com.cermatiproject.screens.common.controller.BaseActivity

class MainActivity : BaseActivity<MainController, MainView, ActivityMainBinding>() {

    override fun initDataBinding() {
        mDataBinding = mViewMvc.dataBinding as ActivityMainBinding
    }

    override fun inject() {
        controllerComponent.inject(this)


    }

    override fun initView() {
        mViewMvc = viewMvcFactory.getMainView(null)
        mController.bindView(mViewMvc)
    }

    override fun onStart() {
        super.onStart()
        mController.onStart()
    }

    override fun onStop() {
        super.onStop()
        mController.onStop()
    }

}
