package com.example.homehelper.ui.news1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.homehelper.SessionManager
import com.example.homehelper.models.NewEvent
import com.example.homehelper.models.NewsEvents1
import com.example.homehelper.network.news1.News1Api
import com.example.homehelper.util.Constants
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class News1ViewModel @Inject constructor(val news1Api: News1Api, val sessionManager: SessionManager): ViewModel() {

    private lateinit var liveData: LiveData<List<NewEvent>>

    init {
        getNews()
    }

    private fun getNews() {
        val token = "Bearer ${sessionManager.cachedToken}"

        Log.d(Constants.TAG, token)

        liveData = LiveDataReactiveStreams.fromPublisher(
            news1Api.getNews(token)
            .onErrorReturn {
                return@onErrorReturn NewsEvents1(
                    listOf(
                        NewEvent(
                            listOf("-1"),
                            "$it",
                            "err",
                            "err",
                            null,
                            null
                        )
                    )
                )
            }
                .map ( object : Function<NewsEvents1, List<NewEvent>> {
                override fun apply(it: NewsEvents1): List<NewEvent> {
                    val list = ArrayList<NewEvent>()
                    for (new in it.news) {
                        if(new.dateEnd == null)
                            list.add(new)
                    }
                    return list
                    }
                })
            .subscribeOn(Schedulers.io())
        )
    }

    fun observeNews() = liveData
}