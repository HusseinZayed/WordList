package com.example.wordlist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel{

        private WordsRepository Repository;
        private LiveData<List<Words>> mAllWords;

        public WordViewModel(@NonNull Application application) {
            super(application);
            Repository = new WordsRepository(application);
            mAllWords = Repository.getAllWords();
        }

        public void insert(Words word) {
            Repository.insert(word);
        }
        public void delete(Words word) {
            Repository.delete(word);
        }
        public void update(Words word) {
            Repository.update(word);
        }
        public void deleteAllWords() {
            Repository.deleteAllWords();
        }
        public LiveData<List<Words>> getAllWords() {
            return mAllWords;
        }


}