package com.cjw.demo1.room.converter

import androidx.room.TypeConverter
import java.util.Date

class DateTypeConverter {

  @TypeConverter
  fun parseDate(value: Long) = Date(value)

  @TypeConverter
  fun parseDateTimeStramp(date: Date) = date.time
}