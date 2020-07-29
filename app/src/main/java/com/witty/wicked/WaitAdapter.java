package com.witty.wicked;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.witty.wicked.Utils.MyAdapter;

class WaitAdapter extends RecyclerView.Adapter<WaitAdapter.MyViewHolder> {
    private String[] mDataset;
    private final WickedActivity wickedActivity;

    public WaitAdapter(String[] myDataset, WickedActivity wickedActivity) {
        mDataset = myDataset;
        this.wickedActivity = wickedActivity;
    }

    @NonNull
    @Override
    public WaitAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.name_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WaitAdapter.MyViewHolder holder, int position) {
        Log.d("jaya",mDataset[position]);
        holder.name.setText(mDataset[position]);
        holder.mPosition = position;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView name;
        public ImageView imageView;
        public int mPosition;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name_text);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
        }
    }
}
