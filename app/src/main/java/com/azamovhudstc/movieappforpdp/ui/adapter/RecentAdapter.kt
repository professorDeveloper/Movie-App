package com.azamovhudstc.movieappforpdp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azamovhudstc.movieappforpdp.R
import com.azamovhudstc.movieappforpdp.data.local.entity.LastMovieEntity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_search.view.*

class RecentAdapter :ListAdapter<LastMovieEntity,RecentAdapter.RecentVh>(RecentCallback){
    private lateinit var listenerClick:((Int)->Unit)


    fun setItemClickListener(listener:((Int)->Unit)){
        listenerClick=listener
    }

    inner  class  RecentVh(view: View):RecyclerView.ViewHolder(view){

        fun  onBind(data:LastMovieEntity){
            itemView.apply {

                title_tv.text = data.original_title
                rating_tv.text = "Rating: ${data.vote_average}"
                Glide.with(context).load("https://image.tmdb.org/t/p/w500/" + data.poster_path)
                    .into(image_view)

                setOnClickListener {
                    listenerClick.invoke(data.id)
                }
            }
        }

    }


    object RecentCallback: DiffUtil.ItemCallback<LastMovieEntity>() {
        override fun areItemsTheSame(oldItem: LastMovieEntity, newItem: LastMovieEntity): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: LastMovieEntity, newItem: LastMovieEntity): Boolean {
            return oldItem.id==newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentVh {
        return  RecentVh(LayoutInflater.from(parent.context).inflate(R.layout.item_recent,parent,false))
    }

    override fun onBindViewHolder(holder: RecentVh, position: Int) {
        holder.onBind(getItem(position))
    }
}