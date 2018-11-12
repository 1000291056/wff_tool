package com.wff.wff_tool.component.activity

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wff.wff_tool.R
import com.wff.wff_tool.ui.view.CustomImageView
import kotlinx.android.synthetic.main.activity_viewpager.*

class ViewpagerActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager)
        viewpager.adapter=MyAdapter(this)

    }

    class MyAdapter constructor(context: Context) : PagerAdapter() {
        var url:String="http://a.hiphotos.baidu.com/image/pic/item/4a36acaf2edda3ccc4a53e450ce93901213f9216.jpg";
        open var images = listOf<String>(url,url,url,url);
        var mContext: Context? = null;

        init {
            mContext = context
            for (url:String in images){

            }
        }

        override fun isViewFromObject(p0: View, p1: Any): Boolean {
            if (p0 != null && p0.tag != null) {
                return p0 == p1
            }
            return false
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            var view = LayoutInflater.from(mContext).inflate(R.layout.item_viewpager, null)
            view.findViewById<CustomImageView>(R.id.viewpager).setImageUrl(images.get(position))
            return view
        }

        override fun getCount(): Int {
            return images.size
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            super.destroyItem(container, position, `object`)
            container.removeViewAt(position)
        }

    }
}
