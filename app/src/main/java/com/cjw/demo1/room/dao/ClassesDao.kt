package com.cjw.demo1.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.cjw.demo1.room.data.Classes
import io.reactivex.Flowable

@Dao
interface ClassesDao {

  @Query("select * from tb_classes")
  fun queryList(): List<Classes>

  @Query("select * from tb_classes where classes_id in (:idList)")
  fun queryListById(idList: List<Int>): List<Classes>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(vararg insertClasses: Classes)

  @Delete
  fun delete(deleteClasses: Classes): Int

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

}