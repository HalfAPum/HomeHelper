package com.example.homehelper.ui.events.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.homehelper.databinding.LayoutEventsListItemBinding
import com.example.homehelper.models.NewEvent

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var events: List<NewEvent> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutEventsListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyViewHolder).bind(events[position])
    }

    override fun getItemCount(): Int {
        return events.size
    }

    fun setNews(events: List<NewEvent>) {
        this.events = events
        notifyDataSetChanged()
    }

    inner class MyViewHolder constructor(val binding: LayoutEventsListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(event: NewEvent) {
            val imageSlider: ImageSlider = binding.listImage
            if(event.images == null) {
                val layoutParams: ViewGroup.LayoutParams = imageSlider.layoutParams
                layoutParams.height = 0
                imageSlider.layoutParams = layoutParams
            } else {
                val slideModels: MutableList<SlideModel> = ArrayList(event.images.size)
                for (image in event.images) {
                    slideModels.add(SlideModel(image))
                }
                imageSlider.setImageList(slideModels, ScaleTypes.CENTER_CROP)
            }
            binding.header.text = event.header
            binding.reasonDescription.text = event.text
            binding.durationDescription.text = "${event.dateStart} ${event.dateEnd}"
        }
    }
}