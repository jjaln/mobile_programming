package com.jjaln.dailychart.ui.Recycler.CoinList;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
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

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Coin_List_RecyclerAdapter extends RecyclerView.Adapter<Coin_List_ViewHolder> {

    DecimalFormat in = new DecimalFormat("###,###");
    DecimalFormat dot1 = new DecimalFormat("###.#");
    DecimalFormat dot2 = new DecimalFormat("###.##");
    private ArrayList<Coin_List_Data> Coin_List;

    public void setData(ArrayList<Coin_List_Data> list) {
        Coin_List = list;
    }

    @NonNull
    @Override
    public Coin_List_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_coinlist, parent, false);

        Coin_List_ViewHolder holder = new Coin_List_ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Coin_List_ViewHolder holder, int position) {
        final Coin_List_Data data = Coin_List.get(position);
        Coin update = MainActivity.coins.get(position);
        Log.d("AAAAAAAAAAAAAAAAAAAAA", update.getClosing_price());
        holder.coin_img.setImageResource(data.getImg());
        holder.coin_name.setText(data.getText());

        holder.fluctate_rate.setText(getFormat(update.getFluctate_rate_24H()));
        holder.fluctate_price.setText(getFormat(update.getFluctate_24H()));
        holder.market_price.setText(getFormat(update.getClosing_price()));

        if (update.getFluctate_rate_24H().charAt(0) == '-') {
            holder.market_price.setTextColor(Color.BLUE);
            holder.fluctate_price.setTextColor(Color.BLUE);
            holder.fluctate_rate.setTextColor(Color.BLUE);
        } else {
            if (Float.valueOf(update.getFluctate_24H()) > 0) {
                holder.market_price.setTextColor(Color.RED);
                holder.fluctate_price.setTextColor(Color.RED);
                holder.fluctate_rate.setTextColor(Color.RED);
            } else {
                holder.market_price.setTextColor(Color.WHITE);
                holder.fluctate_price.setTextColor(Color.WHITE);
                holder.fluctate_rate.setTextColor(Color.WHITE);
            }

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Test onClick method
                //Toast.makeText(view.getContext(),data.getText(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), CoinInfo.class);
                intent.putExtra("coin_name", data.getText());
                intent.putExtra("coin_img", data.getImg());
                view.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return Coin_List.size();
    }

    public String getFormat(String price) {
        String pm = "";
        if (price.charAt(0) == '-') {
            pm = "-";
            price = price.substring(1);
        }

        Float temp_price = Float.valueOf(price);
        String res;
        if (temp_price >= 100)
            res = in.format(temp_price);
        else if (temp_price < 100 && temp_price >= 10)
            res = dot1.format(temp_price);
        else
            res = dot2.format(temp_price);
        return pm+res;
    }
}
