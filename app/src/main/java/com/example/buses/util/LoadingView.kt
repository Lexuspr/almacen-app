package com.example.buses.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.example.buses.R

class LoadingView(context: Context?) : Dialog(context!!, R.style.Theme_Buses) {
    init {
        this.setContentView(R.layout.view_loading)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        this.setCancelable(false)
    }
}