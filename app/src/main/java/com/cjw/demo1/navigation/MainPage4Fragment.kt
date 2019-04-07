package com.cjw.demo1.navigation

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cjw.demo1.R
import com.cjw.demo1.base.BaseFragment
import com.cjw.demo1.logger.Log
import com.cjw.demo1.navigation.adapter.ClassesAdapter
import com.cjw.demo1.room.data.Classes
import com.cjw.demo1.room.database.AppDatabase
import com.cjw.demo1.utils.GsonUtils
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.single.SingleToFlowable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main_page4.address_classes_et
import kotlinx.android.synthetic.main.fragment_main_page4.classes_rv
import kotlinx.android.synthetic.main.fragment_main_page4.clear_classes_bt
import kotlinx.android.synthetic.main.fragment_main_page4.id_classes_et
import kotlinx.android.synthetic.main.fragment_main_page4.insert_classes_bt
import kotlinx.android.synthetic.main.fragment_main_page4.name_classes_et
import kotlinx.android.synthetic.main.fragment_main_page4.single_query_classes_bt
import kotlinx.android.synthetic.main.fragment_main_page4.update_classes_bt

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
                queryClassesLiveData()
              } else {
                Log.error(getString(R.string.storage_permission_failed, ""))
              }
            }, { throwable ->
              Log.error(getString(R.string.storage_permission_failed, throwable.message))
              Log.error(throwable, throwable.message!!)
            })
    )

    insert_classes_bt.setOnClickListener {
      insertClasses()
    }

    single_query_classes_bt.setOnClickListener {
      singleQueryClasses()
    }

    clear_classes_bt.setOnClickListener {
      id_classes_et.setText("")
      name_classes_et.setText("")
      address_classes_et.setText("")
    }

    update_classes_bt.setOnClickListener {
      updateClasses()
    }

  }

  private fun updateClasses() {
    mDisposable.add(SingleToFlowable.fromCallable {
      val classes = Classes()
      classes.classesId = id_classes_et.text.toString()
          .toLong()
      classes.name = name_classes_et.text.toString()
      classes.address = address_classes_et.text.toString()
      mAppDatabase.classesDao()
          .update(classes)
    }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe())
  }

  private fun singleQueryClasses() {
    mDisposable.add(
        Flowable.just(id_classes_et.text.toString().toLong())
            .map {
              mAppDatabase.classesDao()
                  .queryListById(listOf(it))
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
              if (it.isNotEmpty()) {
                val firstClasses = it[0]
                name_classes_et.setText(firstClasses.name)
                address_classes_et.setText(firstClasses.address)
              }
            }
    )
  }

  private fun insertClasses() {
    mDisposable.add(SingleToFlowable.fromCallable {
      val classes = Classes()
      classes.name = name_classes_et.text.toString()
      classes.address = address_classes_et.text.toString()

      mAppDatabase.classesDao()
          .insert(classes)
    }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe())
  }

  private fun queryClassesLiveData() {
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

                  classes_rv.layoutManager =
                    LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

                  classes_rv.adapter = mClassesAdapter
                } else {
                  mClassesAdapter!!.setNewData(it)
                }

                Log.debug(GsonUtils.toJson(it))
              })
        })
  }

}