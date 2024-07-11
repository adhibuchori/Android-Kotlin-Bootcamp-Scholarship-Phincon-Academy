package com.adhibuchori.diaryapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.adhibuchori.diaryapp.data.database.DiaryData
import com.adhibuchori.diaryapp.data.repository.DiaryRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val mDiariesRepository: DiaryRepository = DiaryRepository(application)

    fun getAllDiaries(): LiveData<List<DiaryData>> =
        runBlocking { mDiariesRepository.getAllDiaries() }

    fun insert(diaryData: DiaryData) = viewModelScope.launch {
        mDiariesRepository.insert(diaryData)
    }

    fun update(diaryData: DiaryData) = viewModelScope.launch {
        mDiariesRepository.update(diaryData)
    }

    fun delete(diaryData: DiaryData) = viewModelScope.launch {
        mDiariesRepository.delete(diaryData)
    }

    fun updateChecked(diaryData: DiaryData) = viewModelScope.launch {
        mDiariesRepository.update(diaryData)
    }
}