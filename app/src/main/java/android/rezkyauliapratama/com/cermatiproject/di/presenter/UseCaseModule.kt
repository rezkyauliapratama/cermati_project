package android.rezkyauliapratama.com.cermatiproject.di.presenter

import android.rezkyauliapratama.com.cermatiproject.data.DataManager
import android.rezkyauliapratama.com.cermatiproject.usecases.SearchUserUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule{
    @Provides
    fun getSearchUseCase(dataManager: DataManager) : SearchUserUseCase{
        return SearchUserUseCase(dataManager)
    }

}