package com.jjaln.dailychart.ui.Recycler.ExchangeList;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jjaln.dailychart.R;

import java.util.ArrayList;

public class Exchange_List_RecyclerAdapter extends RecyclerView.Adapter<Exchange_List_ViewHolder>
{
    private ArrayList<Exchange_List_Data> Exchange_List;

    public void setData(ArrayList<Exchange_List_Data> list)
    {
        Exchange_List = list;
    }

    @NonNull
    @Override
    public Exchange_List_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_exchange_item,parent,false);

        Exchange_List_ViewHolder holder = new Exchange_List_ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Exchange_List_ViewHolder holder, int position) {
        final Exchange_List_Data data = Exchange_List.get(position);

        holder.icon.setImageResource(data.getImg());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                //Test onClick method
                //Toast.makeText(view.getContext(),data.getText(),Toast.LENGTH_SHORT).show();
                Intent intent = view.getContext().getPackageManager().getLaunchIntentForPackage(data.getText());
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return Exchange_List.size();
    }
}
