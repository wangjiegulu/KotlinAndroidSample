## KotlinAndroidSample
### 1. NetWork with [Kotlin] + [OkHttp] + [RxJava] / [RxAndroid]
Request url("https://github.com/wangjiegulu") and update UI:
```
"https://github.com/wangjiegulu".request().get().rxExecute()
                    .map({ r -> r.body().string() })
                    .observeOnMain()
                    .subscribeSafeNext { result -> Log.d(TAG, "request result: $result"); resultTv.setText("Http request succeed, see log") }
```

### 2. Inject views & events with [AndroidInject] library
Inject "tv"(TextView) view and inject click event of the button.
```
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
```

[AndroidInject]: https://github.com/wangjiegulu/androidInject
[Kotlin]: http://kotlinlang.org/
[OkHttp]: https://github.com/square/okhttp
[RxJava]: https://github.com/ReactiveX/RxJava
[RxAndroid]: https://github.com/ReactiveX/RxAndroid
