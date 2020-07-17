package com.example.wordlist;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordsRepository {

    private WordsDAO wordsDAO;
    private LiveData<List<Words>> getAllWords;


    public WordsRepository(Application app)
    {
        WordsRoom dp = WordsRoom.wordsRoom(app);
        wordsDAO = dp.wordsDAO();
        getAllWords = wordsDAO.getAllWords();
    }


    //-----------------------------------------------------------
    //all operation
    public LiveData<List<Words>> getAllWords() {
        return getAllWords;
    }

    public void deleteAllWords() {
        new DeleteAllAsyncTask(wordsDAO).execute();
    }

    public void insert (Words word) {
        new InsertAsyncTask(wordsDAO).execute(word);
    }

    public void update (Words word) {
        new UpdateAsyncTask(wordsDAO).execute(word);
    }

    public void delete (Words word) {
        new DeleteAsyncTask(wordsDAO).execute(word);
    }
  //----------------------------------------------------------------------------------

   // do all operation in background beacuse room not allow deal with main thread
    private static class InsertAsyncTask extends AsyncTask<Words,Void,Void>{
        private WordsDAO wordsDAO;
        InsertAsyncTask(WordsDAO wordsDAO){
            this.wordsDAO = wordsDAO ;
        }

        @Override
        protected Void doInBackground(Words... words) {
            wordsDAO.insert(words[0]);
            return null;
        }
    }


    private static class DeleteAsyncTask extends AsyncTask<Words,Void,Void>{
        private WordsDAO wordsDAO;
        DeleteAsyncTask(WordsDAO wordsDAO){
            this.wordsDAO = wordsDAO ;
        }

        @Override
        protected Void doInBackground(Words... words) {
            wordsDAO.delete(words[0]);
            return null;
        }
    }


    private static class UpdateAsyncTask extends AsyncTask<Words,Void,Void>{
        private WordsDAO wordsDAO;
        UpdateAsyncTask(WordsDAO wordsDAO){
            this.wordsDAO = wordsDAO ;
        }

        @Override
        protected Void doInBackground(Words... words) {
            wordsDAO.update(words[0]);
            return null;
        }
    }



    private static class DeleteAllAsyncTask extends AsyncTask<Words,Void,Void>{
        private WordsDAO wordsDAO;
        DeleteAllAsyncTask(WordsDAO wordsDAO){
            this.wordsDAO = wordsDAO ;
        }

        @Override
        protected Void doInBackground(Words... words) {
            wordsDAO.deleteAllWords();
            return null;
        }
    }
    //----------------------------------------------------------------------


}
