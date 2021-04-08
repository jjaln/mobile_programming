package com.jjaln.dailychart.ui.Recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jjaln.dailychart.R;

import java.util.ArrayList;

public class Coin_List_RecyclerAdapter extends RecyclerView.Adapter<Coin_List_ViewHolder>
{
    private ArrayList<Coin_List_Data> CoinListDatas;

    public void setData(ArrayList<Coin_List_Data> list)
    {
        CoinListDatas = list;
    }

    @NonNull
    @Override
    public Coin_List_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_coin_item,parent,false);

        Coin_List_ViewHolder holder = new Coin_List_ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Coin_List_ViewHolder holder, int position) {
        Coin_List_Data data = CoinListDatas.get(position);

        holder.des.setText(data.getText());
        holder.icon.setImageResource(data.getImg());
    }

    @Override
    public int getItemCount() {
        return CoinListDatas.size();
    }
}
