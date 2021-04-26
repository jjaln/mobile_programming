package com.jjaln.dailychart.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jjaln.dailychart.R;
import com.jjaln.dailychart.ui.Recycler.BoardList.BoardListData;
import com.jjaln.dailychart.ui.Recycler.BoardList.BoardListRecyclerAdapter;
import com.jjaln.dailychart.ui.Recycler.CoinList.Coin_List_Data;
import com.jjaln.dailychart.ui.Recycler.CoinList.Coin_List_RecyclerAdapter;
import com.jjaln.dailychart.ui.notifications.NotificationsViewModel;

import java.util.ArrayList;

public class BoardList extends Fragment {
    private RecyclerView mRecyclerView;
    private BoardListRecyclerAdapter mBoardAdapter;
    private LinearLayoutManager mLayoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.board_list, container, false);


        mRecyclerView = (RecyclerView)root.findViewById(R.id.board_list_recycler_view);
        ArrayList<BoardListData> BoardData = new ArrayList<>();

        BoardData.add(new BoardListData(R.mipmap.btc,"비트코인"));
        BoardData.add(new BoardListData(R.mipmap.eth,"이더리움"));
        BoardData.add(new BoardListData(R.mipmap.xrp,"리플"));
        BoardData.add(new BoardListData(R.mipmap.ada,"에이다"));
        BoardData.add(new BoardListData(R.mipmap.dot,"폴카닷"));

        mLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mBoardAdapter = new BoardListRecyclerAdapter();
        mBoardAdapter.setData(BoardData);
//        mBoardAdapter.setOnItemClickListener(new BoardListRecyclerAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View v, int position) {
//                Log.d("BoardList", "Clicked");
//            }
//        });
        mRecyclerView.setAdapter(mBoardAdapter);


        return root;
    }
}
