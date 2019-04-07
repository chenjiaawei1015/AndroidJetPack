package com.cjw.demo1.room.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "tb_student",
    foreignKeys = [
      ForeignKey(
          entity = Classes::class,
          parentColumns = ["classes_id"],
          childColumns = ["classes_id"],
          onDelete = ForeignKey.CASCADE
      )
    ]
)
class Student {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "student_id")
  var studentId: Long = 0

  @ColumnInfo(name = "classes_id")
  var classesId: Long = 0

  @ColumnInfo(name = "student_name")
  var studentName: String? = null
}