package com.azamovhudstc.movieappforpdp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azamovhudstc.movieappforpdp.R
import com.azamovhudstc.movieappforpdp.data.network.response.ResultXX
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.videos_item.view.*

class VideosAdapter:ListAdapter<ResultXX,VideosAdapter.VideosVh>(VideosItemCallBack) {

    private lateinit var clickItemListener :((Int)->Unit)


    fun setItemClickListener(listener:((Int)->Unit)){
        clickItemListener=listener
    }

    inner class  VideosVh(view:View):RecyclerView.ViewHolder(view){
        fun onBind(data: ResultXX){
            itemView.apply {
                titleVideo.text=data.name
                size.text=data.size.toString()
                site.text=data.site
                setOnClickListener {
                    clickItemListener.invoke(absoluteAdapterPosition)
                }
            }
        }
    }


    object VideosItemCallBack: ItemCallback<ResultXX>(){
        override fun areItemsTheSame(oldItem: ResultXX, newItem: ResultXX): Boolean {
            return  oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: ResultXX, newItem: ResultXX): Boolean {
            return  oldItem==newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosVh {
        return VideosVh(LayoutInflater.from(parent.context).inflate(R.layout.videos_item,parent,false))
    }

    override fun onBindViewHolder(holder: VideosVh, position: Int) {
        holder.onBind(getItem(position))
    }
}