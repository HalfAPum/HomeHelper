package com.example.homehelper.ui.news1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.homehelper.databinding.LayoutNewsListItemBinding
import com.example.homehelper.models.NewEvent
import kotlin.collections.ArrayList

class News1RecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var news: List<NewEvent> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutNewsListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NewsViewHolder).bind(news[position])
    }

    override fun getItemCount(): Int {
        return news.size
    }

    fun setNews(news: List<NewEvent>) {
        this.news = news
        notifyDataSetChanged()
    }

    inner class NewsViewHolder constructor(val binding: LayoutNewsListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(new: NewEvent) {

            val imageSlider: ImageSlider = binding.listImage
            if(new.images == null) {
                val layoutParams: ViewGroup.LayoutParams = imageSlider.layoutParams
                layoutParams.height = 0
                imageSlider.layoutParams = layoutParams
            } else {
                val slideModels: MutableList<SlideModel> = ArrayList(new.images.size)
                for (image in new.images) {
                    slideModels.add(SlideModel(image))
                }
                imageSlider.setImageList(slideModels, ScaleTypes.CENTER_CROP)
            }
            binding.header.text = new.header
            binding.date.text = new.date.substring(0, new.date.indexOf("T"))
            binding.text.text = new.text
        }
    }
}