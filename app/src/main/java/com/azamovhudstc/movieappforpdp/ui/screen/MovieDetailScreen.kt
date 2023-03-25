package com.azamovhudstc.movieappforpdp.ui.screen

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.activity.addCallback
import androidx.fragment.app.DialogFragment.STYLE_NO_FRAME
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.movieappforpdp.R
import com.azamovhudstc.movieappforpdp.data.local.entity.LastMovieEntity
import com.azamovhudstc.movieappforpdp.data.local.entity.MovieEntity
import com.azamovhudstc.movieappforpdp.data.network.response.Backdrop
import com.azamovhudstc.movieappforpdp.data.network.response.Cast
import com.azamovhudstc.movieappforpdp.data.network.response.FullMovieResponse
import com.azamovhudstc.movieappforpdp.ui.adapter.ActorsAdapter
import com.azamovhudstc.movieappforpdp.ui.adapter.ImagesAdapter
import com.azamovhudstc.movieappforpdp.ui.dialog.MyInfoDialog
import com.azamovhudstc.movieappforpdp.utils.invisible
import com.azamovhudstc.movieappforpdp.utils.showSnack
import com.azamovhudstc.movieappforpdp.utils.visible
import com.azamovhudstc.movieappforpdp.viewmodel.imp.MovieDetailScreenViewModelImp
import com.bumptech.glide.Glide
import com.draggable.library.extension.ImageViewerHelper
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.movie_detail_screen.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.ln
import kotlin.math.pow


@AndroidEntryPoint
class MovieDetailScreen:Fragment(R.layout.movie_detail_screen), AppBarLayout.OnOffsetChangedListener  {
     var data: Int=0

    lateinit var listByUrl:ArrayList<Backdrop>
    lateinit var actorsImagesList:ArrayList<Cast>
    private val adapterImages by lazy { ImagesAdapter() }
    private val actorsAdapter by lazy { ActorsAdapter() }
    lateinit var dataMovie:LastMovieEntity
    private val viewModel by viewModels<MovieDetailScreenViewModelImp>()
    private var state: State? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback {
            when(arguments?.getInt("back")){
                0->{
                    findNavController().navigate(R.id.homeScreen,null,NavOptions.Builder().setPopUpTo(R.id.movieDetailScreen,true,
                        saveState = false
                    ).build())
                }
                1->{
                    findNavController().navigate(R.id.homeScreen,null,NavOptions.Builder().setPopUpTo(R.id.movieDetailScreen,true,
                        saveState = false
                    ).build())
                }
                2->{
                    findNavController().navigate(R.id.homeScreen,null,NavOptions.Builder().setPopUpTo(R.id.movieDetailScreen,true,
                        saveState = false
                    ).build())
                }
                3->{
                    findNavController().navigate(R.id.homeScreen,null,NavOptions.Builder().setPopUpTo(R.id.movieDetailScreen,true,
                        saveState = false
                    ).build())
                }

            }


        }
        listByUrl= ArrayList()
        actorsImagesList=ArrayList()
        viewModel.progressLiveData.observe(this,progressDataObserver)
        viewModel.errorMessageLiveData.observe(this,errorMessageObserver)
        viewModel.fullMovieLiveData.observe(this,fullMovieResponseObserver)
        viewModel.imagesLiveData.observe(this,imagesDataObserver)
        viewModel.authorsListLiveData.observe(this,actorsDataObserver)
        data= arguments?.getInt("data")!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadDataById(data)
        viewModel.loadAuthorsDataById(data)
        viewModel.loadImagesDataById(data)
    }

    private fun clickBookMark() {
        back_iv.setOnClickListener {
            when(arguments?.getInt("back")){
                0->{
                    findNavController().navigate(R.id.homeScreen,null,NavOptions.Builder().setPopUpTo(R.id.movieDetailScreen,true,
                        saveState = false
                    ).build())
                }
                1->{
                    findNavController().navigate(R.id.homeScreen,null,NavOptions.Builder().setPopUpTo(R.id.movieDetailScreen,true,
                        saveState = false
                    ).build())
                }
                2->{
                    findNavController().navigate(R.id.homeScreen,null,NavOptions.Builder().setPopUpTo(R.id.movieDetailScreen,true,
                        saveState = false
                    ).build())
                }
                3->{
                    findNavController().navigate(R.id.homeScreen,null,NavOptions.Builder().setPopUpTo(R.id.movieDetailScreen,true,
                        saveState = false
                    ).build())
                }

            }
        }
        play_button.setOnClickListener {
            var bundle =Bundle()
            bundle.putInt("id",data)
            findNavController().navigate(R.id.watchVideoScreen,bundle)
        }
        play_iv.setOnClickListener {
            var bundle =Bundle()
            bundle.putInt("id",data)
            findNavController().navigate(R.id.watchVideoScreen,bundle)

        }
        save_iv.setOnClickListener {
            dataMovie.isSaved = !dataMovie.isSaved
            dataMovie.isSaved.let { it1 ->
                dataMovie.isSaved=it1
                viewModel.addData(dataMovie)
                setUpSaveView(it1)
            }
        }
    }

    private fun setUpSaveView(boolean: Boolean){
        if (boolean) {
            save_iv.setImageResource(R.drawable.ic_baseline_bookmark_24)
        } else {
            save_iv.setImageResource(R.drawable.ic_baseline_bookmark_border_24)
        }

    }


    private val imagesDataObserver=Observer<List<Backdrop>>{
        if (it.isNotEmpty()){
            images_rv.visible()
            images_rv.adapter=adapterImages
            adapterImages.submitList(it)
            listByUrl.addAll(it)
        }
    }


    private val actorsDataObserver=Observer<List<Cast>>{
        if (it.isNotEmpty()){
            actors_rv.visible()
            actors_rv.adapter=actorsAdapter
            actorsAdapter.submitList(it)
            actorsImagesList.addAll(it)
        }
    }
    private val fullMovieResponseObserver=Observer<LastMovieEntity>{
        loadDataToView(it)
        dataMovie=it

        initBookMark()
        clickBookMark()


    }
    private val errorMessageObserver=Observer<String>{
        showSnack(it)
    }
    private val progressDataObserver=Observer<Boolean>{
        checkProgress(it)
    }

    private fun initBookMark() {
        if (dataMovie.isSaved) {
            save_iv.setImageResource(R.drawable.ic_baseline_bookmark_24)
        } else {
            save_iv.setImageResource(R.drawable.ic_baseline_bookmark_border_24)
        }
    }
    @SuppressLint("InflateParams")
    private fun loadDataToView(fullMovieResponse: LastMovieEntity){
        adapterImages.setItemClickListener {view,url,position->
            val images = ArrayList<ImageViewerHelper.ImageInfo>()
            for (i in 0 until listByUrl.size){
                images.add(ImageViewerHelper.ImageInfo( "https://image.tmdb.org/t/p/w500/"+listByUrl.get(i).file_path))
            }
            ImageViewerHelper.showImages(requireContext(), listOf(view),images,position,showDownLoadBtn = false)



        }
        toolbar_title_tv.text = fullMovieResponse.original_title
        toolbar_title_tv.isSelected = true
        duration_tv.text = "${fullMovieResponse.runtime} minutes"
        Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500/${fullMovieResponse.poster_path}").into(image_view)
        runtime_tv.text = "Runtime: ${fullMovieResponse.runtime /60} h ${fullMovieResponse.runtime %60} min"
        released_data_tv.text = "Released: ${dataFormatter(fullMovieResponse.release_date)}"
        fullMovieResponse.imdb_id?.let { imdb_tv.text = "IMDb: "+it.subSequence(2, fullMovieResponse.imdb_id.length) }
        if (fullMovieResponse.budget !=0) { budget_tv.text = "Budget: $${numberFormatter(fullMovieResponse.budget.toLong())}"
        }else budget_tv.visibility = View.GONE
        val companies = StringBuilder()
        fullMovieResponse.production_companies.forEachIndexed { index, prCompany ->
            companies.append("\n${prCompany.name}" + if (index!=fullMovieResponse.production_companies.size-1) ", " else "")
        }
        val genre = StringBuilder()
        fullMovieResponse.genres.forEachIndexed { index, g ->
            genre.append("${g.name}" + if (index!=fullMovieResponse.genres.size-1) ", " else "")
        }
        companies_tv.text = if (companies.isNotEmpty()) "Production companies: ${companies}" else ""

        description_tv.text = fullMovieResponse.overview

        actorsAdapter.setItemClickListener { imageView, cast ,position->
            val images = ArrayList<ImageViewerHelper.ImageInfo>()
            for (i in 0 until actorsImagesList.size){
                images.add(ImageViewerHelper.ImageInfo( "https://image.tmdb.org/t/p/w500/"+actorsImagesList.get(i).profile_path))
            }
            ImageViewerHelper.showImages(requireContext(), listOf(imageView),images,position,showDownLoadBtn = false)


        }
        rating_progress_bar.progress = (fullMovieResponse.vote_average *10).toInt()
        progress_title_tv.text = fullMovieResponse.vote_average.toString()


        title_tv.text = fullMovieResponse.original_title

    }
    private fun dataFormatter(data:String): String {
        val incomingDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val showedDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
        val date = incomingDateFormat.parse(data)
        return showedDateFormat.format(date)
    }

    private fun setVisibilityLayoutViews(visibility:Int){
        title_tv.visibility = visibility
        genre_tv.visibility = visibility
        runtime_tv.visibility = visibility
    }

    private fun setVisibilityToolbarViews(visibility:Int){
        toolbar_title_tv.visibility = visibility
        duration_tv.visibility = visibility
        play_iv.visibility = visibility
    }
    private fun numberFormatter(count: Long): String {
        if (count < 1000) return "" + count
        val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
        return String.format(
            "%.1f %c",
            count / 1000.0.pow(exp.toDouble()),
            "kMGTPE"[exp - 1]
        )
    }

    private fun checkProgress(boolean: Boolean){
        if(boolean){
            all_data_view.invisible()
            blurview.invisible()
            buy_ticket.invisible()
            progress_bar_detail.visible()
        }else{

            all_data_view.visible()
            blurview.visible()
            buy_ticket.visible()

            progress_bar_detail.invisible()
        }
    }
    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        if (verticalOffset == 0) {
            if (state != State.EXPANDED) {
                setVisibilityLayoutViews(View.VISIBLE)
                setVisibilityToolbarViews(View.GONE)
            }
            state = State.EXPANDED
        } else if (Math.abs(verticalOffset) >= appBarLayout?.totalScrollRange!!) {
            if (state != State.COLLAPSED) {
                setVisibilityLayoutViews(View.GONE)
                setVisibilityToolbarViews(View.VISIBLE)
            }
            state = State.COLLAPSED
        } else {
            if (state != State.IDLE) {
            }
            state = State.IDLE
        }
    }


    private enum class State {
        EXPANDED, COLLAPSED, IDLE
    }

}