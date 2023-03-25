package com.azamovhudstc.movieappforpdp.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.movieappforpdp.R
import com.azamovhudstc.movieappforpdp.data.local.entity.LastMovieEntity
import com.azamovhudstc.movieappforpdp.data.local.entity.MovieEntity
import com.azamovhudstc.movieappforpdp.ui.adapter.BookMarkAdapter
import com.azamovhudstc.movieappforpdp.viewmodel.imp.BookMarkScreenViewModelImp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.bookmark_screen.*
@AndroidEntryPoint
class BookMarkScreen:Fragment(R.layout.bookmark_screen) {
    private val viewModel by viewModels<BookMarkScreenViewModelImp>()
    private val adapter by lazy { BookMarkAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.bookMarkListLiveData.observe(this,bookMarkScreenObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadBookMarkList()
        adapter.setItemClickListener {
            val bundle= Bundle()
            bundle.putInt("back",2)
            bundle.putInt("data",it)
            findNavController().navigate(R.id.movieDetailScreen,bundle)

        }
    }

    private val bookMarkScreenObserver =Observer<List<LastMovieEntity>>{
        adapter.submitList(it)
        rv_bookmark.adapter=adapter
    }

    override fun onPause() {
        println("OnPause")
        super.onPause()
    }

    override fun onStop() {
        println("OnStop")
        super.onStop()
    }

    override fun onStart() {
        println("OnStart")
        super.onStart()
    }

    override fun onResume() {
        viewModel.loadBookMarkList()
        super.onResume()

    }
}