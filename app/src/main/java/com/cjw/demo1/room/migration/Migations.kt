package com.cjw.demo1.room.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migations {

  val migration_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
      database.execSQL(
          "alter table tb_classes add width INTEGER default 10 null"
      )
    }
  }

  val migration_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
      database.execSQL(
          "alter table tb_classes add height INTEGER default 20 null"
      )
    }
  }

}