package com.cjw.demo1.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.cjw.demo1.room.data.Classes
import io.reactivex.Flowable

@Dao
interface ClassesDao {

  @Query("select * from tb_classes")
  fun queryList(): List<Classes>

  @Query("select * from tb_classes where classes_id in (:idList)")
  fun queryListById(idList: List<Long>): List<Classes>

  // 对于增加操作
  // 如果参数只有一个, 返回值可以设置为 Long
  // 如果参数有多个, 返回值可以设置为 Long[] 或者 List<Long>
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(vararg insertClasses: Classes)

  // 对于删除操作
  // 可以返回 Int , 代表影响的行数
  @Delete
  fun delete(deleteClasses: Classes): Int

  // 对于更新操作
  // 可以返回 Int , 代表影响的行数
  @Update(onConflict = OnConflictStrategy.REPLACE)
  fun update(vararg updateClasses: Classes)

  @Query("select * from tb_classes")
  fun queryLiveData(): LiveData<List<Classes>>

  @Query("select * from tb_classes where classes_id in (:idList)")
  fun queryLiveDataById(idList: List<Int>): LiveData<List<Classes>>

  @Query("select * from tb_classes")
  fun queryFlowable(): Flowable<List<Classes>>

  @Query("select * from tb_classes where classes_id in (:idList)")
  fun queryFlowableById(idList: List<Int>): Flowable<List<Classes>>

  // 事务
  @Transaction
  fun queryInsert(vararg insertClasses: Classes): List<Classes> {
    insert(*insertClasses)
    val errorData = 1 / 0
    return queryList()
  }

}