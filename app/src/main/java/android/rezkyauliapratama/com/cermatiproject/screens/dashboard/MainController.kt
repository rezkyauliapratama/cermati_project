package android.rezkyauliapratama.com.cermatiproject.screens.dashboard

import android.rezkyauliapratama.com.cermatiproject.data.network.schema.UserSchema
import android.rezkyauliapratama.com.cermatiproject.screens.common.controller.BaseController
import android.rezkyauliapratama.com.cermatiproject.screens.common.screennavigator.ScreensNavigator
import android.rezkyauliapratama.com.cermatiproject.usecases.SearchUserUseCase
import org.jetbrains.anko.error

class MainController(private val screensNavigator: ScreensNavigator, private val useCase: SearchUserUseCase): BaseController(),
    MainView.Listener, SearchUserUseCase.Listener{

    private lateinit var mViewMvc: MainView

    fun bindView(viewMvc: MainView) {
        mViewMvc = viewMvc
    }

    fun onStart(){
        error { "onstart" }
        mViewMvc.registerListener(this)
        useCase.registerListener(this)
    }

    fun onStop(){
        error { "onstop" }
        mViewMvc.unregisterListener(this)
        useCase.unregisterListener(this)
    }

    override fun onSearch(s: String) {
        useCase.FetchApiAndNotify(s)
        mViewMvc.showProgressIndication()
    }

    override fun onFetchTodoSuccess(response: MutableList<UserSchema>) {
        mViewMvc.hideStatusIndication()
        mViewMvc.hideProgressIndication()
        mViewMvc.bindListUsers(response)
    }

    override fun onFetchTodoFailure(message: String) {
        mViewMvc.showStatusIndication()
        mViewMvc.hideProgressIndication()
    }


}
