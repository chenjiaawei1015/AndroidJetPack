package com.cjw.demo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.cjw.demo1.lifecycle.MainLifecycleObserver
import com.cjw.demo1.logger.Log

class MainActivity : AppCompatActivity() {

    private lateinit var mMainLifecycleObserver: MainLifecycleObserver
    private lateinit var mActivityLiveData: MutableLiveData<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.getSingleInstance().info("${javaClass.simpleName} onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMainLifecycleObserver = MainLifecycleObserver()
        lifecycle.addObserver(mMainLifecycleObserver)

        mActivityLiveData = MutableLiveData()

        // 与 Lifecycle 不同的是
        // 当调用 observe 方法, 只有当 owner 处于活跃的状态时才会回调 Observer, 否则内部是直接 return 处理的
        // 参考 LifecycleBoundObserver

        // 推荐使用 observe
        // 这个例子中, owner 为 this, 即 Activity 对象
        // 在 Activity 中, 活跃状态位于 onStart 之后, onStop 之前
        mActivityLiveData.observe(this, Observer { value ->
            Log.getSingleInstance().info("LiveData 收到当前的状态为 $value")
        })

        // 如果调用的是 observeForever, 则都会回调 Observer
        // 参考 AlwaysActiveObserver
//        mActivityLiveData.observeForever { value ->
//            Log.getSingleInstance().info("LiveData 收到当前的状态为 $value")
//        }

        mActivityLiveData.value = "onCreate"
    }

    override fun onStart() {
        Log.getSingleInstance().info("${javaClass.simpleName} onStart")
        mActivityLiveData.value = "onStart"
        super.onStart()
    }

    override fun onResume() {
        Log.getSingleInstance().info("${javaClass.simpleName} onResume")
        mActivityLiveData.value = "onResume"
        super.onResume()
    }

    override fun onPause() {
        Log.getSingleInstance().info("${javaClass.simpleName} onPause")
        mActivityLiveData.value = "onPause"
        super.onPause()
    }

    override fun onStop() {
        Log.getSingleInstance().info("${javaClass.simpleName} onStop")
        mActivityLiveData.value = "onStop"
        super.onStop()
    }

    override fun onDestroy() {
        Log.getSingleInstance().info("${javaClass.simpleName} onDestroy")
        mActivityLiveData.value = "onDestroy"
        super.onDestroy()
    }

}
