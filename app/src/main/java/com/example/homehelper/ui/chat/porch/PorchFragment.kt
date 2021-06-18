package com.example.homehelper.ui.chat.porch

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.homehelper.databinding.FragmentPorchBinding
import com.example.homehelper.models.chat.ChatMessage
import com.example.homehelper.ui.chat.apartment.ApartmentViewModel
import com.example.homehelper.util.Constants
import com.example.homehelper.util.utils
import com.example.homehelper.viewmodels.ViewModelProviderFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PorchFragment : DaggerFragment() {

    private lateinit var viewModel: PorchViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private var _binding: FragmentPorchBinding? = null

    private val binding get() = _binding!!

    private val messageAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPorchBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this, providerFactory).get(PorchViewModel::class.java)
        observeUserId()
        subscribeChatIdObserver()
    }

    private fun observeUserId() {
        viewModel.liveData.observe(viewLifecycleOwner) {
            viewModel.user_id = it
        }
    }

    private fun lateInit() {
        subscribeChatInfo()
        subscribeMessagesObserver()
        binding.recyclerView.adapter = messageAdapter
        binding.sendMessage.setOnClickListener {
            viewModel.sendMessage(binding.editText.text.toString())
            binding.editText.setText("")
        }
    }

    private var householder : String = "unknown"

    private fun subscribeChatInfo() {
        viewModel.fullChatInfo.observe(viewLifecycleOwner) {
            viewModel.inhabitants = it.inhabitants
            if(it.house_keepers!=null)
                householder = it.house_keepers[0].nickname
            if(it.messages != null) {
                for (message in it.messages) {
                    Log.d(Constants.TAG, "${viewModel.user_id}   ${message.user_id}")
                    if (viewModel.user_id == message.user_id)
                        messageAdapter.add(
                            utils.ReceiveMessageItem(
                                ChatMessage(
                                    message.text,
                                    findUsernameById(message.user_id)
                                )
                            )
                        )
//                    else
//                        messageAdapter.add(
//                            utils.SendMessageItem(
//                                ChatMessage(
//                                    message.text,
//                                    findUsernameById(message.user_id)
//                                )
//                            )
//                        )
                }
            }
        }
    }

    private fun findUsernameById(id: Int) : String {
        for (inhabitant in viewModel.inhabitants!!) {
            if(inhabitant.inhabitant_id == id)
                return inhabitant.name + " " + inhabitant.second_name
        }
        return householder
    }

    private fun subscribeChatIdObserver() {
        viewModel.chatIdLiveData.observe(viewLifecycleOwner) {
            viewModel.porch_chat_id = it.porch_chat[0].chat_id
            viewModel.getChatHistory()
            lateInit()
            viewModel.startWebSocketConnection()
        }
    }

    private fun subscribeMessagesObserver() {
        viewModel.outputLiveData.observe(viewLifecycleOwner) {
            if(it.startsWith("@%Error :"))
                return@observe
            val str = it.substring(it.indexOf("body") + 7,it.indexOf("room_id") - 3)
            messageAdapter.add(utils.ReceiveMessageItem(ChatMessage(str)))
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.closeConnection()
    }
}