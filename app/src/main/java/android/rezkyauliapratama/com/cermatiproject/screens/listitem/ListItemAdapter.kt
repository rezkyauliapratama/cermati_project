package android.rezkyauliapratama.com.cermatiproject.screens.listitem

import android.rezkyauliapratama.com.cermatiproject.data.network.schema.UserSchema
import android.rezkyauliapratama.com.cermatiproject.screens.common.Recyclerview.BaseAdapter
import android.rezkyauliapratama.com.cermatiproject.screens.common.ViewMvcFactory
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ListItemAdapter(private val viewMvcFactory: ViewMvcFactory):
    BaseAdapter<ListItemAdapter.ViewHolder>(){


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mViewMvc.bindList(mItems[position])
    }

    fun bindSearchItems(items: List<UserSchema>) {
        mItems.clear()
        if (!items.isEmpty()){
            mItems.addAll(items)
        }
        notifyDataSetChanged()
    }

    fun bindFetchItems(items: List<UserSchema>) {
        if (!items.isEmpty()){
            mItems.addAll(items)
        }
        notifyDataSetChanged()
        setLoaded()
    }
    private val mItems: MutableList<UserSchema> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewMvc = viewMvcFactory.getListItemView(parent)
        return ViewHolder(viewMvc)
    }

    override fun getItemCount() = mItems.size

    fun removeData() {
        mItems.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(val mViewMvc: ListItemView) : RecyclerView.ViewHolder(mViewMvc.dataBinding.root)

}