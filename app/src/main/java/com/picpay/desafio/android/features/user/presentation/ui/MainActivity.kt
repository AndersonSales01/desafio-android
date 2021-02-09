package com.picpay.desafio.android.features.user.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.features.MyApplication
import com.picpay.desafio.android.features.user.presentation.viewmodel.UserViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter
    private lateinit var viewModel: UserViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (applicationContext as MyApplication).appComponent.inject(this)

        viewModel = ViewModelProvider(this,viewModelFactory).get(UserViewModel::class.java)

        setupUI()

        observables()

        viewModel.getUsers()
    }

    private fun setupUI(){
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)
        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun observables() {
        viewModel.usersList.observe(this, Observer { list->
            recyclerView.visibility = View.VISIBLE
            adapter.users = list
        })

        viewModel.error.observe(this, Observer { messageError ->
            recyclerView.visibility = View.GONE
            Toast.makeText(this@MainActivity, messageError, Toast.LENGTH_SHORT)
                .show()
        })

        viewModel.loading.observe(this, Observer { load->
            if(load){
                progressBar.visibility = View.VISIBLE
            }else {
                progressBar.visibility = View.GONE
            }
        })
    }
}
