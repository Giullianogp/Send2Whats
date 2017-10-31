package com.giulliano.send2whats.Adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.giulliano.send2whats.Class.ItemData
import com.giulliano.send2whats.R


/**
 * Created by Giulliano on 31/10/2017.
 */
class SpinnerAdapter(context: Context, GroupId: Int, resource: Int, list: ArrayList<ItemData>) :
        ArrayAdapter<ItemData>(context, GroupId, resource, list) {

    private var groupid: Int = 0
    var list: ArrayList<ItemData> = list
    private var inflater: LayoutInflater

    init {
        this.groupid = GroupId
        this.inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val itemView = inflater.inflate(groupid, parent, false)
        val imageView = itemView.findViewById<ImageView>(R.id.img)

        imageView.setImageResource(list!![position].imageId!!)
        val textView = itemView.findViewById<TextView>(R.id.txt)
        textView.text = list!![position].text
        return itemView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View? {
        return getView(position, convertView, parent)

    }
}