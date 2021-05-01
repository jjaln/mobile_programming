package com.jjaln.dailychart.ui.Recycler.CoinList;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.jjaln.dailychart.MainActivity;
import com.jjaln.dailychart.R;
import com.jjaln.dailychart.ui.Contents.CoinInfo;

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
                .inflate(R.layout.item_home_coinlist,parent,false);

        Coin_List_ViewHolder holder = new Coin_List_ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull Coin_List_ViewHolder holder, int position) {
        final Coin_List_Data data = Coin_List.get(position);
        holder.coin_img.setImageResource(data.getImg());
        holder.coin_name.setText(data.getText());
        holder.fluctate_rate.setText(data.getFlucate_rate());
        holder.fluctate_price.setText(data.getFlucate_price());
        holder.market_price.setText(data.getMarket_price());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                //Test onClick method
                //Toast.makeText(view.getContext(),data.getText(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), CoinInfo.class);
                intent.putExtra("coin_name",data.getText());
                intent.putExtra("coin_img",data.getImg());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return Coin_List.size();
    }
}
