package com.jjaln.dailychart.ui.Recycler.BoardList;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.jjaln.dailychart.MainActivity;
import com.jjaln.dailychart.R;
import com.jjaln.dailychart.ui.Contents.CoinInfo;
import com.jjaln.dailychart.ui.dashboard.BoardList;
import com.jjaln.dailychart.ui.dashboard.DashboardFragment;

import java.util.ArrayList;

public class BoardListRecyclerAdapter extends RecyclerView.Adapter<BoardListViewHolder> {

    private ArrayList<BoardListData> Board_List;
    MainActivity mainActivity;

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
    }

//    public interface OnItemClickListener {
//        void onItemClick(View v, int position) ;
//    }
//    // 리스너 객체 참조를 저장하는 변수
//    private OnItemClickListener mListener = null ;
//
//    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        this.mListener = listener ;
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        ViewHolder(View itemView) {
//            super(itemView) ;
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int pos = getAdapterPosition() ;
//                    if (pos != RecyclerView.NO_POSITION) {
//                        // 리스너 객체의 메서드 호출.
//                        if (mListener != null) {
//                            mListener.onItemClick(v, pos) ;
//                        }
//                    }
//                }
//            });
//        }
//    }

    @Override
    public int getItemCount() {
        return Board_List.size();
    }
}
