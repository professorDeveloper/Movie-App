package com.azamovhudstc.movieappforpdp.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.azamovhudstc.movieappforpdp.R
import com.azamovhudstc.movieappforpdp.ui.adapter.BottomNavigationPageAdapter
import com.azamovhudstc.movieappforpdp.viewmodel.imp.MainViewModelImp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_screen.*

@AndroidEntryPoint
class HomeScreen:Fragment(R.layout.main_screen) {
    private val viewModel by viewModels<MainViewModelImp>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.viewPagerLiveData.observe(this){
            val adapter= BottomNavigationPageAdapter(requireActivity())
            viewPager2.adapter=adapter

            viewPager2.isUserInputEnabled=false
            bottom_bar.setOnItemSelectedListener {
                when(it.itemId){
                    R.id.home_menu->{
                        viewPager2.currentItem=0
                        bottom_bar.getMenu().getItem(0).setChecked(true);
                    }
                    R.id.favorite_menu->{
                        viewPager2.currentItem=1
                        bottom_bar.getMenu().getItem(1).setChecked(true);

                    }
                    R.id.profile_menu->{
                        viewPager2.currentItem=2
                        bottom_bar.getMenu().getItem(2).setChecked(true);
                    }

                }

                false
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewPagerSetup()

    }


    override fun onResume() {

        super.onResume()
    }



}


