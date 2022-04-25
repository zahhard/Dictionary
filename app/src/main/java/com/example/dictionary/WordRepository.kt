package com.example.dictionary

import android.content.Context

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
            WordEntity("zebra", "گورخر", "Crossing roads is always frightening at first, and the patient may have to relearn how to use pelican and zebra crossings.",""),
            WordEntity("road", "جاده", "The road, narrow as any country road, was a single-lane journey for any vehicle.", "artery")
        )
    }

    fun insertAccount(word: String, meaning: String, example: String, synonym: String){
        val newWord = WordEntity(word, meaning, example, synonym)
        newWord?.let { WordEntity(word,  meaning, example, synonym) }
            .let { db?.questionDao()?.insertAll(it) }
    }
}