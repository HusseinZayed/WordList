package com.example.wordlist;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "words")  // you must write @entity  but table name is optinal if it not find name table is class name
public class Words {
    @PrimaryKey(autoGenerate = true)  //to make column primary key
    private int id;

    private String word;
    @ColumnInfo(name = "mean")  // optinal if you want change column name
    private String meaning;


    public Words(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //----------------------------------------
    public String getWord() {
        return word;
    }

    public String getMeaning() {
        return meaning;
    }


}
