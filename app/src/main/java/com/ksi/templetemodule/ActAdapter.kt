package com.ksi.templetemodule

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.ksi.core.base.SimpleAdapter
import com.ksi.core.base.initRec
import com.ksi.core.interfaces.ImplementAdapter
import kotlinx.android.synthetic.main.activity_act_adapter.*
import kotlinx.android.synthetic.main.item_animal.*

class ActAdapter : AppCompatActivity(), ImplementAdapter {
    private val animals: ArrayList<Any> = ArrayList()
    var adapter: SimpleAdapter? = null
    var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_adapter)
        addAnimals()
        adapter = initRec(rv as RecyclerView, R.layout.item_animal, animals, this)
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


    }

    override fun loadMore() {

        if (count < 3) {
            count++
            Handler().postDelayed({
                val animals: ArrayList<Any> = ArrayList()
                animals.add("lion")
                animals.add("tiger")
                adapter?.addMore(animals)
            }, 2000)
        }


    }

    override fun setContentViewHolder(holder: SimpleAdapter.ViewHolder, data: Any,position:Int) {
        with(holder){
            tvAnimal.text=data.toString()
            tvAnimal.setOnClickListener {
                 startActivity(Intent(this@ActAdapter,ActSecondAdapter::class.java))
            }
        }

    }
}
