package com.example.homehelper.ui.chat.fundraising

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homehelper.databinding.ItemFundraisingBinding
import com.example.homehelper.models.chat.Fundraising

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var collections: List<Fundraising> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = ItemFundraisingBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyViewHolder).bind(collections[position])
    }

    override fun getItemCount(): Int {
        return collections.size
    }

    fun setNews(collections: List<Fundraising>) {
        this.collections = collections
        notifyDataSetChanged()
    }

    inner class MyViewHolder constructor(val binding: ItemFundraisingBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(collection: Fundraising) {
//            binding.header.text = event.header
//            binding.reasonDescription.text = event.text
//            binding.durationDescription.text = "${event.dateStart} ${event.dateEnd}"
        }
    }
}