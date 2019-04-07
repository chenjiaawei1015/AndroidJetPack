package com.cjw.demo1.navigation

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.cjw.demo1.R
import com.cjw.demo1.base.BaseFragment
import com.cjw.demo1.logger.Log
import com.cjw.demo1.navigation.adapter.ClassesAdapter
import com.cjw.demo1.room.data.Classes
import com.cjw.demo1.room.database.AppDatabase
import com.cjw.demo1.utils.GsonUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.single.SingleToFlowable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main_page4.classes_rv

class MainPage4Fragment : BaseFragment() {

  private lateinit var mAppDatabase: AppDatabase
  private var mClassesAdapter: ClassesAdapter? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_main_page4, container, false)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    mDisposable.add(
        permission.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .subscribe({
              if (it) {
                mAppDatabase = AppDatabase.getAppDatabase(context!!)
                queryLiveData()
              } else {
                Log.error(getString(R.string.storage_permission_failed, ""))
              }
            }, { throwable ->
              Log.error(getString(R.string.storage_permission_failed, throwable.message))
              Log.error(throwable, throwable.message!!)
            })
    )

  }

  private fun queryLiveData() {
    mDisposable.add(SingleToFlowable.fromCallable {
      mAppDatabase.classesDao()
          .queryLiveData()
    }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { liveData: LiveData<List<Classes>> ->
          liveData.observe(this,
              Observer<List<Classes>> {
                if (mClassesAdapter == null) {
                  mClassesAdapter = ClassesAdapter(it)
                  mClassesAdapter!!.addHeaderView(
                      layoutInflater.inflate(R.layout.item_classes_header, null)
                  )

                  classes_rv.adapter = mClassesAdapter
                } else {
                  mClassesAdapter!!.setNewData(it)
                }

                Log.debug(GsonUtils.toJson(it))
              })
        })
  }

}