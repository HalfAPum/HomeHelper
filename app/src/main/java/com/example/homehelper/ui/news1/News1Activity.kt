package com.example.homehelper.ui.news1

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homehelper.databinding.ActivityAuthBinding
import com.example.homehelper.databinding.ActivityNewsBinding
import com.example.homehelper.ui.auth.AuthViewModel
import com.example.homehelper.util.Constants
import com.example.homehelper.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class News1Activity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var adapter: News1RecyclerViewAdapter

    private lateinit var viewModel: News1ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this, providerFactory).get(News1ViewModel::class.java)
        init()
    }

    private fun init() {
        initRecyclerView()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.observeNews().observe(this) {
            if(it.isEmpty()){
                return@observe
            }

            //if(it[0].images == "-1") Log.d(Constants.TAG, it[0].header)
                adapter.setNews(it)
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}