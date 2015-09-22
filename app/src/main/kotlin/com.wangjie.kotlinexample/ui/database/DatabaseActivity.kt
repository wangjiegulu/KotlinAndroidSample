package com.wangjie.kotlinexample.ui.database

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import com.wangjie.androidinject.annotation.annotations.base.AIClick
import com.wangjie.androidinject.annotation.annotations.base.AIItemClick
import com.wangjie.androidinject.annotation.annotations.base.AILayout
import com.wangjie.androidinject.annotation.annotations.base.AIView
import com.wangjie.kotlinexample.R
import com.wangjie.kotlinexample.ui.base.BaseActivity
import com.wangjie.kotlinexample.ui.database.adapter.DataAdapter
import com.wangjie.kotlinexample.ui.database.provider.DatabaseFactory
import com.wangjie.kotlinexample.ui.database.provider.dao.PersonDaoImpl
import com.wangjie.kotlinexample.ui.database.provider.model.Person
import com.wangjie.rapidorm.core.generate.builder.Where
import org.jetbrains.anko.toast
import java.util.Random

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 9/22/15.
 */
AILayout(R.layout.activity_database)
public class DatabaseActivity : BaseActivity() {
    @AIView(R.id.activity_database_data_lv)
    private var dataLv: ListView? = null
    private var adapter: DataAdapter? = null
    private var random = Random()

    private var personDao: PersonDaoImpl? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DatabaseFactory.resetDatabase()

        personDao = DatabaseFactory.getDao(javaClass<PersonDaoImpl>());

        adapter = DataAdapter(this, dataLv)
        dataLv?.setAdapter(adapter)
        refreshAll()

    }

    @AIClick(R.id.activity_database_add_btn, R.id.activity_database_delete_btn, R.id.activity_database_update_btn, R.id.activity_database_delete_all_btn)
    private fun onClick(view: View) {
        when (view.getId()) {
            R.id.activity_database_add_btn -> {
                personDao?.insert(generatePerson());
                refreshAll()
            }
            R.id.activity_database_delete_btn -> {
                personDao?.deleteBuilder()?.setWhere(Where.eq("student", true))?.delete(personDao)
                refreshAll()
            }
            R.id.activity_database_update_btn -> {
                var p = personDao?.queryBuilder()?.setLimit(1)?.addOrder("id", false)?.queryFirst(personDao)
                var s = p?.student
                p?.student = if (s ?: false) false else true
                personDao?.update(p)
                refreshAll()
            }
            R.id.activity_database_delete_all_btn -> {
                personDao?.deleteAll()
                refreshAll()
            }
        }
    }

    @AIItemClick(R.id.activity_database_data_lv)
    override fun onItemClickCallbackSample(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        toast(adapter?.getItem(position)?.name ?: "")
    }

    private fun refreshAll() {
        adapter?.setList(personDao?.queryAll())
        adapter?.notifyDataSetChanged()
    }

    private fun generatePerson(): Person {
        var i = random.nextInt(Int.MAX_VALUE)
        var p = Person()
        p.id = i;
        p.name = "wangjie_$i"
        p.email = "wangjie_$i@gmail.com"
        p.student = 0 == i % 2;
        return p;
    }

}
