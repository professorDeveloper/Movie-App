package com.azamovhudstc.movieappforpdp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azamovhudstc.movieappforpdp.R
import com.azamovhudstc.movieappforpdp.data.local.entity.LastMovieEntity
import com.azamovhudstc.movieappforpdp.data.local.entity.MovieEntity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_search.view.*
import kotlinx.android.synthetic.main.movie_detail_screen.view.*
import kotlinx.android.synthetic.main.movie_detail_screen.view.image_view
import kotlinx.android.synthetic.main.movie_detail_screen.view.title_tv

class SearchAdapter:ListAdapter<MovieEntity,SearchAdapter.SearchVh>(SearchCallBack) {
    private lateinit var onClickListener:((Int)->Unit)

    fun setOnItemClickListener(listener:((Int)->Unit)){
        onClickListener=listener
    }

    inner  class SearchVh(view:View):RecyclerView.ViewHolder(view){
        fun onBind(resultX: MovieEntity){

            itemView.apply {
                Glide.with(context).load("https://image.tmdb.org/t/p/w500/" + resultX.poster_path).into(image_view)
                title_tv.text=resultX.title
                rating_tv.text="Rating: ${resultX.vote_average}"
                setOnClickListener {
                    resultX.apply {
                        onClickListener.invoke(id)
                    }
                }
            }
        }
    }

    object  SearchCallBack : DiffUtil.ItemCallback<MovieEntity>() {
        override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return  oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return  oldItem.id==newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchVh {
        return  SearchVh(LayoutInflater.from(parent.context).inflate(R.layout.item_search,parent,false))
    }

    override fun onBindViewHolder(holder: SearchVh, position: Int) {
        holder.onBind(getItem(position))
    }
}