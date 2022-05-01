package com.example.dictionary

import android.content.Context
import androidx.lifecycle.LiveData

object WordRepository {
    var db : AppDatabase? = null
    var wordDao  : WordDao? = null

    fun initDB(context : Context){
        db = AppDatabase.getAppDataBase(context)

        wordDao = db?.questionDao()

        addTestData()
    }

    private fun addTestData() {
        wordDao?.insertAll(
            WordEntity("zebra", "گورخر",
                "Crossing roads is always frightening at first, and the patient may have to relearn how to use pelican and zebra crossings."
                ,"fff", "https://en.wikipedia.org/wiki/Zebra"
            ),
            WordEntity("road", "جاده",
                "The road, narrow as any country road, was a single-lane journey for any vehicle.", "artery"
                , "https://en.wikipedia.org/wiki/Road?wprov=srpw1_0"
            )
        )
    }

    fun insertWord(word: String, meaning: String, example: String, synonym: String, url :String
    ){
        val newWord = WordEntity(word, meaning, example, synonym ,url )
        WordEntity(word,  meaning, example, synonym, url)
            .let { db?.questionDao()?.insertAll(it) }
    }

    fun getCount(): LiveData<Int>? {
        return db?.questionDao()?.getCount()
    }

    fun search(search: String) : WordEntity? {
        return db?.questionDao()?.loadWord(search)
    }

    fun searchInPersian(search: String) : WordEntity? {
        return db?.questionDao()?.loadWordInPersian(search)
    }

    fun update (item: WordEntity){
        db?.questionDao()?.update(item)
    }

    fun delete(temp: WordEntity) {
        db?.questionDao()?.deleteWord(temp)
    }
}