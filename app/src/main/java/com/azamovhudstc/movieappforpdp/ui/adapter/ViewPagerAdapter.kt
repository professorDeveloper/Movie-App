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
import kotlinx.android.synthetic.main.item_pager.view.*

class ViewPagerAdapter :ListAdapter<MovieEntity,ViewPagerAdapter.ViewPagerVh>(ViewPagerCallBack){

   private lateinit var itemClickListener :((Int)->Unit)

    fun itemClickListener(listener:((Int)->Unit)){
        itemClickListener=listener
    }

    inner  class  ViewPagerVh(view: View):RecyclerView.ViewHolder(view){
        fun onBind(data:MovieEntity){
            itemView.apply {
                title_tv.text = data.original_title
                rating_tv.text = "Rating: ${data.vote_average}/10"
                Glide.with(context).load("https://image.tmdb.org/t/p/w500/${data.poster_path}").into(image_view)
                setOnClickListener {
                    data.apply {
                        itemClickListener.invoke(id)

                    }
                }
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerVh {
        return  ViewPagerVh(LayoutInflater.from(parent.context).inflate(R.layout.item_pager,parent,false))
    }

    override fun onBindViewHolder(holder: ViewPagerVh, position: Int) {
        holder.onBind(getItem(position))
    }
    object ViewPagerCallBack:DiffUtil.ItemCallback<MovieEntity>(){
        override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return    oldItem.id ==newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return    oldItem==newItem
        }

    }

}