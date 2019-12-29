package com.pembelajar.latihanroom.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.pembelajar.latihanroom.data.WordDbRoom
import com.pembelajar.latihanroom.model.Word
import com.pembelajar.latihanroom.repo.WordRepo
import kotlinx.coroutines.launch

class WordViewModel (application: Application): AndroidViewModel(application){
    private val repo: WordRepo

    val allData: LiveData<List<Word>>

    init {
        val wordHelper = WordDbRoom.getDb(application, viewModelScope).dbHelper()
        repo = WordRepo(wordHelper)
        allData = repo.allData
    }

    fun insert(word: Word) = viewModelScope.launch{
        repo.inserting(word)
    }

}