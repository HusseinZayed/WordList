package com.example.wordlist;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordsDAO {

    @Insert
    void insert(Words word);

    @Update
    void update(Words word);

    @Delete
    void delete(Words word);

    @Query("DELETE FROM words")
    void deleteAllWords();

    @Query("SELECT * FROM words")
    LiveData<List<Words>> getAllWords();
}
