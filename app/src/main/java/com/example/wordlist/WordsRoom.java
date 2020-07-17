package com.example.wordlist;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Words.class},version = 1)  // must write this if more than entity put , .... in entities array
public abstract class WordsRoom extends RoomDatabase {

    private static WordsRoom instance = null;

    public abstract WordsDAO wordsDAO(); // class DAO

    //singleton
     public static synchronized WordsRoom wordsRoom (final Context context)
     {
         if(instance ==null){
             instance =  Room.databaseBuilder(context.getApplicationContext(),WordsRoom.class,"WordListDB")
                        .fallbackToDestructiveMigration()
                        //.addCallback(roomCallBack) optinal
                        .build();}
         return instance;
     }

     //method callback to do create function
    /*private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {  //inside create meathod call asyncTask
            super.onCreate(db);
            new PopulateDataAsyncTask(instance).execute();
        }
    };*/



     //asynTask to intial not nessecry
  /*  private static class PopulateDataAsyncTask extends AsyncTask<Void,Void, Void>
    {
        private WordsDAO WordsDao;
        PopulateDataAsyncTask(WordsRoom db)
        {
            WordsDao = db.wordsDAO();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            WordsDao.insert(new Words("book", "كتاب"));
            WordsDao.insert(new Words("book", "كتاب"));
            //WordsDao.insert(new Words("book", "كتاب"));
            return null;
        }
    }*/

}
