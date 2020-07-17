package com.example.wordlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.example.wordlist.MainActivity.Edit_CODE;


// class adapter   should extend    <> have ViewHolder (class extend from RecyclerView.ViewHolder )
// in inside this class send data to layout
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {

    List<Words> modelview = new ArrayList<>();
    setOnMyClick listner;
    public void setArrayList(List<Words> modelview,setOnMyClick listner) {
        this.modelview = modelview;
        this.listner =listner;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    //note : this method do with number item(view_holder)  which display on screen only after this no
    // this oppsite list view all time dispaly item call method getView() from adapter
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate to custom layout which hold data
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_word, null, false);
        // send layout to class myViewHolder
        myViewHolder myHolder = new myViewHolder(v);

        //return holder to can all method see it
        return myHolder;
    }


    // link data to holder
    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        Words model = modelview.get(position);
        holder.word.setText(model.getWord());
        holder.mean.setText(model.getMeaning());
        holder.mean.setTag(model);

    }

    @Override
    public int getItemCount() {
        return modelview.size();
    }


    // holder class to recive custom_layout to can find_view_item
    //may declare out no problem

    class myViewHolder extends RecyclerView.ViewHolder {
        TextView word,mean;

        public myViewHolder(@NonNull View itemView) {  // itemView == custom_layout
            super(itemView);
            word = itemView.findViewById(R.id.txtWord);
            mean = itemView.findViewById(R.id.txtMean);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listner.myClick(Edit_CODE, (Words) mean.getTag());
                }
            });

        }
    }

    public Words getWordAtPosition (int position) {
        return modelview.get(position);
    }


}
