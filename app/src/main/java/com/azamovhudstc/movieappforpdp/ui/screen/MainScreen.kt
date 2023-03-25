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
import com.azamovhudstc.movieappforpdp.ui.adapter.RecentAdapter
import com.azamovhudstc.movieappforpdp.ui.adapter.ViewPagerAdapter
import com.azamovhudstc.movieappforpdp.utils.*
import com.azamovhudstc.movieappforpdp.viewmodel.HomeScreenViewModel
import com.azamovhudstc.movieappforpdp.viewmodel.imp.HomeScreenViewModelImp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.home_screen.*
import kotlinx.android.synthetic.main.home_screen.view.*

@AndroidEntryPoint
class MainScreen:Fragment(R.layout.home_screen) {

    private val viewModel:HomeScreenViewModel by viewModels<HomeScreenViewModelImp>()

    private val adapter by lazy { ViewPagerAdapter() }
    private val recentAdapter by lazy { RecentAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.progressLiveData.observe(this,progressDataObserver)
        viewModel.errorMessageLiveData.observe(this,messageDataObserver)
        viewModel.movieListLiveData.observe(this,movieDataObserver)
        viewModel.recentMovieLiveData.observe(this,recentMovieListObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadDataRecent()
        viewModel.loadData()
        setUpPager()

    }

    private val movieDataObserver=Observer<List<MovieEntity>>{
        adapter.submitList(it)
    }

    private val recentMovieListObserver=Observer<List<LastMovieEntity>> {
        val sortedList = it.sortedByDescending { it.index }

        loadRecentData(sortedList)
    }

        private val progressDataObserver=Observer<Boolean>{
        checkProgress(it)
    }

    private val messageDataObserver=Observer<String>{
        showSnack(it)
    }

    private fun checkProgress(boolean: Boolean){
        requireView().apply {
            if (boolean){
                view_pager_home.invisible()
                popular_tv.invisible()
                progress_bar.visible()
            }else{
                view_pager_home.visible()
                popular_tv.visible()
                progress_bar.invisible()
            }
        }
    }
    private fun setUpPager() {
        view_pager_home.adapter = adapter
        view_pager_home.offscreenPageLimit = 1
        view_pager_home.setPageTransformer(CardTransformer(requireContext()))
        search_iv.setOnClickListener {
            findNavController().navigate(R.id.searchScreen)
        }
        val itemDecoration = HorizontalMarginItemDecoration(
            requireContext(),
            R.dimen.viewpager_current_item_horizontal_margin
        )
        view_pager_home.addItemDecoration(itemDecoration)
        adapter.itemClickListener {
            val bundle= Bundle()
            bundle.putInt("back",0)
            bundle.putInt("data",it)
            findNavController().navigate(R.id.movieDetailScreen,bundle)
        }

    }

    private  fun loadRecentData(recentMovies:List<LastMovieEntity>){
        val list =recentMovies

        if (list.isNotEmpty()) {
            recentAdapter.submitList(list)
            recent_rv.visibility = View.VISIBLE
            recent_tv.visibility = View.VISIBLE
            recent_rv.adapter=recentAdapter
            recentAdapter.setItemClickListener {
                val bundle= Bundle()
                bundle.putInt("back",0)
                bundle.putSerializable("data",it)
                findNavController().navigate(R.id.movieDetailScreen,bundle)

            }
        }else{
            recent_rv.invisible()
            recent_tv.invisible()

        }

    }

    override fun onResume() {

        super.onResume()
    }



}