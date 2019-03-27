package com.cjw.demo1.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cjw.demo1.room.dao.UserDao
import com.cjw.demo1.room.dao.UserInfoDao
import com.cjw.demo1.room.data.User
import com.cjw.demo1.room.data.UserInfo

@Database(
    entities = [
        User::class, UserInfo::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "cjw"

        fun getAppDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME).build()
        }
    }

    abstract fun userDao(): UserDao

    abstract fun userInfoDao(): UserInfoDao

}