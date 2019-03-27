package com.cjw.demo1.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cjw.demo1.room.data.UserInfo
import io.reactivex.Flowable

@Dao
interface UserInfoDao {

    @Query("select * from user_info")
    fun queryAllUserInfo(): List<UserInfo>

    @Query("select * from user_info where user_id in (:idList)")
    fun queryUserInfoByIds(vararg idList: Int): List<UserInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserInfo(vararg userInfo: UserInfo)

    @Delete
    fun deleteUserInfo(userInfo: UserInfo): Int

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUserInfo(vararg userInfo: UserInfo)

    @Query("select * from user_info")
    fun queryAllUserInfoByLiveData(): LiveData<List<UserInfo>>

    @Query("select * from user_info where user_id in (:idList)")
    fun queryUserInfoByIdsByLiveData(vararg idList: Int): LiveData<List<UserInfo>>

    @Query("select * from user_info")
    fun queryAllUserInfoByRx(): Flowable<List<UserInfo>>

    @Query("select * from user_info where user_id in (:idList)")
    fun queryUserInfoByIdsByRx(vararg idList: Int): Flowable<List<UserInfo>>
}