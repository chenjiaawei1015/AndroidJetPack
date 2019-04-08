package com.cjw.demo1.room.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tb_teacher_classes",
    foreignKeys = [
      ForeignKey(
          entity = Classes::class,
          parentColumns = ["classes_id"],
          childColumns = ["classes_id"],
          onDelete = ForeignKey.CASCADE
      ),
      ForeignKey(
          entity = Teacher::class,
          parentColumns = ["teacher_id"],
          childColumns = ["teacher_id"],
          onDelete = ForeignKey.CASCADE
      )
    ],
    indices = [
      Index(value = ["teacher_id"]),
      Index(value = ["classes_id"])
    ]
)
class TeacherClasses {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  var id: Long = 0

  @ColumnInfo(name = "teacher_id")
  var teacherId: Long = 0

  @ColumnInfo(name = "classes_id")
  var classesId: Long = 0

}