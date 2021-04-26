package com.jjaln.dailychart.ui.Recycler.BoardList;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.jjaln.dailychart.R;
import com.jjaln.dailychart.ui.dashboard.DashboardFragment;

public class BoardListViewHolder extends RecyclerView.ViewHolder {
    public ImageView board_icon;
    public TextView board_name;

    public BoardListViewHolder(View itemView) {
        super(itemView);
        board_icon = (ImageView)itemView.findViewById(R.id.boardIcon);
        board_name = (TextView)itemView.findViewById(R.id.boardName);

        board_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                int pos = getAdapterPosition() ;
                Bundle arg = new Bundle();
                if (pos != RecyclerView.NO_POSITION) {
                    arg.putInt("boardPosition", pos);
                }


                AppCompatActivity activity = (AppCompatActivity)view.getContext();
                Fragment myFragment = new DashboardFragment();
                myFragment.setArguments(arg);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.action_container, myFragment).addToBackStack(null).commit();


            }
        });
    }

}