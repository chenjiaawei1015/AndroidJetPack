package com.cjw.demo1.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.cjw.demo1.room.data.Teacher

@Dao
interface TeacherDao {

  @Query("select * from tb_teacher")
  fun queryList(): List<Teacher>

  @Query("select * from tb_teacher")
  fun queryLiveData(): LiveData<List<Teacher>>

  @Query("select * from tb_teacher where teacher_id = :teacherId")
  fun query(teacherId: Long): Teacher

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(vararg teacher: Teacher): List<Long>

  @Update(onConflict = OnConflictStrategy.REPLACE)
  fun update(vararg teacher: Teacher): Int

  @Delete
  fun delete(vararg teacher: Teacher): Int

}