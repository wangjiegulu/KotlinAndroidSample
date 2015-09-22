## KotlinAndroidSample
### 1. NetWork with [Kotlin] + [OkHttp] + [RxJava] / [RxAndroid]
Request url("https://github.com/wangjiegulu") and update UI:
```Kotlin
"https://github.com/wangjiegulu".request().get().rxExecute()
                    .map({ r -> r.body().string() })
                    .observeOnMain()
                    .subscribeSafeNext { result -> Log.d(TAG, "request result: $result"); resultTv.setText("Http request succeed, see log") }
```

### 2. Inject views & events with [AndroidInject] library
Inject "tv"(TextView) view and inject click event of the button.
```Kotlin
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

### 3. Database operation with [RapidORM]
- Checkout `feature-rapidorm` branch,
- Checkout [RapidORM] library,
- `setting.gradle` Configuration:
```java
include ':app', ':RapidORM'
project(":RapidORM").projectDir = new File(settingsDir, "[Your relative path of RapidORM library]");
```
```Kotlin
@Table
public data class Person : Serializable{
    @Column(primaryKey = true)
    var id: Int? = null;
    @Column
    var name: String? = null;
    @Column
    var email: String? = null;
    @Column
    var student: Boolean? = null;
}

// Get Person dao
var personDao = DatabaseFactory.getDao(javaClass<PersonDaoImpl>());

// insert
var p = Person()
p.id = 100023L;
p.name = "wangjie"
p.email = "tiantian.china.2@gmail.com"
p.student = true;
personDao.insert(generatePerson());

// delete
personDao.deleteBuilder()
		.setWhere(Where.eq("student", true))
		.delete(personDao)
		
// update
p.student = false
personDao.update(p)

// query
personDao.queryAll()
```


[AndroidInject]: https://github.com/wangjiegulu/androidInject
[Kotlin]: http://kotlinlang.org/
[OkHttp]: https://github.com/square/okhttp
[RxJava]: https://github.com/ReactiveX/RxJava
[RxAndroid]: https://github.com/ReactiveX/RxAndroid
[RapidORM]: https://github.com/wangjiegulu/RapidORM
