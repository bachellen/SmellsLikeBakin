package com.example.smellslikebakin;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends RecyclerView.Adapter {
    private final ListFragment.OnRecipeSelectedInterface mListener;

    public ListAdapter(ListFragment.OnRecipeSelectedInterface listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ((ListViewHolder) viewHolder ).bindView(i);
    }

    @Override
    public int getItemCount() {
        return Recipes.names.length;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mtextView;
        private ImageView mImageView;
        private int mIndex;

        ListViewHolder(View itemView){
            super(itemView);
            mtextView = itemView.findViewById(R.id.ItemText);
            mImageView = itemView.findViewById(R.id.itemImage);
            itemView.setOnClickListener(this);
        }

        public void bindView(int position ){
            mIndex = position;
            mtextView.setText(Recipes.names[position]);
            mImageView.setImageResource(Recipes.resourceIds[position]);
        }
        @Override
        public void onClick(View v) {

            mListener.OnListRecipeSelected(mIndex);
        }
    }

}

