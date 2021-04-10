package com.jjaln.dailychart.ui.Recycler.CoinList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jjaln.dailychart.R;

import java.util.ArrayList;

public class Coin_List_RecyclerAdapter extends RecyclerView.Adapter<Coin_List_ViewHolder>
{
    private ArrayList<Coin_List_Data> Coin_List;

    public void setData(ArrayList<Coin_List_Data> list)
    {
        Coin_List = list;
    }

    @NonNull
    @Override
    public Coin_List_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_coin_item,parent,false);

        Coin_List_ViewHolder holder = new Coin_List_ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Coin_List_ViewHolder holder, int position) {
        final Coin_List_Data data = Coin_List.get(position);

        holder.coin_img.setImageResource(data.getImg());
        holder.coin_name.setText(data.getText());
        holder.market_price.setText(data.getMarket_price());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                //Test onClick method
                //Toast.makeText(view.getContext(),data.getText(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return Coin_List.size();
    }
}
