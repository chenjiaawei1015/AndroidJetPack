package com.cjw.demo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.cjw.demo1.logger.Log

class MainActivity : AppCompatActivity() {

    private lateinit var mMainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.getSingleInstance().info("MainActivity onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mMainPresenter = MainPresenter()

        lifecycle.addObserver(mMainPresenter)
    }

    override fun onStart() {
        Log.getSingleInstance().info("MainActivity onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.getSingleInstance().info("MainActivity onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.getSingleInstance().info("MainActivity onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.getSingleInstance().info("MainActivity onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.getSingleInstance().info("MainActivity onDestroy")
        super.onDestroy()
    }

    private class MainPresenter : LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun onCreate() {
            Log.getSingleInstance().info("MainPresenter onCreate")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onStart() {
            Log.getSingleInstance().info("MainPresenter onStart")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onResume() {
            Log.getSingleInstance().info("MainPresenter onResume")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun onPause() {
            Log.getSingleInstance().info("MainPresenter onPause")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onStop() {
            Log.getSingleInstance().info("MainPresenter onStop")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestory() {
            Log.getSingleInstance().info("MainPresenter onStop")
        }
    }
}
