package com.example.homehelper.ui.events.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.homehelper.SessionManager
import com.example.homehelper.models.NewEvent
import com.example.homehelper.models.NewsEvents1
import com.example.homehelper.network.news1.News1Api
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel @Inject constructor(val news1Api: News1Api, val sessionManager: SessionManager) : ViewModel(){

    private lateinit var liveData: LiveData<List<NewEvent>>

    init {
        getEvents()
    }

    private fun getEvents() {
        val token = "Bearer ${sessionManager.cachedToken}"

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
                                "null",
                                "null"
                            )
                        )
                    )
                }
                .map ( object : Function<NewsEvents1, List<NewEvent>> {
                    override fun apply(it: NewsEvents1): List<NewEvent> {
                        val list = ArrayList<NewEvent>()
                        for (new in it.news) {
                            if(new.dateEnd != null)
                                list.add(new)
                        }
                        list.add(
                            NewEvent(listOf("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-87R7uLZILYWu7w1RyQvNFumrhQvPMdFzNg&usqp=CAU"),
                                "spigs",
                                "err",
                                "err",
                                "2021-06-12",
                                "2021-06-12")
                        )
                        list.add(
                            NewEvent(listOf("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-87R7uLZILYWu7w1RyQvNFumrhQvPMdFzNg&usqp=CAU"),
                                "spoahjag",
                                "err",
                                "err",
                                "2021-6-13",
                                "2021-6-13")
                        )
                        list.add(
                            NewEvent(listOf("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-87R7uLZILYWu7w1RyQvNFumrhQvPMdFzNg&usqp=CAU"),
                                "spoahjag",
                                "err",
                                "err",
                                "2021-6-13",
                                "2021-6-13")
                        )
                        list.add(
                            NewEvent(listOf("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-87R7uLZILYWu7w1RyQvNFumrhQvPMdFzNg&usqp=CAU"),
                                "spoahjag",
                                "err",
                                "err",
                                "2021-6-13",
                                "2021-6-13")
                        )
                        list.add(
                            NewEvent(listOf("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-87R7uLZILYWu7w1RyQvNFumrhQvPMdFzNg&usqp=CAU"),
                                "spoahjag",
                                "err",
                                "err",
                                "2021-6-06T00:00:00Z",
                                "2021-6-13")
                        )
                        return list
                    }
                })
                .subscribeOn(Schedulers.io())
        )
    }

    fun observeNews() = liveData
}
