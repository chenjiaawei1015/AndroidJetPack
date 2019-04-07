package com.cjw.demo1.room.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "tb_classes")
class Classes {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "classes_id")
  var classesId: Long = 0

  @ColumnInfo(name = "classes_name")
  var name: String = ""

  @Ignore
  var address: String = ""
}