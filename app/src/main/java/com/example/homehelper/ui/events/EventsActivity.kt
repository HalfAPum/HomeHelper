package com.example.homehelper.ui.events

import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.homehelper.R
import com.example.homehelper.databinding.ActivityEventsBinding
import com.example.homehelper.databinding.ActivityMainMenuBinding
import com.example.homehelper.ui.events.list.RecyclerViewAdapter
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class EventsActivity : DaggerAppCompatActivity(), CompoundButton.OnCheckedChangeListener{

    private lateinit var binding: ActivityEventsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.checkBox.setOnCheckedChangeListener(this)
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if(isChecked) {
            if(isValidDestination(R.id.calendarFragment))
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.calendarFragment)
        }
        else {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.events, true)
                .build()

            Navigation.findNavController(this, R.id.nav_host_fragment).navigate(
                R.id.listFragment,
                null,
                navOptions)
        }
    }

    private fun isValidDestination(destination: Int) : Boolean {
        return destination != Navigation.findNavController(this, R.id.nav_host_fragment).currentDestination?.id ?: false
    }

    fun onClick(v: View?) {
        binding.checkBox.isChecked = true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (isValidDestination(R.id.calendarFragment))
            binding.checkBox.isChecked = false
    }
}