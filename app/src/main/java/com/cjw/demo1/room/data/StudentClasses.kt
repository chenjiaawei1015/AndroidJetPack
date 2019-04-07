package com.cjw.demo1.room.data

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
class StudentClasses {

  @ColumnInfo(name = "student_id")
  var studentId: Long = 0

  @ColumnInfo(name = "student_name")
  var studentName: String? = null

  @ColumnInfo(name = "classes_name")
  var className: String? = null

}