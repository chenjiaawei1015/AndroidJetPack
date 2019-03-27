package com.cjw.demo1.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.cjw.demo1.R
import com.cjw.demo1.base.BaseFragment
import com.cjw.demo1.logger.Log
import com.cjw.demo1.room.data.User
import com.cjw.demo1.room.data.UserInfo
import com.cjw.demo1.room.database.AppDatabase
import com.google.gson.Gson
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.single.SingleToFlowable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main_page4.*

class MainPage4Fragment : BaseFragment() {

    private lateinit var mAppDatabase: AppDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_page4, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAppDatabase = AppDatabase.getAppDatabase(context!!)

        mDisposable.add(
            SingleToFlowable.fromCallable {
                mAppDatabase.userDao()
                    .queryAllUserByLiveData()
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { data: LiveData<List<User>> ->
                    data.observe(this, Observer { userList: List<User> ->
                        Log.debug(Gson().toJson(userList))
                    })
                }
        )

        add_user_bt.setOnClickListener {
            val user = User()
            user.firstName = first_name_et.text.toString()
            user.lastName = last_name_et.text.toString()
            user.idCard = card_number_et.text.toString()

            mDisposable.add(
                SingleToFlowable.fromCallable {
                    mAppDatabase.userDao().insertUser(user)
                }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.debug("添加成功")
                    }
            )
        }

        add_user_info_bt.setOnClickListener {
            val userInfo = UserInfo()
            userInfo.userId = id_et.text.toString().toLong()
            userInfo.address = address_et.text.toString()
            Flowable.just(userInfo)

            mDisposable.add(
                SingleToFlowable.fromCallable {
                    mAppDatabase.userInfoDao().insertUserInfo(userInfo)
                }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.debug("添加成功")
                    }
            )
        }
    }

    private fun queryAllUser() {
        mDisposable.add(
            mAppDatabase.userDao().queryAllUserByRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { data: List<User> ->
                    Log.debug(data)
                }
        )
    }

}