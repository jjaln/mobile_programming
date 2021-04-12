package com.jjaln.dailychart.ui.Recycler.BoardList;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jjaln.dailychart.R;
import com.jjaln.dailychart.ui.dashboard.Post;

public class BoardListViewHolder extends RecyclerView.ViewHolder
{
    public ImageView board_icon;
    public TextView board_name;

    public BoardListViewHolder(View itemView)
    {
        super(itemView);
        board_icon = (ImageView)itemView.findViewById(R.id.boardIcon);
        board_name = (TextView)itemView.findViewById(R.id.boardName);
    }

}
