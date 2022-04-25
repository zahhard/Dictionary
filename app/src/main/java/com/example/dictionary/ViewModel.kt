package com.example.dictionary


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.ArrayList
import kotlin.concurrent.thread

class MainViewModel (app: Application) : AndroidViewModel(app) {

    val countLiveData : LiveData<Int>?

    init {
        WordRepository.initDB(app.applicationContext)
//        viewModelScope.launch {
//            WordRepository.initDB(app.applicationContext)
//        }
//        thread{
//            WordRepository.initDB(app.applicationContext)
//        }
        countLiveData = WordRepository.getCount()
    }

    fun addAccountToDatabase(word: String, meaning: String, example: String, synonym: String) {
        WordRepository.insertAccount(word, meaning, example, synonym)
    }
}