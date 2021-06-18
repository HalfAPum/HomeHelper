package com.example.homehelper.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.homehelper.R
import com.example.homehelper.databinding.ActivityProfileBinding
import com.example.homehelper.ui.auth.AuthActivity
import com.example.homehelper.viewmodels.ViewModelProviderFactory
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ProfileActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var viewModel: ProfileViewModel

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this, providerFactory).get(ProfileViewModel::class.java)

        initialize()
    }

    private fun initialize() {
        subscribeObservers()
        binding.exitAccount.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.putExtra("Stop", "Stop")
            startActivity(intent)
            finish()
        }
    }

    private fun subscribeObservers() {
        viewModel.observeUser().observe(this) {
            if(it.images == null || it.images == listOf("") || it.images!!.isEmpty()) {
                it.images = listOf("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-87R7uLZILYWu7w1RyQvNFumrhQvPMdFzNg&usqp=CAU")
            }
            //if(it[0].images == "-1") Log.d(Constants.TAG, it[0].header)
            Picasso.get()
                .load(it.images!![0])
                .placeholder(R.drawable.profile_logo)
                .error(R.drawable.profile_logo)
                .into(binding.profileImage)

            binding.aboutInfo.setText(it.name + " " + it.second_name)
        }
    }
}