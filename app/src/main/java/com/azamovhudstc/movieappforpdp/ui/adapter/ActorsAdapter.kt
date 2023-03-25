package com.azamovhudstc.movieappforpdp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azamovhudstc.movieappforpdp.R
import com.azamovhudstc.movieappforpdp.data.network.response.Cast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_actors.view.*
import kotlinx.android.synthetic.main.movie_detail_screen.view.*

class ActorsAdapter:ListAdapter<Cast,ActorsAdapter.ActorsWh>(ActorsCallback) {

    private lateinit var listenerClick:((ImageView,String,Int)->Unit)


    fun setItemClickListener(listener:((ImageView,String,Int)->Unit)){
        listenerClick=listener
    }

    inner class ActorsWh(itemView:View):RecyclerView.ViewHolder(itemView){
        fun onBind(data:Cast){
            itemView.apply {
                Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+data.profile_path).into(image_view_actors)
                setOnClickListener {
                    listenerClick.invoke(image_view_actors,"https://image.tmdb.org/t/p/w500/"+data.profile_path,absoluteAdapterPosition)
                }
            }
        }
    }

    object ActorsCallback:DiffUtil.ItemCallback<Cast>(){
        override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return  oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return  oldItem.id==newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsWh {
        return  ActorsWh(LayoutInflater.from(parent.context).inflate(R.layout.item_actors,parent,false))
    }

    override fun onBindViewHolder(holder: ActorsWh, position: Int) {
        holder.onBind(getItem(position))
    }
}