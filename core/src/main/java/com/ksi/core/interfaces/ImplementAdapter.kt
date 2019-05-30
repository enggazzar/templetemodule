package com.ksi.core.interfaces

import com.ksi.core.base.SimpleAdapter

/**
 * Created by lenovo on 2/23/2016.
 */
interface ImplementAdapter {

    fun loadMore()

   fun setContentViewHolder(holder: SimpleAdapter.ViewHolder, data: Any,position:Int )

}
