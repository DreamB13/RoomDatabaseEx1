package com.dreamb.roomdatabaseex1.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "phoneNum") val phoneNum: String?,
    @ColumnInfo(name = "email") val email: String?
)