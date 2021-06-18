package com.example.homehelper.util

import android.view.View
import com.example.homehelper.R
import com.example.homehelper.databinding.ItemMessageSendBinding
import com.example.homehelper.databinding.MyChatMessageBinding
import com.example.homehelper.models.chat.ChatMessage
import com.xwray.groupie.viewbinding.BindableItem

class utils {

    class ReceiveMessageItem(private val message: ChatMessage) : BindableItem<ItemMessageSendBinding>() {
        override fun getLayout(): Int {
            return R.layout.item_message_send
        }

        override fun bind(viewBinding: ItemMessageSendBinding, position: Int) {
            viewBinding.message = message
        }

        override fun initializeViewBinding(view: View): ItemMessageSendBinding {
            return ItemMessageSendBinding.bind(view)
        }

    }

    class SendMessageItem(private val message: ChatMessage) : BindableItem<MyChatMessageBinding>() {
        override fun getLayout(): Int {
            return R.layout.my_chat_message
        }

        override fun bind(viewBinding: MyChatMessageBinding, position: Int) {
            viewBinding.message = message
        }

        override fun initializeViewBinding(view: View): MyChatMessageBinding {
            return MyChatMessageBinding.bind(view)
        }
    }
}

