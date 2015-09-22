package com.wangjie.kotlinexample.ui.network

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.wangjie.kotlinexample.R
import com.wangjie.kotlinexample.ui.base.BaseActivity
import org.jetbrains.anko.find
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 9/22/15.
 */
public class NetWorkActivity : BaseActivity() {
    private val TAG: String = this.javaClass.getSimpleName();
    private val URL: String = "https://github.com/wangjiegulu"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)

        var resultTv = find<TextView>(R.id.activity_network_request_result_tv)

        find<Button>(R.id.activity_network_request_btn).setOnClickListener {

//            Observable.defer({ ->
//                Observable.just(OkHttpClient().newCall(
//                        Request.Builder()
//                                .url(URL)
//                                .get()
//                                .build()
//                ).execute())
//            }).subscribeOn(Schedulers.newThread())
//                    .map({r -> r.body().string()})
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe({ result -> Log.d(TAG, "request result: $result"); resultTv.setText("Http request succeed, see log") }, {throwable -> Log.e(TAG, "", throwable)});


            //                        Thread {
            //                            var result = URL.request().get().execute().body().string();
            //                            runOnUiThread { Log.d(TAG, "request result: $result"); resultTv.setText("Http request succeed, see log") }
            //                        }.start()

            //                        URL.request().get().build().asyncExecute(
            //                                { r, e -> Log.e(TAG, "", e) },
            //                                { r -> Log.d(TAG, "request result: ${r.body().string()}"); Observable.empty<Unit>().observeOn(AndroidSchedulers.mainThread()).subscribe({}, {}, {resultTv.setText("Http request succeed, see log")}) });


            URL.request().get().rxExecute()
                    .map({ r -> r.body().string() })
                    .observeOnMain()
                    .subscribeSafeNext { result -> Log.d(TAG, "request result: $result"); resultTv.setText("Http request succeed, see log") }





        }

    }


}