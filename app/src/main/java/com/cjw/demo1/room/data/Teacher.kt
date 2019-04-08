package com.cjw.demo1.room.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_teacher")
class Teacher {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "teacher_id")
  var teacherId: Long = 0

  @ColumnInfo(name = "teacher_name")
  var teacherName: String? = null

}