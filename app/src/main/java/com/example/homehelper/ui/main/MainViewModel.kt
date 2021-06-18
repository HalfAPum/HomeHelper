package com.example.homehelper.ui.main

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homehelper.R
import com.example.homehelper.ui.chat.ChatActivity
import com.example.homehelper.ui.events.EventsActivity
import com.example.homehelper.ui.news1.News1Activity
import com.example.homehelper.ui.profile.ProfileActivity
import dagger.android.support.DaggerAppCompatActivity

class MainViewModel : ViewModel(), View.OnClickListener{

    private val mutableLiveData = MutableLiveData<Class<*>>()

    override fun onClick(v: View?) {
        if(v == null)
            return
        when(v.id) {
            R.id.chat_button -> mutableLiveData.value = ChatActivity::class.java
//the same activity as chat button< so provide some logic how to start activity with appropriate fragment
//            R.id.fundraising_button -> mutableLiveData.value = ChatActivity::class.java
            R.id.news_button -> mutableLiveData.value = News1Activity::class.java
            R.id.events_button -> mutableLiveData.value = EventsActivity::class.java
//            R.id.accounting_button -> mutableLiveData.value = AccountingActivity::class.java
            R.id.profile_button -> mutableLiveData.value = ProfileActivity::class.java
        }
    }

    fun observeAction() : LiveData<Class<*>> {
        return mutableLiveData
    }
}