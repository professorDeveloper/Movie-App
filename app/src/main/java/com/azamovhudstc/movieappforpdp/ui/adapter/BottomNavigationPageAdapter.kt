package com.azamovhudstc.movieappforpdp.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.azamovhudstc.movieappforpdp.ui.screen.BookMarkScreen
import com.azamovhudstc.movieappforpdp.ui.screen.MainScreen
import com.azamovhudstc.movieappforpdp.ui.screen.ProfileScreen
import kotlin.random.Random

class BottomNavigationPageAdapter(fragmentManager: FragmentActivity) :
    FragmentStateAdapter(fragmentManager) {


    override fun getItemCount(): Int {
        return 3
    }


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                return  MainScreen()
            }
            1 -> BookMarkScreen()
            else -> {

                val foodScreen = ProfileScreen()
                foodScreen
            }
        }
    }

}