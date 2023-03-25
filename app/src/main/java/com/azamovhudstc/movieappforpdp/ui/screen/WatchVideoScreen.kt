package com.azamovhudstc.movieappforpdp.ui.screen

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.movieappforpdp.R
import com.azamovhudstc.movieappforpdp.data.network.response.ResultXX
import com.azamovhudstc.movieappforpdp.ui.adapter.VideosAdapter
import com.azamovhudstc.movieappforpdp.utils.gone
import com.azamovhudstc.movieappforpdp.utils.invisible
import com.azamovhudstc.movieappforpdp.utils.showSnack
import com.azamovhudstc.movieappforpdp.utils.visible
import com.azamovhudstc.movieappforpdp.viewmodel.imp.WatchVideoViewModelImp
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.watch_video_screen.*
@AndroidEntryPoint
class WatchVideoScreen:Fragment(R.layout.watch_video_screen) {

    private val viewModel by viewModels<WatchVideoViewModelImp>()
    private val videosAdapter by lazy { VideosAdapter() }
    private lateinit var youTubePlayerView:YouTubePlayerView
    private val arrayList=ArrayList<ResultXX>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.progressLiveData.observe(this,progressLiveDataObserver)
        viewModel.messageLiveData.observe(this,messageLiveDataObserver)
        viewModel.resultLiveData.observe(this,videosDataObserver)
        requireActivity().onBackPressedDispatcher.addCallback {
            findNavController().popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id =arguments?.getInt("id",0)
        viewModel.loadVideosDataById(id!!)
        initRv()
        videosAdapter.setItemClickListener {
            youTubePlayerView.removeYouTubePlayerListener(object : AbstractYouTubePlayerListener() {

            })
            initVideoPlayer(arrayList[it].key)
        }
    }

    private fun initVideoPlayer(id:String){
       youTubePlayerView= requireView()!!.findViewById(R.id.youtubeVideo)

        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.enterFullScreen()

        youTubePlayerView.toggleFullScreen()
        fullScreen.setOnClickListener {
            youTubePlayerView.enterFullScreen()
            youTubePlayerView.toggleFullScreen()
            println("if")
            youTubePlayerView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            fullScreen.visibility = View.GONE
            fullExitScreen.visibility = View.VISIBLE

        }
        fullExitScreen.setOnClickListener {

            youTubePlayerView.exitFullScreen()
            youTubePlayerView.toggleFullScreen()
            youTubePlayerView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            fullScreen.visibility = View.VISIBLE
            fullExitScreen.visibility = View.GONE
        }

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(id, 0f)
            }
        })
    }

    private fun initRv(){
        videos_rv.adapter=videosAdapter


    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

    }

    private val videosDataObserver=Observer<List<ResultXX>>{
        for (i in it.indices) {
            arrayList.add(it[i])
        }
        initVideoPlayer(arrayList[0].key)

        videosAdapter.submitList(arrayList)
    }
    private val messageLiveDataObserver =Observer<String>{
        showSnack(it)
    }
    private val progressLiveDataObserver=Observer<Boolean>{
        if (it){
            textView.invisible()
            videos_rv.invisible()
            youtubeVideo.invisible()
            fullExitScreen.invisible()
            fullScreen.invisible()
            progressVideoInfoScreen.visible()
        }else{
            textView.visible()
            videos_rv.visible()
            youtubeVideo.visible()
            fullScreen.visible()
            progressVideoInfoScreen.gone()
        }

    }
}