package com.mobiledev.kotlinsqlite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

/**
 * Created by Manu on 11/7/2017.
 */
class UserListAdapter(context: Context, arrayOfData: ArrayList<UserInfo>)  : BaseAdapter() {

    var arrayOfData : ArrayList<UserInfo>;
    private val mInflator: LayoutInflater

    init {
        this.arrayOfData = arrayOfData
        this.mInflator = LayoutInflater.from(context)
    }

    override fun getItem(position: Int): Any {
        return arrayOfData[position];
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return arrayOfData.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        val view: View?
        val vh: ListRowHolder
        if (convertView == null) {
            view = this.mInflator.inflate(R.layout.row_item, parent, false)
            vh = ListRowHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }
        vh.nameTV.text = arrayOfData[position].name
        vh.ageTV.text = ""+arrayOfData[position].age
        return view
    }




    private class ListRowHolder(row: View?) {
        public val nameTV: TextView
        public val ageTV : TextView

        init {
            this.nameTV = row?.findViewById(R.id.nameTV) as TextView
            this.ageTV = row?.findViewById(R.id.ageTV) as TextView
        }
    }
}