package com.pembelajar.latihanroom.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pembelajar.latihanroom.model.Word

@Dao
interface DbHelper {
    @Query("SELECT * FROM word_table ORDER BY Word ASC")
    fun showDataByAsc(): LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun delete()
}