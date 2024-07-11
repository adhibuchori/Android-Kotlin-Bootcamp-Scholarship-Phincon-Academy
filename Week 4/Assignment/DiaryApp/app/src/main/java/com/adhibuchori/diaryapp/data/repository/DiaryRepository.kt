package com.adhibuchori.diaryapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.adhibuchori.diaryapp.data.database.DiaryDao
import com.adhibuchori.diaryapp.data.database.DiaryData
import com.adhibuchori.diaryapp.data.database.DiaryRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DiaryRepository(application: Application) {
    private val mDiaryDao: DiaryDao

    init {
        val db = DiaryRoomDatabase.getDatabase(application)
        mDiaryDao = db.noteDao()
    }

    suspend fun getAllDiaries(): LiveData<List<DiaryData>> =
        withContext(Dispatchers.IO) { mDiaryDao.getAllDiaries() }

    suspend fun insert(diaryData: DiaryData) =
        withContext(Dispatchers.IO) { mDiaryDao.insert(diaryData) }

    suspend fun delete(diaryData: DiaryData) =
        withContext(Dispatchers.IO) { mDiaryDao.delete(diaryData) }

    suspend fun update(diaryData: DiaryData) =
        withContext(Dispatchers.IO) { mDiaryDao.update(diaryData) }
}