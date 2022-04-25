package com.example.dictionary

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*


@Entity
data class WordEntity(
    @PrimaryKey val word : String,
    val meaning : String,
    val example : String,
    val synonym : String,
)


@Database(entities = [WordEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun questionDao(): WordDao

    companion object {
        var db: AppDatabase? = null

        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase {

            val db : AppDatabase by lazy {

                Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java, "myDB")
                    //.allowMainThreadQueries()
                    .build()
            }
            return db

        }
        fun destroyDataBase() {
            db = null
        }
    }
}
@Dao
interface WordDao {

    @Query ("SELECT COUNT(*) FROM WordEntity")
    fun getCount(): LiveData<Int>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg wordEntity: WordEntity)

    @Query("DELETE FROM WordEntity")
    fun deleteAll()
}