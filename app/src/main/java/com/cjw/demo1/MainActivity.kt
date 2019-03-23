package com.cjw.demo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cjw.demo1.lifecycle.MainLifecycleObserver
import com.cjw.demo1.logger.Log

class MainActivity : AppCompatActivity() {

    private lateinit var mMainLifecycleObserver: MainLifecycleObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.getSingleInstance().info("${javaClass.simpleName} onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mMainLifecycleObserver = MainLifecycleObserver()

        lifecycle.addObserver(mMainLifecycleObserver)
    }

    override fun onStart() {
        Log.getSingleInstance().info("${javaClass.simpleName} onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.getSingleInstance().info("${javaClass.simpleName} onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.getSingleInstance().info("${javaClass.simpleName} onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.getSingleInstance().info("${javaClass.simpleName} onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.getSingleInstance().info("${javaClass.simpleName} onDestroy")
        super.onDestroy()
    }

}
