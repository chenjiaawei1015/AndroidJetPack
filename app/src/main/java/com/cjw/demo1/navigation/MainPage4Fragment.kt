package com.cjw.demo1.navigation

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cjw.demo1.R
import com.cjw.demo1.base.BaseFragment
import com.cjw.demo1.logger.Log
import com.cjw.demo1.navigation.adapter.ClassesAdapter
import com.cjw.demo1.navigation.adapter.StudentAdapter
import com.cjw.demo1.room.data.Classes
import com.cjw.demo1.room.data.Student
import com.cjw.demo1.room.database.AppDatabase
import com.cjw.demo1.utils.GsonUtils
import com.cjw.demo1.utils.RandomUtils
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.single.SingleToFlowable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main_page4.address_classes_et
import kotlinx.android.synthetic.main.fragment_main_page4.classes_id_student_et
import kotlinx.android.synthetic.main.fragment_main_page4.classes_rv
import kotlinx.android.synthetic.main.fragment_main_page4.clear_classes_bt
import kotlinx.android.synthetic.main.fragment_main_page4.delete_classes_bt
import kotlinx.android.synthetic.main.fragment_main_page4.id_classes_et
import kotlinx.android.synthetic.main.fragment_main_page4.insert_classes_bt
import kotlinx.android.synthetic.main.fragment_main_page4.insert_random_classes_bt
import kotlinx.android.synthetic.main.fragment_main_page4.insert_student_bt
import kotlinx.android.synthetic.main.fragment_main_page4.name_classes_et
import kotlinx.android.synthetic.main.fragment_main_page4.name_student_et
import kotlinx.android.synthetic.main.fragment_main_page4.single_query_classes_bt
import kotlinx.android.synthetic.main.fragment_main_page4.student_rv
import kotlinx.android.synthetic.main.fragment_main_page4.update_classes_bt

class MainPage4Fragment : BaseFragment() {

  private lateinit var mAppDatabase: AppDatabase
  private var mClassesAdapter: ClassesAdapter? = null
  private var mStudentAdapter: StudentAdapter? = null

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
                queryStudentLiveData()
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

    delete_classes_bt.setOnClickListener {
      deleteClasses()
    }

    insert_random_classes_bt.setOnClickListener {
      insertRandomClasses()
    }

    insert_student_bt.setOnClickListener {
      insertStudent()
    }

  }

  private fun insertStudent() {
    mDisposable.add(
        SingleToFlowable.fromCallable {
          val student = Student()
          student.studentName = name_student_et.text.toString()
          student.classesId = classes_id_student_et.text.toString()
              .toLong()
          mAppDatabase.studentDao()
              .insert(student)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    )
  }

  private fun queryStudentLiveData() {
    mDisposable.add(
        SingleToFlowable.fromCallable {
          mAppDatabase.studentDao()
              .queryLiveData()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
              it.observe(this, Observer<List<Student>> { studentList: List<Student> ->
                if (mStudentAdapter == null) {
                  mStudentAdapter = StudentAdapter(studentList)
                  mStudentAdapter!!.addHeaderView(
                      layoutInflater.inflate(R.layout.item_student_header, null)
                  )

                  student_rv.layoutManager = LinearLayoutManager(activity)
                  student_rv.adapter = mStudentAdapter
                } else {
                  mStudentAdapter!!.setNewData(studentList)
                }

                Log.debug(getString(R.string.student_database_data, GsonUtils.toJson(studentList)))

              })
            }
    )
  }

  private fun deleteClasses() {
    mDisposable.add(Flowable.just(id_classes_et.text.toString().toLong())
        .map {
          val classesList = mAppDatabase.classesDao()
              .queryListById(listOf(it))

          var deleteCount = 0
          for (classes in classesList) {
            val res = mAppDatabase.classesDao()
                .delete(classes)
            if (res > 0) {
              deleteCount++
            }
          }

          return@map deleteCount > 0
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
          if (it != null && it) {
            Log.info(getString(R.string.success))
          } else {
            Log.info(getString(R.string.failed))
          }
        }
    )
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

  private fun insertRandomClasses() {
    mDisposable.add(SingleToFlowable.fromCallable {

      val classesList = mutableListOf<Classes>()
      for (index in 0..RandomUtils.nextInt(1, 5)) {
        val classes = Classes()
        classes.name = RandomUtils.nextString(6)
        classes.address = RandomUtils.nextString(10)
        classesList.add(classes)
      }

      mAppDatabase.classesDao()
          .insert(*classesList.toTypedArray())
    }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe())
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

                  classes_rv.layoutManager = LinearLayoutManager(activity)
                  classes_rv.adapter = mClassesAdapter
                } else {
                  mClassesAdapter!!.setNewData(it)
                }

                Log.debug(getString(R.string.classes_database_data, GsonUtils.toJson(it)))
              })
        })
  }

}