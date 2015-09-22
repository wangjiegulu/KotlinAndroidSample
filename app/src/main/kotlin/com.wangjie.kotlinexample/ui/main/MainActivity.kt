package com.wangjie.kotlinexample.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import com.wangjie.kotlinexample.R
import com.wangjie.kotlinexample.ui.base.BaseActivity
import com.wangjie.kotlinexample.ui.inject.InjectWithAIActivity
import com.wangjie.kotlinexample.ui.inject.InjectWithKotlinActivity
import com.wangjie.kotlinexample.ui.network.NetWorkActivity
import org.jetbrains.anko.find

public class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        find<Button>(R.id.activity_main_network_btn).setOnClickListener(onClickListener)
        find<Button>(R.id.activity_main_inject_with_ai_btn).setOnClickListener(onClickListener)
        find<Button>(R.id.activity_main_inject_with_kotlin_btn).setOnClickListener(onClickListener)

    }

    var onClickListener = fun(view: View) {
        when (view.getId()) {
            R.id.activity_main_network_btn -> startActivity(Intent(this@MainActivity, javaClass<NetWorkActivity>()))
            R.id.activity_main_inject_with_ai_btn -> startActivity(Intent(this@MainActivity, javaClass<InjectWithAIActivity>()))
            R.id.activity_main_inject_with_kotlin_btn -> startActivity(Intent(this@MainActivity, javaClass<InjectWithKotlinActivity>()))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item!!.getItemId()

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
