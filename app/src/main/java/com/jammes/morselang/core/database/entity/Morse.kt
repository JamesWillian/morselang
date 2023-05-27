package com.jammes.morselang.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "morse")
data class Morse(
    @PrimaryKey val uuid: String,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "morse") val morse: String
)
