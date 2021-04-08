package com.jjaln.dailychart.ui.Recycler;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jjaln.dailychart.R;



public class Coin_List_ViewHolder extends RecyclerView.ViewHolder
{
    public ImageView icon;
    public TextView des;

    public Coin_List_ViewHolder(View itemView)
    {
        super(itemView);

        icon = (ImageView)itemView.findViewById(R.id.coin_image);
        des = (TextView)itemView.findViewById(R.id.coin_name);
    }
}
