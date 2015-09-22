package com.wangjie.kotlinexample.ui.inject

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.wangjie.kotlinexample.R
import com.wangjie.kotlinexample.ui.base.BaseActivity
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import kotlin.reflect.jvm.java

/**
 * Annotation for inject view
 */
annotation class KView(var resId: Int)

/**
 * Android inject view with Kotlin annotations
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 9/22/15.
 */
public class InjectWithKotlinActivity : BaseActivity() {

    @KView(R.id.activity_inject_tv)
    private var tv: TextView? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inject)

        var field: java.lang.reflect.Field = InjectWithKotlinActivity::class.java.getDeclaredField("tv");
        var v: KView = field.getAnnotation(javaClass<KView>())
        field.set(this, find(v.resId))

        tv?.setText("TextView inject succeed")
    }


}
