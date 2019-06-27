package com.ksi.core.base

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ksi.core.utlities.LocaleHelper
import com.ksi.core.utlities.LocaleHelper.Companion.setLocale
import com.ksi.core.R
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.layout_error.*
import java.io.IOException

abstract class BaseAct : AppCompatActivity() {
    protected val viewModel by lazy {
        ViewModelProviders.of(this).get(CallViewModel::class.java)
    }
    private lateinit var linearLayout: LinearLayout
    private var progressDialog: Dialog? = null
    //private var loadingDialog: LoadingDialog? = null
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun setContentView(layoutResID: Int) {
        setLocale("ar")
        linearLayout = layoutInflater.inflate(R.layout.activity_base, null) as LinearLayout
        val activityContainer = linearLayout.findViewById(R.id.flContainer) as FrameLayout
        layoutInflater.inflate(layoutResID, activityContainer, true)
        super.setContentView(linearLayout)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_base)
        viewModel.showProgress.observe(this,
            Observer {it?.let { showHideDialog(it) } })
    }
    protected fun enableHomeAsUp() {
        setSupportActionBar(app_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//         app_toolbar?.title="ahmed"


    }
    protected fun setTitle(title: String) {
        app_toolbar.title = title
    }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    override fun onBackPressed() {
        Log.e("bck","base");
        finish()
    }
    private fun showProgressDialog() {
        /*
        if (progressDialog == null) {

            progressDialog = DialogTool(this).initLoadingDialog()

        }
        progressDialog?.let {
            if(!it.isShowing){
                it.show()
            }
        }
        */
    }

    private fun hideProgressDialog() {
        progressDialog?.let {
            if(it.isShowing){
                it.dismiss()
            }
        }


    }
    protected  fun showHideDialog(show: Boolean){
        if(show){
            Log.e("show","progress")
            showProgressDialog()
        }else{
            Log.e("hide","progress")
            hideProgressDialog()
        }

    }
    protected fun showError(@DrawableRes drawable: Int, message: String? = null, throwable: Throwable? = null, onRetry: () -> Unit){
        llError.visibility = View.VISIBLE
        tvError.text = message ?: when (throwable) {
            is IOException -> // Timeout
                "حدث خطا اعد المحاولة مرةاخرى"
            is RuntimeException -> // Unexpected Json response from server
                "تاكد من اتصالك بالانترنت واعد المحاولة مرةاخرى"
            else -> // Other error
                "تاكد من اتصالك بالانترنت واعد المحاولة مرةاخرى"
        }
        btnRetry.setOnClickListener {
            llError.visibility = View.GONE
            onRetry.invoke()

        }

        //call like this  showError(R.drawable.ic_connection, throwable = it) { getMyOrders() }

    }
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }
}
