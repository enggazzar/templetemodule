package com.ksi.core.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ksi.core.R
import com.wang.avi.AVLoadingIndicatorView

/**
 * Created by sobhy.
 */

class MyCustomView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    var listview: RecyclerView
    var progress: AVLoadingIndicatorView
    var errormessageTv: TextView
    var btnRetry: Button
    var retry_layout: LinearLayout


    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.layout_custom_view, this, true)
        listview = view.findViewById(R.id.public_recycler)
        progress = view.findViewById(R.id.aviLoading)
        errormessageTv = view.findViewById(R.id.retry_tv)
        btnRetry = view.findViewById(R.id.retry_btn)
        retry_layout = view.findViewById(R.id.retry_layout)


    }

    fun showprogress(show: Boolean) {
        if (show) {
            progress.visibility = View.VISIBLE
        } else {
            progress.visibility = View.GONE

        }
    }

    fun showerror(show: Boolean) {
        if (show) {
            retry_layout.visibility = View.VISIBLE
        } else {
            retry_layout.visibility = View.GONE

        }
    }

    fun setErrorMessage(message: String) {

        errormessageTv.text = message

    }


}