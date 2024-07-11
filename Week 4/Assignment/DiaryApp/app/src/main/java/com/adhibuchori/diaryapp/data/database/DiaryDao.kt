package com.adhibuchori.diaryapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface DiaryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(diaryData: DiaryData)

    @Update
    suspend fun update(diaryData: DiaryData)

    @Delete
    suspend fun delete(diaryData: DiaryData)

    @Query("SELECT * from diarydata ORDER BY id ASC")
    fun getAllDiaries(): LiveData<List<DiaryData>>
}