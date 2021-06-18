package com.example.homehelper.ui.chat.householder

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homehelper.databinding.ItemHouseHolderListBinding
import com.example.homehelper.models.chat.Letter
import com.example.homehelper.models.chat.MessageList
import com.example.homehelper.util.Constants

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var lists = ArrayList<Letter>()

    private var context: HouseHolderFragment? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = ItemHouseHolderListBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyViewHolder).bind(lists[position])
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    fun setLetters(lists: ArrayList<Letter>) {
        this.lists = lists
        notifyDataSetChanged()
    }

    fun setLetter(letter : Letter) {
        lists.add(letter)
        notifyDataSetChanged()
    }

    fun setContext(context : HouseHolderFragment) {
        this.context = context
    }

    inner class MyViewHolder constructor(val binding: ItemHouseHolderListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(list: Letter) {
            binding.answerText.text = list.answer_text
            binding.answerText.setTextColor(Color.BLACK)

            if(list.reply_type == false) {
                binding.answerText.setBackgroundColor(Color.RED)
                binding.answerText.setTextColor(Color.WHITE)
            }
            else if(list.reply_type == true) {
                binding.answerText.setBackgroundColor(Color.GREEN)
            }

            binding.root.setOnClickListener {
                context!!.showDialog(list)
            }
        }
    }
}