package com.example.homehelper.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.homehelper.R
import com.example.homehelper.databinding.ActivityAuthBinding
import com.example.homehelper.databinding.ActivityMainMenuBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainMenuBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        init()
    }

    private fun init() {
        binding.chatButton.setOnClickListener(viewModel)
        binding.fundraisingButton.setOnClickListener(viewModel)
        binding.newsButton.setOnClickListener(viewModel)
        binding.eventsButton.setOnClickListener(viewModel)
        binding.accountingButton.setOnClickListener(viewModel)
        binding.profileButton.setOnClickListener(viewModel)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.observeAction().observe(this) {
            val intent = Intent(this, it)
            startActivity(intent)
            //we dont finish the activity because if we want return to main activity,
            // we need to create main activity again then
            //finish()
        }
    }

}