package com.jammes.morselang.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jammes.morselang.core.database.dao.MorseDao
import com.jammes.morselang.core.database.entity.Morse

@Database(entities = [Morse::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun morseDao(): MorseDao

    companion object {

        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DATABASE_NAME
                    )
                        .build()
                }
            }
            return instance!!
        }

        private const val DATABASE_NAME = "morse-database.db"
    }
}