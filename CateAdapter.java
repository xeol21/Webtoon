package com.example.webtoon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CateAdapter extends RecyclerView.Adapter<CateAdapter.viewHolder> {

    Context context;
    ArrayList<CateModel>list;

    public CateAdapter(Context context, ArrayList<CateModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_cate,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        CateModel model=list.get(position);
        holder.cateName.setText(model.getCateName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        TextView cateName;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            cateName=itemView.findViewById(R.id.chapterName);
        }
    }
}
