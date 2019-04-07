package com.cjw.demo1.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment : Fragment() {

    protected lateinit var permission: RxPermissions

    protected val mDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        permission = RxPermissions(this)
    }

    override fun onDestroy() {
        mDisposable.clear()
        super.onDestroy()
    }

}