package com.azamovhudstc.movieappforpdp.ui.screen

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.movieappforpdp.R
import com.azamovhudstc.movieappforpdp.data.local.entity.MovieEntity
import com.azamovhudstc.movieappforpdp.data.network.response.ResultX
import com.azamovhudstc.movieappforpdp.ui.MainActivity
import com.azamovhudstc.movieappforpdp.ui.adapter.SearchAdapter
import com.azamovhudstc.movieappforpdp.utils.invisible
import com.azamovhudstc.movieappforpdp.utils.showSnack
import com.azamovhudstc.movieappforpdp.utils.visible
import com.azamovhudstc.movieappforpdp.viewmodel.SearchScreenViewModel
import com.azamovhudstc.movieappforpdp.viewmodel.imp.SearchScreenViewModelImp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.search_screen.*
@AndroidEntryPoint
class SearchScreen : Fragment(R.layout.search_screen) {
    private val searchAdapter by lazy { SearchAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.messageLiveData.observe(this,messageDataObserver)
        viewModel.progressLiveData.observe(this,progressDataObserver)
        viewModel.searchMoviesLiveData.observe(this,searchMovieDataObserver)
    }
    private val viewModel:SearchScreenViewModel by viewModels<SearchScreenViewModelImp>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewAndSearch()
    }

    private fun initViewAndSearch(){
        search_et.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard()
                viewModel.searchDataByQuery(v.text.toString().toLowerCase())

            }
            true
        }
        rv.adapter=searchAdapter
        searchAdapter.setOnItemClickListener {
            val bundle= Bundle()
            bundle.putInt("back",3)

            bundle.putInt("data",it)
            bundle.putBoolean("isSearch",true)
            findNavController().navigate(R.id.movieDetailScreen,bundle)
        }
    }
    private val searchMovieDataObserver=Observer<List<MovieEntity>>{
        searchAdapter.submitList(it)
    }
    private val messageDataObserver=Observer<String>{
        showSnack(it)
    }
    private val progressDataObserver= Observer<Boolean> {
        if (it){
            rv.invisible()
            progress_bar_search.visible()
        }else{

            rv.visible()
            progress_bar_search.invisible()
        }
    }


    private fun hideKeyboard() {
        (activity as MainActivity).currentFocus?.let { view ->
            val imm =
                (activity as MainActivity).getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}