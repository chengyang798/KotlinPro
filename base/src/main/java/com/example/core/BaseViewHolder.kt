package com.example.core

import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 * @author  chy
 * @date    2020-04-04
 */
open class BaseViewHolder : RecyclerView.ViewHolder{

    constructor(itemView : View):super(itemView)

    private val viewHashMap: MutableMap<Int, View> = HashMap()
    protected fun <T : View?> getView(@IdRes id: Int): T? {
        var view: View? = viewHashMap[id]
        if (view == null) {
            view = itemView.findViewById(id)
            viewHashMap[id] = view
        }
        return view as T?
    }

     fun setText(@IdRes id: Int, text: String?) {
        (getView<View>(id) as TextView?)!!.text = text
    }
}