package com.example.wordlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.wordlist.MainActivity.ADD_CODE;
import static com.example.wordlist.MainActivity.Edit_CODE;
import static com.example.wordlist.MainActivity.KEY_CODE;
import static com.example.wordlist.MainActivity.KEY_ID;
import static com.example.wordlist.MainActivity.KEY_MEAN;
import static com.example.wordlist.MainActivity.KEY_WORD;

public class AddNewWord extends AppCompatActivity {

    EditText txtWord , txtMean;
    WordViewModel wordViewModel;
    MenuItem SaveMenuItem,EditMenuItem;

    int result_key,result_id;
    String result_word,result_mean;
    boolean doEdit =false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_word);

        txtWord = findViewById(R.id.inputWord);
        txtMean = findViewById(R.id.inputMean);

        wordViewModel =  new ViewModelProvider(this).get(WordViewModel.class);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        setTitle("add new word");

        Intent intent = getIntent();
        result_key = intent.getIntExtra(KEY_CODE,-1);
        result_id = intent.getIntExtra(KEY_ID,-1);
        result_word = intent.getStringExtra(KEY_WORD);
        result_mean = intent.getStringExtra(KEY_MEAN);
        int id =10;




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add,menu);
        SaveMenuItem = menu.findItem(R.id.save);
        EditMenuItem = menu.findItem(R.id.edit);
        if(result_key==ADD_CODE){
            EditMenuItem.setVisible(false);
        }
        if(result_key==Edit_CODE){
            SaveMenuItem.setVisible(false);
            txtWord.setText(result_word);
            txtMean.setText(result_mean);
            txtMean.setEnabled(false);
            txtWord.setEnabled(false);
            setTitle("Edit Word");
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
                if(doEdit){
                    updateWord(result_id);
                    Toast.makeText(this,"word edited",Toast.LENGTH_LONG).show();
                    doEdit=false;
                }
                else {
                    saveNewWord();
                    Toast.makeText(this, "word saved", Toast.LENGTH_LONG).show();
                    clearFilleds();
                }
                break;
            case R.id.edit:
                SaveMenuItem.setVisible(true);
                EditMenuItem.setVisible(false);
                txtMean.setEnabled(true);
                txtWord.setEnabled(true);
                doEdit=true;
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveNewWord(){
        String word = txtWord.getText().toString();
        String mean = txtMean.getText().toString();

        if(word.isEmpty()||mean.isEmpty())
           Toast.makeText(getApplicationContext(),"fill all fildes",Toast.LENGTH_LONG).show();
        else
            wordViewModel.insert(new Words(word,mean));

    }

    public void updateWord(int id){
        String word = txtWord.getText().toString();
        String mean = txtMean.getText().toString();

        if(word.isEmpty()||mean.isEmpty())
            Toast.makeText(getApplicationContext(),"fill all fildes",Toast.LENGTH_LONG).show();
        else{
            Words word_obj = new Words(word,mean);
            word_obj.setId(id);
            wordViewModel.update(word_obj);}
      startActivity(new Intent(AddNewWord.this,MainActivity.class));
    }

    public  void clearFilleds()
    {
        txtWord.setText("");
        txtMean.setText("");
    }

}
