package com.pembelajar.latihanroom.repo

import androidx.lifecycle.LiveData
import com.pembelajar.latihanroom.data.DbHelper
import com.pembelajar.latihanroom.model.Word

class WordRepo (private val dbHelper: DbHelper){
    val allData: LiveData<List<Word>> = dbHelper.showDataByAsc()

    suspend fun inserting(word: Word){
        dbHelper.insert(word)
    }
}