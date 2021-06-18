package com.example.homehelper.ui.chat

import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.example.homehelper.R
import com.example.homehelper.databinding.ActivityChatBinding
import com.example.homehelper.databinding.ActivityEventsBinding
import dagger.android.support.DaggerAppCompatActivity

class ChatActivity : DaggerAppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.porchButton.setOnClickListener(this)
        binding.apartmentButton.setOnClickListener(this)
        binding.houseKeeperButton.setOnClickListener(this)
        binding.collectionButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.porch_button -> {
                if(isValidDestination(R.id.porchFragment)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment).popBackStack()
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.porchFragment)
                }
            }
            R.id.apartment_button -> {
                if(isValidDestination(R.id.apartmentFragment)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment).popBackStack()
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.apartmentFragment)
                }
            }
            R.id.house_keeper_button -> {
                if(isValidDestination(R.id.houseHolderFragment)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment).popBackStack()
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.houseHolderFragment)
                }
            }
            R.id.collection_button -> {
                if(isValidDestination(R.id.fundraisingFragment)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment).popBackStack()
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.fundraisingFragment)
                }
            }
        }
    }

    private fun isValidDestination(destination: Int) : Boolean {
        return destination != Navigation.findNavController(this, R.id.nav_host_fragment).currentDestination?.id ?: false
    }
}