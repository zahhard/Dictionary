package com.example.dictionary


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

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

    fun addAccountToDatabase(word: String, meaning: String, example: String, synonym: String//, url : String
        ) {
        WordRepository.insertWord(word, meaning, example, synonym//, url
        )
    }

    fun search (search: String): WordEntity? {
        return WordRepository.search(search)
    }

    fun searchInPersian (search: String): WordEntity? {
        return WordRepository.searchInPersian(search)
    }

    fun update (item: WordEntity){
        WordRepository.update(item)
    }
}