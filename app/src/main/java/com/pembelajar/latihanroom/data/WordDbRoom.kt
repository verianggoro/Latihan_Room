package com.pembelajar.latihanroom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.pembelajar.latihanroom.model.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordDbRoom : RoomDatabase() {
    abstract fun dbHelper(): DbHelper

    companion object {
        @Volatile
        private var INSTANCE: WordDbRoom? = null

        fun getDb(
            context: Context,
            scope: CoroutineScope
        ): WordDbRoom {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordDbRoom::class.java,
                    "word_db"
                ).addCallback(WordDbCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class WordDbCallback(
            private val scope: CoroutineScope
        ): RoomDatabase.Callback(){
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let {
                    scope.launch(Dispatchers.IO) {
                        populateDb(it.dbHelper())
                    }
                }
            }
        }

        suspend fun populateDb(dbHelper: DbHelper){
            dbHelper.delete()

            var word = Word("Hello")
            dbHelper.insert(word)
            word = Word("WORLD !")
            dbHelper.insert(word)
        }
    }


}