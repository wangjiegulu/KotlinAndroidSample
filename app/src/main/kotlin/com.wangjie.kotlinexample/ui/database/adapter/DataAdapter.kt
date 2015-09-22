package com.wangjie.kotlinexample.ui.database.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.TextView
import com.wangjie.androidbucket.adapter.ABaseAdapter
import com.wangjie.androidbucket.application.ABApplication
import com.wangjie.androidbucket.utils.ABTextUtil
import com.wangjie.kotlinexample.ui.database.provider.model.Person
import java.util.ArrayList

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 9/22/15.
 */
public class DataAdapter(var context: Context, lv: AbsListView?) : ABaseAdapter(lv) {
    private var list: ArrayList<Person> = ArrayList();

    public fun setList(list: List<Person>){
        $list.clear();
        $list.addAll(list);
    }

    override fun getCount(): Int {
        return list.size()
    }

    override fun getItem(position: Int): Person? {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var view: View? = null;
        if (null == convertView) {
            var tv: TextView = TextView(context)
            tv.setLayoutParams(AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
            var padding: Int = ABTextUtil.dip2px(ABApplication.getInstance(), 16f);
            tv.setPadding(padding, padding, padding, padding)
            view = tv
        }else{
            view = convertView
        }

        var p: Person = list.get(position);
        (view as TextView).setText("name:${p.name}_student:${p.student}")

        return view;

    }

}
