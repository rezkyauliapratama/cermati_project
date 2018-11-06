package android.rezkyauliapratama.com.cermatiproject.screens.listitem

import android.rezkyauliapratama.com.cermatiproject.R
import android.rezkyauliapratama.com.cermatiproject.common.DimensionConverter
import android.rezkyauliapratama.com.cermatiproject.data.network.schema.UserSchema
import android.rezkyauliapratama.com.cermatiproject.databinding.ListItemUserBinding
import android.rezkyauliapratama.com.cermatiproject.screens.common.ViewMvcFactory
import android.rezkyauliapratama.com.cermatiproject.screens.common.views.BaseView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.squareup.picasso.Picasso


class ListItemViewImpl(inflater: LayoutInflater, parent: ViewGroup?, viewMvcFactory: ViewMvcFactory) :
    BaseView(), ListItemView{


    var binding: ListItemUserBinding = DataBindingUtil.inflate(inflater, R.layout.list_item_user,parent,false)

    init {
        dataBinding = binding
    }

    override fun bindList(userSchema: UserSchema) {
        binding.tvUsername.text = userSchema.login
        val width =
            getContext()?.resources?.getDisplayMetrics()?.let {
                DimensionConverter.stringToDimension("62dp",
                    it
                ).toInt()
            }

        width?.let {
            Picasso
                .get()
                .load(userSchema.avatar_url)
                .placeholder(R.drawable.ic_image)
                .resize(width, 0)
                .onlyScaleDown()
                .centerInside()
                .into(binding.imageView)
        }

    }

}