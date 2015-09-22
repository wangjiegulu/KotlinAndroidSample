package com.wangjie.kotlinexample.ui.network

import android.util.Log
import com.squareup.okhttp.Callback
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.io.IOException

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 9/22/15.
 */

val TAG: String = "NetWorkExt";

//fun String.request(): Response = OkHttpClient().newCall(Request.Builder().url(this).get().build()).execute()
annotation class schedule();

// OkHttp
fun String.request(): Request.Builder = Request.Builder().url(this);

fun Request.Builder.execute(): Response = OkHttpClient().newCall(this.build()).execute();

class OkhttpCallback(var onFailure: (Request, IOException) -> Unit, var onResponse: (Response) -> Unit) : Callback {
    public override fun onFailure(request: com.squareup.okhttp.Request, e: java.io.IOException) {
        onFailure.invoke(request, e)
    }

    kotlin.throws(java.io.IOException::class)
    public override fun onResponse(response: com.squareup.okhttp.Response) {
        onResponse.invoke(response)
    }
}

fun Request.asyncExecute(onFailure: (Request, IOException) -> Unit, onReponse: (Response) -> Unit) {
    OkHttpClient().newCall(this).enqueue(OkhttpCallback(onFailure, onReponse))
}

// ---- OkHttp with RxJava ----
fun String.rxRequest(): Observable<Response> = Observable.defer({ Observable.just("http://www.baidu.com".request().get().execute()) }).subscribeOn(Schedulers.newThread())

fun Request.Builder.rxExecute(): Observable<Response> = Observable.defer({ Observable.just(OkHttpClient().newCall(this.build()).execute()) }).subscribeOn(Schedulers.newThread());

fun <T> Observable<T>.observeOnMain(): Observable<T> = this.observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.subscribeSafeNext(onNext: (T) -> Unit): Subscription = this.subscribe(onNext, { t -> Log.e(TAG, "", t) }, {});

fun <T> Observable<T>.subscribeSafeCompleted(onCompleted: () -> Unit): Subscription = this.subscribe({}, { t -> Log.e(TAG, "", t) }, onCompleted);




