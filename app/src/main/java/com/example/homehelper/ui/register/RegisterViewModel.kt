package com.example.homehelper.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.homehelper.models.House
import com.example.homehelper.models.Porch
import com.example.homehelper.models.Token
import com.example.homehelper.models.chat.Register
import com.example.homehelper.network.register.RegisterApi
import com.example.homehelper.util.Constants
import com.google.gson.JsonObject
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RegisterViewModel @Inject constructor(private val registerApi: RegisterApi) : ViewModel() {

    lateinit var houseIdObserver: LiveData<House>

    lateinit var porchesObserver: LiveData<List<Porch>>

    lateinit var registerObserver: LiveData<Register>

    fun getHouseId(house: String) {
        if(house.isEmpty())
            return

        val jsonObject = JsonObject()
        jsonObject.addProperty("house_number", house)

        houseIdObserver = LiveDataReactiveStreams.fromPublisher(
            registerApi.getHouseIdByName(jsonObject)
                .onErrorReturn {
                    Log.d(Constants.TAG, it.toString())
                    return@onErrorReturn House(-1)
                }
                .subscribeOn(Schedulers.io())
        )
    }

    fun loadPorches(house_id: Int) {
        porchesObserver = LiveDataReactiveStreams.fromPublisher(
            registerApi.getPorches(house_id)
                .onErrorReturn {
                    return@onErrorReturn listOf(Porch(-1))
                }
                .subscribeOn(Schedulers.io())
        )
    }

    fun register(
        email: String,
        password: String,
        name: String,
        second_name: String,
        patronymic: String,
        apartment: String,
        porch_id: Int,
    ) {
        val body = JsonObject()


        registerObserver = LiveDataReactiveStreams.fromPublisher(
            registerApi.postUser(body)
                .onErrorReturn {
                    return@onErrorReturn Register("err", "err")
                }
                .subscribeOn(Schedulers.io())
        )
    }

}