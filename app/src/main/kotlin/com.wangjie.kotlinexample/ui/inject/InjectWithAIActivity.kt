package com.wangjie.kotlinexample.ui.inject;

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.wangjie.androidinject.annotation.annotations.base.AIClick
import com.wangjie.androidinject.annotation.annotations.base.AILayout
import com.wangjie.androidinject.annotation.annotations.base.AIView
import com.wangjie.kotlinexample.R
import com.wangjie.kotlinexample.ui.base.BaseActivity
import org.jetbrains.anko.toast

/**
 * Android inject views & Events with AndroidInject library (https://github.com/wangjiegulu/androidInject)
 *
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 9/22/15.
 */
@AILayout(R.layout.activity_inject)
public class InjectWithAIActivity : BaseActivity(){
    @AIView(R.id.activity_inject_tv)
    private var tv: TextView? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tv?.setText("TextView inject succeed")
    }

    @AIClick(R.id.activity_inject_btn)
    public fun onClick(view: View){
        when(view.getId()){
            R.id.activity_inject_btn -> toast("clicked, button inject succeed!")
        }
    }

}
