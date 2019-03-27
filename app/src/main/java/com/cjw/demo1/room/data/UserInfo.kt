package com.cjw.demo1.room.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_info",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["user_id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
class UserInfo {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_info_id")
    var userInfoId: Long = 0

    @ColumnInfo(name = "user_id")
    var userId: Long = 0

    @ColumnInfo(name = "age")
    var age: Int = 0

    @ColumnInfo(name = "address")
    var address: String = ""
}