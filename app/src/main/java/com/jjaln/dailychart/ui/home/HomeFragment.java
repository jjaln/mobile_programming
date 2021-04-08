package com.jjaln.dailychart.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jjaln.dailychart.R;
import com.jjaln.dailychart.ui.Recycler.Coin_List_Data;
import com.jjaln.dailychart.ui.Recycler.Coin_List_RecyclerAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    // RecyclerView by H
    private RecyclerView mRecyclerView;
    private Coin_List_RecyclerAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private int Max_Coin_List = 5;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //RecyclerView Part
        mRecyclerView = (RecyclerView)root.findViewById(R.id.main_coin_list);
        ArrayList<Coin_List_Data> data = new ArrayList<>();

        data.add(new Coin_List_Data(R.mipmap.btc,"BTC"));
        data.add(new Coin_List_Data(R.mipmap.eth,"ETH"));
        data.add(new Coin_List_Data(R.mipmap.xrp,"XRP"));
        data.add(new Coin_List_Data(R.mipmap.ada,"ADA"));
        data.add(new Coin_List_Data(R.mipmap.dot,"DOT"));

        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new Coin_List_RecyclerAdapter();
        mAdapter.setData(data);
        mRecyclerView.setAdapter(mAdapter);

        final TextView textView = root.findViewById(R.id.text_person_data);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}