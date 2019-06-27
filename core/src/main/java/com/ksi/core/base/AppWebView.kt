package com.ksi.core.base

import android.app.ProgressDialog
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.ksi.core.R
import kotlinx.android.synthetic.main.activity_app_web_view.*
import kotlinx.android.synthetic.main.content_app_web_view.*

class AppWebView : AppCompatActivity() {
     lateinit var dialog: ProgressDialog
     var url: String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_web_view)
        setSupportActionBar(toolbar)
        dialog = ProgressDialog.show(
            this, "",
            getString(R.string.plzwait), true
        )
        dialog.setCancelable(true)
        dialog.isIndeterminate = true
        // dialog.setIndeterminateDrawable(getResources().getDrawable(R.drawable.my_progress_indeterminate));
        val intent = intent
        url = intent.getStringExtra("url")
        val from = intent.getStringExtra("from")
        supportActionBar?.setTitle(from)
          webview.getSettings().setUserAgentString("android-webview")
        // webView.getSettings().setJavaScriptEnabled(true);

        webview.loadUrl(url)
        webview.getSettings().setJavaScriptEnabled(true)
        // dialog.dismiss();
        webview.setWebViewClient(object : WebViewClient() {

            override fun onPageFinished(view: android.webkit.WebView, url: String) {
                dialog.dismiss()
                // do your stuff here

            }

        })

    }

}
