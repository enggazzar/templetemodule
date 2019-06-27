package com.ksi.templetemodule

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.lifecycle.Observer
import com.ksi.core.base.BaseAct
import com.ksi.core.base.SimpleAdapter
import com.ksi.core.base.initRec
import com.ksi.core.interfaces.ImplementAdapter
import kotlinx.android.synthetic.main.activity_act_adapter.*
import kotlinx.android.synthetic.main.item_animal.*

class ActAdapter : BaseAct(), ImplementAdapter {
    private val animals: ArrayList<Any> = ArrayList()
    var adapter: SimpleAdapter? = null
    var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_adapter)

        intialize()
        viewModel.responseLiveData.observe(this,
            Observer { populoateModel(it) })

    }

    private fun populoateModel(it: Any?) {
        Log.e("have value ", "kkjkdl")
    }

    fun intialize() {
        addAnimals()
//        adapter = initRec(rv as RecyclerView, R.layout.item_animal, animals, this)
        adapter = initRec(customview.listview, R.layout.item_animal, animals, this)

        customview.btnRetry.setOnClickListener {
            customview.showError(false)
        }
    }

    fun addAnimals() {
        animals.add("dog")
        animals.add("cat")
        animals.add("owl")
        animals.add("cheetah")
        animals.add("raccoon")
        animals.add("bird")
        animals.add("snake")
        animals.add("lizard")
        animals.add("hamster")
        animals.add("bear")
        animals.add("hamster")
        animals.add("hamster")
        animals.add("hamster")
        animals.add("hamster")


    }

    override fun loadMore() {
        customview.showProgress(true)

        if (count < 3) {
         count++
            Handler().postDelayed({
                val animals: ArrayList<Any> = ArrayList()
                animals.add("lion")
                animals.add("tiger")
                animals.add("tiger")
                animals.add("tiger")
                animals.add("tiger")
                animals.add("tiger")
                animals.add("tiger")
                animals.add("tiger")

                adapter?.addMore(animals)
                customview.showProgress(false)


            }, 2000)
        }

        if (animals.size > 2) {

          //  customview.showError(true)
        }


    }
  fun  showRecErrore(){
      Log.e("clicked","true")
      customview.showError(true)
    }

    override fun setContentViewHolder(holder: SimpleAdapter.ViewHolder, data: Any, position: Int) {
        with(holder) {
            tvAnimal.text = data.toString()
            tvAnimal.setOnClickListener {
                // startActivity(Intent(this@ActAdapter, ActSecondAdapter::class.java))
                // customview.showerror(true)
                showRecErrore()
            }
        }

    }
}
