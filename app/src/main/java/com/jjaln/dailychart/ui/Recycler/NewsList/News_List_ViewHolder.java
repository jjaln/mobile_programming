package com.jjaln.dailychart.ui.Recycler.NewsList;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jjaln.dailychart.R;

import org.w3c.dom.Text;


public class News_List_ViewHolder extends RecyclerView.ViewHolder
{
    public TextView title;

    public News_List_ViewHolder(View itemView)
    {
        super(itemView);
        title = (TextView)itemView.findViewById(R.id.news_title);
    }
}
