package com.ksi.core.base

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ksi.core.interfaces.ImplementAdapter
import kotlinx.android.extensions.LayoutContainer


class SimpleAdapter(var items: ArrayList<Any>, var layOutAdapter: Int, var activty: AppCompatActivity) :
    RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {
    var implementAdapterContentLoadMore: ImplementAdapter? = null
    var lastLoaded: Int = 0
    override fun getItemCount(): Int {
        return items.size
    }

 public   fun addMore(itemsMor: ArrayList<Any>) {

        //  var  x =  items as ArrayList<Data>
        val startIndex = items.size
        items.addAll(itemsMor)
        Log.e("sizeeexx", items.size.toString())
        notifyItemRangeInserted(startIndex, items.size)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                layOutAdapter,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.setContent(items.get(position))

        implementAdapterContentLoadMore?.setContentViewHolder(holder, items.get(position),position)
        if (items.size - 2 == position && position > lastLoaded) {
            lastLoaded = position
            implementAdapterContentLoadMore?.loadMore()
        }
        //
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

/*
        fun setContent(name: String) {
            tvAnimal.text = name

            containerView.setOnClickListener {
                Toast.makeText(tvAnimal.context, "" + adapterPosition, LENGTH_SHORT).show()

            }
        }
        */
    }
}


fun AppCompatActivity.initRec(
    rv: RecyclerView,
    layOut: Int,
    items: ArrayList<Any>,
    implementAdapter: ImplementAdapter
): SimpleAdapter {
    rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    val myAdapter = SimpleAdapter(items, layOut, this)
    myAdapter.implementAdapterContentLoadMore = implementAdapter
    rv.adapter = myAdapter
    return myAdapter
}