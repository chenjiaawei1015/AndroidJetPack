package com.cjw.demo1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavArgument
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.cjw.demo1.lifecycle.MainLifecycleObserver
import com.cjw.demo1.logger.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mMainLifecycleObserver: MainLifecycleObserver
    private lateinit var mActivityLiveData: MutableLiveData<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.getSingleInstance().info("${javaClass.simpleName} onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(main_tb)

        // Lifecycle
        mMainLifecycleObserver = MainLifecycleObserver()
        lifecycle.addObserver(mMainLifecycleObserver)

        // LiveData
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

        // Navigation
        // Navigation 动态配置 navGraph
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fg) as NavHostFragment
        val navGraph = navHostFragment.navController.navInflater.inflate(R.navigation.nav_main_fragment_page)

        val navArgument = NavArgument.Builder().setDefaultValue("默认数据").build()
        navGraph.addArgument(getString(R.string.value_fragment_page1_et_text), navArgument)
        navHostFragment.navController.graph = navGraph

        // Navigation 与 BottomNavigationView 联动
        // BottomNavigationView 在布局中写的 menu 文件, 要确保 menu 里的 item id 和 navigation 里的 fragment 的 id 要一致, 不然是不起作用的
        val navController = nav_host_fg.findNavController()
        NavigationUI.setupWithNavController(main_menu_bnv, navController)

        // Navigation 与 ToolBar 联动
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        // onSupportNavigateUp()方法的重写,意味着 Activity 将它的 back 键点击事件的委托出去
        // 如果当前并非栈中顶部的 Fragment, 那么点击 back 键, 返回上一个Fragment
        return findNavController(R.id.nav_host_fg).navigateUp()
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
