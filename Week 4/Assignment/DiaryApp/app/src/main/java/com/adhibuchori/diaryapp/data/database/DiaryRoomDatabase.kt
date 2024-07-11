package com.adhibuchori.diaryapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DiaryData::class], version = 1)
abstract class DiaryRoomDatabase : RoomDatabase() {
    abstract fun noteDao(): DiaryDao
    companion object {
        @Volatile
        private var INSTANCE: DiaryRoomDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): DiaryRoomDatabase {
            if (INSTANCE == null) {
                synchronized(DiaryRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        DiaryRoomDatabase::class.java, "diary_database")
                        .build()
                }
            }
            return INSTANCE as DiaryRoomDatabase
        }
    }
}