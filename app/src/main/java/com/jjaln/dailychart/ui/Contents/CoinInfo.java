package com.jjaln.dailychart.ui.Contents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jjaln.dailychart.R;
import com.jjaln.dailychart.ui.Recycler.NewsList.News_List_Data;
import com.jjaln.dailychart.ui.Recycler.NewsList.News_List_RecyclerAdapter;

import java.util.ArrayList;
import java.util.Map;

import com.jjaln.dailychart.ui.wallet.Api_Client;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;



public class CoinInfo extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private News_List_RecyclerAdapter mNewsAdapter;
    private LinearLayoutManager layoutManager;
    private ArrayList<News_List_Data> newsListData;
    private FirebaseFirestore database;

    List<Integer> price_list = new ArrayList<Integer>();
    public GraphView graph;
    DataPoint[] pricePoints;
    LineGraphSeries series;
    int cnt = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_info);

        graph=(GraphView)findViewById(R.id.chart);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        final String coin_name = intent.getExtras().getString("coin_name");
        final int coin_img = intent.getExtras().getInt("coin_img");

        ImageView imageView = (ImageView) findViewById(R.id.Coinfo_img);
        TextView textView = (TextView) findViewById(R.id.Coinfo_name);

        imageView.setImageResource(coin_img);
        textView.setText(coin_name);

        FirebaseFirestore db = database.getInstance();

        mRecyclerView = (RecyclerView)findViewById(R.id.News_Recycler);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), 1));
        newsListData = new ArrayList<>();

        CollectionReference Ref = db.collection(coin_name);
        Ref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Map<String, Object> data = document.getData();
                    String title = data.get("title").toString();
                    String url = data.get("link").toString();
                    String desc = data.get("desc").toString();
                    newsListData.add(new News_List_Data(title,desc, url));
                }
                mNewsAdapter.notifyDataSetChanged();
            }
        });

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(layoutManager);
        mNewsAdapter = new News_List_RecyclerAdapter();
        mNewsAdapter.setData(newsListData);
        mRecyclerView.setAdapter(mNewsAdapter);

        NetworkThread thread = new NetworkThread();
        thread.start();

    }

    public synchronized void drawLineGraph()throws Exception{
        pricePoints=new DataPoint[price_list.size()];

        for (int i = 0; i < pricePoints.length; i++) {
            pricePoints[i] = new DataPoint(i, price_list.get(i));
        }
        series=new LineGraphSeries<>(pricePoints);
        // ???????????? ????????? ?????? ?????????
        graph.addSeries(series);
        // x?????? ?????? ????????? true
        graph.getViewport().setXAxisBoundsManual(true);
        // ???????????? ???????????? x?????? scroll ??? ???????????? ??????
        graph.getViewport().setScrollable(true);
        // ???????????? ???????????? y?????? scroll ??? ??????????????? ??????
        graph.getViewport().setScrollableY(true);

    }
    public void addEntry(int x){
        series.appendData(new DataPoint(cnt++ ,x),true,10);
    }

    class NetworkThread extends Thread{
        @Override
        public void run() {
            Api_Client api = new Api_Client("a10c1f984334fb14c30ebaf3e60ce998",
                    "32fe2aae6de50ec84b0ed4cf6093a95b");
            HashMap<String, String> rgParams = new HashMap<String, String>();
            rgParams = new HashMap<String, String>();
            int isFirst = 1;
            Intent intent = getIntent();
            final String coin_name = intent.getExtras().getString("coin_name");
            while(true) {
                if (isFirst == 1) {
                    try {
                        rgParams.put("count", "20");

                        //bithumb ????????? ?????? ?????? ?????? ?????? ????????????
                        String result = api.callApi("/public/transaction_history/"+coin_name+"/KRW", rgParams);
                        JSONObject obj = new JSONObject(result);
                        String status = obj.getString("status");
                        JSONArray data_list = obj.getJSONArray("data");
                        for (int i = 0; i < data_list.length(); i++) {
                            JSONObject data_list_obj = data_list.getJSONObject(i);
                            String transaction_date = data_list_obj.getString("transaction_date");
                            final int price = Integer.parseInt(data_list_obj.getString("price"));
                            price_list.add(price);


                        }
                        drawLineGraph();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    isFirst = 0;
                }
                else {
                    try {
                        rgParams.put("count", "1");
                        String result = api.callApi("/public/transaction_history/"+coin_name+"/KRW", rgParams);
                        JSONObject obj = new JSONObject(result);
                        String status = obj.getString("status");
                        JSONArray data_list = obj.getJSONArray("data");
                        for (int i = 0; i < data_list.length(); i++) {
                            JSONObject data_list_obj = data_list.getJSONObject(i);
                            String transaction_date = data_list_obj.getString("transaction_date");
                            final int price = Integer.parseInt(data_list_obj.getString("price"));

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    addEntry(price);
                                }
                            });
                            sleep(1000);
                        }

                    } catch (Exception e) {
//                        e.printStackTrace();
                    }
                }

            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}