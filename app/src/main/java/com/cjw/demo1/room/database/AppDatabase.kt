package com.cjw.demo1.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cjw.demo1.R
import com.cjw.demo1.logger.Log
import com.cjw.demo1.room.dao.ClassesDao
import com.cjw.demo1.room.dao.StudentDao
import com.cjw.demo1.room.dao.TeacherClassesDao
import com.cjw.demo1.room.dao.TeacherDao
import com.cjw.demo1.room.data.Classes
import com.cjw.demo1.room.data.Student
import com.cjw.demo1.room.data.Teacher
import com.cjw.demo1.room.data.TeacherClasses
import java.io.File

@Database(
    entities = [
      Classes::class, Student::class, Teacher::class, TeacherClasses::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

  companion object {
    private const val DATABASE_NAME = "cjw.db"

    private var database: AppDatabase? = null

    fun getAppDatabase(context: Context): AppDatabase {
      if (database != null) {
        return database!!
      }

      synchronized(AppDatabase::class) {
        if (database == null) {
          val dbFile = File(context.externalCacheDir, DATABASE_NAME)
          Log.info(context.getString(R.string.database_path, dbFile.absolutePath))

          database = Room.databaseBuilder(
              context.applicationContext, AppDatabase::class.java, dbFile.absolutePath
          )
              .build()
        }
      }
      return database!!
    }
  }

  abstract fun classesDao(): ClassesDao

  abstract fun studentDao(): StudentDao

  abstract fun teacherDao(): TeacherDao

  abstract fun teacherClassesDao(): TeacherClassesDao

}