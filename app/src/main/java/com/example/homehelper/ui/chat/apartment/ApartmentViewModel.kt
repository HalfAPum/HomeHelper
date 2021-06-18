package com.example.homehelper.ui.chat.apartment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homehelper.SessionManager
import com.example.homehelper.models.Profile
import com.example.homehelper.models.User
import com.example.homehelper.models.chat.*
import com.example.homehelper.network.chat.ChatApi
import com.example.homehelper.network.profile.ProfileApi
import com.example.homehelper.util.Constants
import com.google.gson.JsonObject
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import javax.inject.Inject
import kotlin.properties.Delegates

class ApartmentViewModel @Inject constructor(val chatApi: ChatApi,
                                             val sessionManager: SessionManager,
                                             val profileApi: ProfileApi) : ViewModel() {

    lateinit var chatIdLiveData: LiveData<Chats>

    var fullChatInfo : LiveData<FullChatInfo>? = null

    var outputLiveData = MutableLiveData<String>()

    var apartment_chat_id by Delegates.notNull<Int>()

    var ws : WebSocket? = null

    private val client = OkHttpClient()

    private val token = "Bearer ${sessionManager.cachedToken}"

    private val NORMAL_CLOSURE_STATUS = 1000

    var inhabitants: List<Inhabitant>? = null

    lateinit var liveData: LiveData<Int>

    var user_id : Int? = null

    init {
        getProfile()
        getChat()
    }

    private fun getProfile() {
        val token = "Bearer ${sessionManager.cachedToken}"

        Log.d(Constants.TAG, token)

        liveData = LiveDataReactiveStreams.fromPublisher(
            profileApi.getProfile(token)
                .onErrorReturn {
                    return@onErrorReturn Profile(
                        User(
                            -1,
                            "-1",
                            "$it",
                            "err",
                            "err",
                            "err",
                            false,
                            listOf("err")
                        )
                    )
                }
                .map ( object : Function<Profile, Int> {
                    override fun apply(it: Profile): Int {
                        return it.user.inhabitant_id
                    }
                })
                .subscribeOn(Schedulers.io())
        )
    }

    private fun getChat() {
        chatIdLiveData = LiveDataReactiveStreams.fromPublisher(
            chatApi.getChats(token)
                .onErrorReturn {
                    return@onErrorReturn Chats(
                        listOf(Chat(-1)),
                        listOf(Chat(-1))
                    )
                }
                .subscribeOn(Schedulers.io())
        )
    }

    fun getChatHistory() {
        fullChatInfo = LiveDataReactiveStreams.fromPublisher(
            chatApi.getFullChatInfo(token, apartment_chat_id)
                .onErrorReturn {
                    Log.d(Constants.TAG, "errrr")
                    return@onErrorReturn null
                }
                .map { return@map it.info}
                .subscribeOn(Schedulers.io())
        )
    }

    inner class EchoWebSocketListener : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {

        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            outputLiveData.postValue("Receiving : $text")
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            webSocket.close(NORMAL_CLOSURE_STATUS, null)
            outputLiveData.postValue("Closing : $code / $reason")
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            outputLiveData.postValue("@%Error : " + t.message)
        }
    }



    fun startWebSocketConnection() {
        val request = Request.Builder()
            .url("ws://prp-project-server.herokuapp.com/api/chat/ws/$apartment_chat_id")
            .addHeader("Authorization", token)
            .build()
        val listener = EchoWebSocketListener()
        ws = client.newWebSocket(request, listener)


    }

    fun sendMessage(text: String) {
        if(text.isBlank() || text.isEmpty())
            return

        //create json msg
        val jsonObject = JsonObject()
        jsonObject.addProperty("text", text)
        jsonObject.addProperty("chat_id", apartment_chat_id)
        jsonObject.addProperty("is_quiz", false)

        chatApi.postMessage(token, jsonObject, apartment_chat_id)

        ws!!.send(text)
    }

    fun closeConnection() {
        ws!!.close(NORMAL_CLOSURE_STATUS, "Goodbye !")
        client.dispatcher().executorService().shutdown()
    }
}