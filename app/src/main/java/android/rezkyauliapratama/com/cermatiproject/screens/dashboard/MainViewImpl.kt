package android.rezkyauliapratama.com.cermatiproject.screens.dashboard

import android.rezkyauliapratama.com.cermatiproject.R
import android.rezkyauliapratama.com.cermatiproject.data.network.schema.UserSchema
import android.rezkyauliapratama.com.cermatiproject.databinding.ActivityMainBinding
import android.rezkyauliapratama.com.cermatiproject.screens.common.ViewMvcFactory
import android.rezkyauliapratama.com.cermatiproject.screens.common.views.BaseObservableView
import android.rezkyauliapratama.com.cermatiproject.screens.listitem.ListItemAdapter
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager


class MainViewImpl(inflater: LayoutInflater, parent: ViewGroup?, viewMvcFactory: ViewMvcFactory) :
    BaseObservableView<MainView.Listener>(), MainView {


    var binding: ActivityMainBinding = DataBindingUtil.inflate(inflater, R.layout.activity_main, parent, false)
    private var adapter: ListItemAdapter

    init {
        dataBinding = binding

        adapter = ListItemAdapter(viewMvcFactory)
        binding.recyclerView.layoutManager = LinearLayoutManager(getContext())
        binding.recyclerView.adapter = adapter

        binding.swipeRefreshLayout.isEnabled = false


        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                for(listener in listeners){
                    listener.onSearch(s.toString())
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })


    }

    override fun bindListUsers(users: List<UserSchema>) {
        adapter.bindItems(users)

    }

    override fun showProgressIndication() {
        binding.swipeRefreshLayout.isRefreshing = true
    }

    override fun hideProgressIndication() {
        binding.swipeRefreshLayout.isRefreshing = false

    }

    override fun showStatusIndication() {
        binding.layoutStatus.visibility = View.VISIBLE
    }

    override fun hideStatusIndication() {
        binding.layoutStatus.visibility = View.GONE
    }
}

