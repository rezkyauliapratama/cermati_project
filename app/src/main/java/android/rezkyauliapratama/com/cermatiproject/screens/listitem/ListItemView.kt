package android.rezkyauliapratama.com.cermatiproject.screens.listitem

import android.rezkyauliapratama.com.cermatiproject.data.network.schema.UserSchema
import android.rezkyauliapratama.com.cermatiproject.screens.common.views.ViewPattern


interface ListItemView: ViewPattern{


    fun bindList(userSchema: UserSchema)

}