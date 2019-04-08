package com.cjw.demo1.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.cjw.demo1.room.data.Classes
import com.cjw.demo1.room.data.Teacher
import com.cjw.demo1.room.data.TeacherClasses

@Dao
interface TeacherClassesDao {

  @Query(
      "select * from tb_teacher inner join tb_teacher_classes on tb_teacher.teacher_id = tb_teacher_classes.teacher_id where tb_teacher_classes.classes_id = :classedId"
  )
  fun queryTeacher(classedId: Long): List<Teacher>

  @Query(
      "select * from tb_classes inner join tb_teacher_classes on tb_classes.classes_id = tb_teacher_classes.classes_id where tb_teacher_classes.teacher_id = :teacherId"
  )
  fun queryClasses(teacherId: Long): List<Classes>

  @Query(
      "select * from tb_teacher_classes"
  )
  fun queryList(): List<TeacherClasses>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(vararg teacherClasses: TeacherClasses): List<Long>

  @Update(onConflict = OnConflictStrategy.REPLACE)
  fun update(vararg teacherClasses: TeacherClasses): Int

  @Delete
  fun delete(vararg teacherClasses: TeacherClasses): Int
}