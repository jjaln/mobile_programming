package com.jjaln.dailychart.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.jjaln.dailychart.MainActivity;
import com.jjaln.dailychart.R;
import com.jjaln.dailychart.ui.Recycler.CoinList.Coin;
import com.jjaln.dailychart.ui.Recycler.CoinList.Coin_List_Data;
import com.jjaln.dailychart.ui.Recycler.CoinList.Coin_List_RecyclerAdapter;
import com.jjaln.dailychart.ui.Recycler.ExchangeList.Exchange_List_Data;
import com.jjaln.dailychart.ui.Recycler.ExchangeList.Exchange_List_RecyclerAdapter;

import java.util.ArrayList;



public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    // RecyclerView by H
    private RecyclerView mRecyclerView;
    private Exchange_List_RecyclerAdapter mExchangeAdapter;
    private Coin_List_RecyclerAdapter mCoinAdapter;
    private LinearLayoutManager mLayoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //Home_Exchange_List RecyclerView Part Start
        mRecyclerView = (RecyclerView)root.findViewById(R.id.home_exchange_list);
        ArrayList<Exchange_List_Data> ExchangeData = new ArrayList<>();

        ExchangeData.add(new Exchange_List_Data(R.mipmap.bithumb,"com.btckorea.bithumb","Bithumb"));
        ExchangeData.add(new Exchange_List_Data(R.mipmap.upbit,"com.dunamu.exchange","Upbit"));

        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mExchangeAdapter = new Exchange_List_RecyclerAdapter();
        mExchangeAdapter.setData(ExchangeData);
        mRecyclerView.setAdapter(mExchangeAdapter);
        //Home_Exchange_List RecyclerView End


        //Home_Coin_List RecyclerView Start
        mRecyclerView = (RecyclerView)root.findViewById(R.id.home_coin_list);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),1));
        ArrayList<Coin_List_Data> CoinData = new ArrayList<>();
//        ArrayList<Coin> getcoins = ((MainActivity)MainActivity.mainContext).coins;
//        Log.d("BTC",getcoins.get(0).toString());
        CoinData.add(new Coin_List_Data(R.mipmap.btc,"BTC","a","a",
                "a"));
        CoinData.add(new Coin_List_Data(R.mipmap.btc,"BTC","a","a",
                "a"));
        CoinData.add(new Coin_List_Data(R.mipmap.btc,"BTC","a","a",
                "a"));
        CoinData.add(new Coin_List_Data(R.mipmap.btc,"BTC","a","a",
                "a"));


        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mCoinAdapter = new Coin_List_RecyclerAdapter();
        mCoinAdapter.setData(CoinData);
        mRecyclerView.setAdapter(mCoinAdapter);
        //Home_Coin_List RecyclerView Part Start

        //RefreshLayout
        SwipeRefreshLayout refreshLayout = root.findViewById(R.id.home_coin_list_refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
            refreshLayout.setRefreshing(false);
            }
        });

        final TextView textView = root.findViewById(R.id.text_person_data);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    private void GetData()
    {
        Gson gson = new Gson();

    }

}