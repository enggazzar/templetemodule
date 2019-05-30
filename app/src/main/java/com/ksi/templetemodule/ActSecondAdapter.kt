package com.ksi.templetemodule

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.ksi.core.base.SimpleAdapter
import com.ksi.core.base.initRec
import com.ksi.core.interfaces.ImplementAdapter
import kotlinx.android.synthetic.main.activity_act_adapter.*
import kotlinx.android.synthetic.main.item_meals.*

class ActSecondAdapter : AppCompatActivity(), ImplementAdapter {
    private val animals: ArrayList<Any> = ArrayList()
    var adapter: SimpleAdapter? = null
    var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_second_adapter)
        addAnimals()
        adapter = initRec(rv as RecyclerView, R.layout.item_meals, animals, this)
    }

    fun addAnimals() {
        animals.add("lemon")
        animals.add("meat")
        animals.add("soup")
        animals.add("gargeer")
        animals.add("nabaty")
        animals.add("flafel")
        animals.add("t3mia")
        animals.add("bashamel")
        animals.add("asbakety")
        animals.add("egg")


    }

    override fun loadMore() {

        if (count < 3) {
            count++
            Handler().postDelayed({
                val animals: ArrayList<Any> = ArrayList()
                animals.add("zabady")
                animals.add("mhlbia")
                adapter?.addMore(animals)
            }, 2000)
        }


    }

    override fun setContentViewHolder(holder: SimpleAdapter.ViewHolder, data: Any,possion :Int) {
        holder.tvMeals.text=data.toString()
    }
}
