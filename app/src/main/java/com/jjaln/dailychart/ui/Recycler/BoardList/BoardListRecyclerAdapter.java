package com.jjaln.dailychart.ui.Recycler.BoardList;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jjaln.dailychart.R;
import com.jjaln.dailychart.ui.dashboard.DashboardFragment;

import java.util.ArrayList;

public class BoardListRecyclerAdapter extends RecyclerView.Adapter<BoardListViewHolder>
{
    private ArrayList<BoardListData> Board_List;

    public void setData(ArrayList<BoardListData> list)
    {
        Board_List = list;
    }

    @NonNull
    @Override
    public BoardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_board, parent,false);

        BoardListViewHolder holder = new BoardListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BoardListViewHolder holder, int position) {
        final BoardListData data = Board_List.get(position);

        holder.board_icon.setImageResource(data.getImg());
        holder.board_name.setText(data.getText());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
//                DashboardFragment nextFrag= new DashboardFragment();
//                view.getContext().getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.Layout_container, nextFrag, "findThisFragment")
//                        .addToBackStack(null)
//                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return Board_List.size();
    }
}
