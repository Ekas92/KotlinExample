package com.ekas.finhealkotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.ekas.finhealkotlin.Models.introModel
import com.ekas.finhealkotlin.R


class IntroPagerAdapter(con: Context?, list:ArrayList<introModel>?) : PagerAdapter() {
    var context : Context? = con

    var dataList : ArrayList<introModel>? = list

    var layoutInflater : LayoutInflater? =
        context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataList?.size!!
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var itemView = layoutInflater?.inflate(R.layout.intro_layout_screen, container, false)

        var img = itemView?.findViewById<ImageView>(R.id.intro_img)
        var title = itemView?.findViewById<TextView>(R.id.intro_title)
        var desc = itemView?.findViewById<TextView>(R.id.intro_description)

        img?.setImageResource(dataList?.get(position)?.image!!)
        title?.text = dataList?.get(position)?.title
        desc?.text = dataList?.get(position)?.desc

        container.addView(itemView)
        return itemView!!
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
}