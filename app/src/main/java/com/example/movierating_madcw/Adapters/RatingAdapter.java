package com.example.movierating_madcw.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movierating_madcw.R;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.RatingHolder> {
    Context context;
    int lastPosition;

    public RatingAdapter(Context context, int lastPosition) {
        this.context = context;
        this.lastPosition = lastPosition;
    }

    @NonNull
    @Override
    public RatingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RatingHolder(LayoutInflater.from(context).inflate(R.layout.row_rating,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RatingHolder holder, int position) {
if(position<lastPosition){
    holder.star.setColorFilter(context.getResources().getColor(R.color.yellow));
}else{

}
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class RatingHolder extends RecyclerView.ViewHolder {
        ImageView star;
        public RatingHolder(@NonNull View itemView) {
            super(itemView);
            star=itemView.findViewById(R.id.star);
        }
    }
}
