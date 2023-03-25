package com.azamovhudstc.movieappforpdp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azamovhudstc.movieappforpdp.R
import com.azamovhudstc.movieappforpdp.data.local.entity.MovieEntity
import com.azamovhudstc.movieappforpdp.data.network.response.Backdrop
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_detail_screen.view.*

class ImagesAdapter:ListAdapter<Backdrop,ImagesAdapter.ImageVh>(ImagesCallback) {

    private lateinit var listenerClick:((ImageView,String,Int)->Unit)


    fun setItemClickListener(listener:((ImageView,String,Int)->Unit)){
        listenerClick=listener
    }


    inner class ImageVh(itemView:View):RecyclerView.ViewHolder(itemView){
        fun onBind(backdrop: Backdrop){
            itemView.apply {
                Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+backdrop.file_path).into(image_view)
                setOnClickListener {
                    listenerClick.invoke(image_view,"https://image.tmdb.org/t/p/w500/"+backdrop.file_path,absoluteAdapterPosition)
                }
            }

        }
    }

    object  ImagesCallback: DiffUtil.ItemCallback<Backdrop>() {
        override fun areItemsTheSame(oldItem: Backdrop, newItem: Backdrop): Boolean {
            return  oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Backdrop, newItem: Backdrop): Boolean {
            return  oldItem==newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageVh {
        return ImageVh(LayoutInflater.from(parent.context).inflate(R.layout.item_image,parent,false))
    }

    override fun onBindViewHolder(holder: ImageVh, position: Int) {
        holder.onBind(getItem(position))
    }
}