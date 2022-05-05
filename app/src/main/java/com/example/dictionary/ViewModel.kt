package com.example.dictionary


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.ArrayList

public class MainViewModel constructor(val app: Application) : AndroidViewModel(app) {

    val countLiveData : LiveData<Int>?
    val list: LiveData<List<WordEntity>>?
    val  listAccount = ArrayList<WordEntity>()

    init {
       // WordRepository.initDB(app.applicationContext)

        viewModelScope.launch {
    WordRepository.initDB(app.applicationContext)
  }
//        thread{
//            WordRepository.initDB(app.applicationContext)
//        }
        countLiveData = WordRepository.getCount()
        list = WordRepository.showAllAccounts()
    }

    fun addAccountToDatabase(word: String, meaning: String, example: String, synonym: String, url : String
        ) {
        WordRepository.insertWord(word, meaning, example, synonym , url
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

    fun deleteWord(temp: WordEntity) {
        WordRepository.delete(temp)
    }


}