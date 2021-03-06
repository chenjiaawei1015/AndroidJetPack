package com.cjw.demo1.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.cjw.demo1.room.data.Student
import com.cjw.demo1.room.data.StudentClasses
import io.reactivex.Flowable

@Dao
interface StudentDao {

  @Query("select * from tb_student")
  fun queryList(): List<Student>

  @Query("select * from tb_student where student_id in (:idList)")
  fun queryListById(vararg idList: Long): List<Student>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(vararg student: Student)

  @Delete
  fun delete(student: Student): Int

  @Update(onConflict = OnConflictStrategy.REPLACE)
  fun update(vararg student: Student)

  @Query("select * from tb_student")
  fun queryLiveData(): LiveData<List<Student>>

  @Query("select * from tb_student where student_id in (:idList)")
  fun queryLiveDataById(vararg idList: Int): LiveData<List<Student>>

  @Query("select * from tb_student")
  fun queryFlowable(): Flowable<List<Student>>

  @Query("select * from tb_student where student_id in (:idList)")
  fun queryFlowableById(vararg idList: Int): Flowable<List<Student>>

  @Query(
      "select tb_student.student_id,tb_student.student_name,tb_classes.classes_name from tb_student,tb_classes where tb_student.classes_id = tb_classes.classes_id"
  )
  fun queryStudentClasses(): Flowable<List<StudentClasses>>
}