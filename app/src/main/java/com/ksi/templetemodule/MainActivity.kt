package com.ksi.templetemodule

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.ksi.core.base.BaseAct
import com.ksi.entities.ModelTest
import com.ksi.usecase.getBaseUrlUseCase
import com.ksi.usecases.enumfiles.enumShared
import com.ksi.usecases.prefadd
import kotlinx.android.synthetic.main.layout_error.*

class MainActivity : BaseAct() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getBaseUrlUseCase()
        setTitle("hhh")
        enableHomeAsUp()
        viewModel.call()

        viewModel.responseLiveData.observe(this,
            Observer { populoateModel(it) })
    }

    private fun populoateModel(it: Any?) {
        llError.visibility = View.GONE
        if (it is ModelTest) {
            Log.e("classname", "TestViewModelAct")
//            tvTest.text = it.title + it.userId
//            print(it.title + it.userId)
        } else if (it is Throwable) {

            prefadd(enumShared.shLanguage.name, "en")

           // resetApi()
            showError(R.drawable.abc_ic_ab_back_material, null, it, { callAgain() })
        }

    }

    private fun callAgain() {
        viewModel.call()
    }
}
