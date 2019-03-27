package com.cjw.demo1.room.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var userId: Long = 0

    @ColumnInfo(name = "first_name")
    var firstName: String = ""

    @ColumnInfo(name = "last_name")
    var lastName: String = ""

    @ColumnInfo(name = "id_card")
    var idCard: String = ""

    @Ignore
    var birthday: String = ""
}