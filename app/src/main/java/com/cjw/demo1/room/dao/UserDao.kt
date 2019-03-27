package com.cjw.demo1.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cjw.demo1.room.data.User
import io.reactivex.Flowable

@Dao
interface UserDao {

    @Query("select * from user")
    fun queryAllUser(): List<User>

    @Query("select * from user where user_id in (:idList)")
    fun queryUserByIds(idList: List<Int>): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(vararg user: User)

    @Delete
    fun deleteUser(user: User): Int

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(vararg user: User)

    @Query("select * from user")
    fun queryAllUserByLiveData(): LiveData<List<User>>

    @Query("select * from user where user_id in (:idList)")
    fun queryUserByIdsByLiveData(idList: List<Int>): LiveData<List<User>>

    @Query("select * from user")
    fun queryAllUserByRx(): Flowable<List<User>>

    @Query("select * from user where user_id in (:idList)")
    fun queryUserByIdsByRx(idList: List<Int>): Flowable<List<User>>

}