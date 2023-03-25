package com.azamovhudstc.movieappforpdp.utils

import android.opengl.Visibility
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

  fun View.visible(){
    this.visibility=View.VISIBLE
}


fun View.invisible(){
    this.visibility=View.INVISIBLE
}
fun View.gone(){
    this.visibility=View.GONE
}

fun Fragment.showSnack(string: String){
    Snackbar.make(requireView(),string,1000).show()
}