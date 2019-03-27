package com.cjw.demo1.base

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment : Fragment() {

    protected val mDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onDestroy() {
        mDisposable.clear()
        super.onDestroy()
    }

}