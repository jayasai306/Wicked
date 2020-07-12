package com.witty.wicked.Utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.witty.wicked.R;
import com.witty.wicked.WickedActivity;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private final WickedActivity wickedActivity;
    private String[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView answerText;
        public int mPosition;
        public MyViewHolder(View itemView) {
            super(itemView);
            answerText = (TextView) itemView.findViewById(R.id.answer_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    wickedActivity.selectedAnswer(answerText, mPosition);
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(String[] myDataset, WickedActivity wickedActivity) {
        mDataset = myDataset;
        this.wickedActivity = wickedActivity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.answer_card, parent, false);
        return new MyViewHolder(itemView);
    }


    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d("jaya",mDataset[position]);
        holder.answerText.setText(mDataset[position]);
        holder.mPosition = position;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
