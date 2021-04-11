package com.jjaln.dailychart.ui.Recycler.CoinList;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jjaln.dailychart.R;


public class Coin_List_ViewHolder extends RecyclerView.ViewHolder
{
    public ImageView coin_img;
    public TextView coin_name;
    public TextView market_price;
    public TextView fluctate;

    public Coin_List_ViewHolder(View itemView)
    {
        super(itemView);
        coin_img = (ImageView)itemView.findViewById(R.id.coin_image);
        coin_name = (TextView)itemView.findViewById(R.id.coin_name);
        market_price = (TextView)itemView.findViewById(R.id.market_price);
    }
}
