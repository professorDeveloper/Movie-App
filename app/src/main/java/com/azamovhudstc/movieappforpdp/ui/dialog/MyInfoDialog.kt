package com.azamovhudstc.movieappforpdp.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.azamovhudstc.movieappforpdp.R
import com.azamovhudstc.movieappforpdp.data.network.response.Cast
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.actors_info.*
import kotlinx.android.synthetic.main.item_actors.*
import kotlinx.android.synthetic.main.item_actors.view.*

class MyInfoDialog(private val data :Cast):BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.actors_info,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }
}