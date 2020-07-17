package com.example.wordlist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements setOnMyClick {

    private  WordViewModel wordViewModel;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private FloatingActionButton FAB;
    public static int ADD_CODE = 10;
    public static int Edit_CODE = 11;
    public static String KEY_CODE = "key";
    public static String KEY_WORD = "word";
    public static String KEY_MEAN = "mean";
    public static String KEY_ID = "id";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FAB = findViewById(R.id.FAB);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddNewWord.class);
                intent.putExtra(KEY_CODE,ADD_CODE);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recyclWord);


        RecyclerView.LayoutManager lm = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(lm);
        recyclerView.setHasFixedSize(true);
        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);


        wordViewModel =  new ViewModelProvider(this).get(WordViewModel.class);
        wordViewModel.getAllWords().observe(this, new Observer<List<Words>>() {
            @Override
            public void onChanged( List<Words> words) {
                // Update the cached copy of the words in the adapter.
               // adapter.setWords(words);
                myAdapter.setArrayList((ArrayList<Words>) words,MainActivity.this);
            }
        });



        //swipe
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {


                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Words myWord = myAdapter.getWordAtPosition(position);
                        Toast.makeText(MainActivity.this, "Deleting " +
                                myWord.getWord(), Toast.LENGTH_LONG).show();

                        // Delete the word
                        wordViewModel.delete(myWord);
                    }
                });

        helper.attachToRecyclerView(recyclerView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete:
                wordViewModel.deleteAllWords();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void myClick(int key,Words word) {
        Intent intent = new Intent(MainActivity.this,AddNewWord.class);
        intent.putExtra(KEY_CODE,Edit_CODE);
        intent.putExtra(KEY_WORD,word.getWord());
        intent.putExtra(KEY_MEAN,word.getMeaning());
        intent.putExtra(KEY_ID,word.getId());
        startActivity(intent);
    }
}
