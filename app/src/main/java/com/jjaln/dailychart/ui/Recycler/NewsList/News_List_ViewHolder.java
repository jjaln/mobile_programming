package com.jjaln.dailychart.ui.Recycler.NewsList;


import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jjaln.dailychart.R;



public class News_List_ViewHolder extends RecyclerView.ViewHolder
{
    public TextView title;
    public TextView desc;

    public News_List_ViewHolder(View itemView)
    {
        super(itemView);
        title = (TextView)itemView.findViewById(R.id.news_title);
        desc = (TextView)itemView.findViewById(R.id.news_desc);
    }
}
