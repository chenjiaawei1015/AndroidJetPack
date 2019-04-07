package com.cjw.demo1.room.database

import android.content.Context
import android.os.Environment
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cjw.demo1.R
import com.cjw.demo1.logger.Log
import com.cjw.demo1.room.dao.UserDao
import com.cjw.demo1.room.dao.UserInfoDao
import com.cjw.demo1.room.data.User
import com.cjw.demo1.room.data.UserInfo
import java.io.File

@Database(
    entities = [
      User::class, UserInfo::class
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

  abstract fun userDao(): UserDao

  abstract fun userInfoDao(): UserInfoDao

}