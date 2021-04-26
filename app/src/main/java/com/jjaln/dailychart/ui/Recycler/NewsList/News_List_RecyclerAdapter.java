package com.jjaln.dailychart.ui.Recycler.NewsList;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jjaln.dailychart.R;

import java.util.ArrayList;

public class News_List_RecyclerAdapter extends RecyclerView.Adapter<News_List_ViewHolder>
{
    private ArrayList<News_List_Data> News_List;

    public void setData(ArrayList<News_List_Data> list)
    {
        News_List = list;
    }

    @NonNull
    @Override
    public News_List_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_info_news,parent,false);

        News_List_ViewHolder holder = new News_List_ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull News_List_ViewHolder holder, int position) {
        final News_List_Data data = News_List.get(position);

        holder.title.setText(data.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                //Test onClick method
//                Toast.makeText(view.getContext(),data.getUrl(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.getUrl()));
                view.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return News_List.size();
    }
}
