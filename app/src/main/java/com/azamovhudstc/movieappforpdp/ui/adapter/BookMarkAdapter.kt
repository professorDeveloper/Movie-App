package com.azamovhudstc.movieappforpdp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azamovhudstc.movieappforpdp.R
import com.azamovhudstc.movieappforpdp.data.local.entity.LastMovieEntity
import com.azamovhudstc.movieappforpdp.data.local.entity.MovieEntity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_saved.view.*
import kotlinx.android.synthetic.main.movie_detail_screen.view.*
import kotlinx.android.synthetic.main.movie_detail_screen.view.image_view
import kotlinx.android.synthetic.main.movie_detail_screen.view.title_tv

class BookMarkAdapter:ListAdapter<LastMovieEntity,BookMarkAdapter.BookMarkWh>(BookmarkCallback) {


    private lateinit var listenerClick:((Int)->Unit)


    fun setItemClickListener(listener:((Int)->Unit)){
        listenerClick=listener
    }
    inner class BookMarkWh(view:View):RecyclerView.ViewHolder(view){
        fun onBind(movieEntity: LastMovieEntity){
            itemView.apply {
               Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+movieEntity.poster_path).into(image_view)
                title_tv.text=movieEntity.title
                rating_tv.text="Rating: ${movieEntity.vote_average}"
                setOnClickListener {
                    listenerClick.invoke(movieEntity.id)
                }
            }
        }
    }

    object  BookmarkCallback:DiffUtil.ItemCallback<LastMovieEntity>(){
        override fun areItemsTheSame(oldItem: LastMovieEntity, newItem: LastMovieEntity): Boolean {
            return  oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: LastMovieEntity, newItem: LastMovieEntity): Boolean {
            return  oldItem==newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookMarkWh {
        return  BookMarkWh(LayoutInflater.from(parent.context).inflate(R.layout.item_saved,parent,false))
    }

    override fun onBindViewHolder(holder: BookMarkWh, position: Int) {
      holder.onBind(getItem(position))
    }
}