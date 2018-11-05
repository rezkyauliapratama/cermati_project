package android.rezkyauliapratama.com.cermatiproject.screens.listitem

import android.rezkyauliapratama.com.cermatiproject.data.network.schema.UserSchema
import android.rezkyauliapratama.com.cermatiproject.screens.common.ViewMvcFactory
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class ListItemAdapter(private val viewMvcFactory: ViewMvcFactory):
    RecyclerView.Adapter<ListItemAdapter.ViewHolder>(){


    fun bindItems(items: List<UserSchema>) {
        mItems.clear()
        if (!items.isEmpty()){
            mItems.addAll(items)
        }
        notifyDataSetChanged()
    }


    private val mItems: MutableList<UserSchema> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewMvc = viewMvcFactory.getListItemView(parent)
        return ViewHolder(viewMvc)
    }

    override fun getItemCount() = mItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mViewMvc.bindList(mItems[position])

    }



    class ViewHolder(val mViewMvc: ListItemView) : RecyclerView.ViewHolder(mViewMvc.dataBinding.root)

}