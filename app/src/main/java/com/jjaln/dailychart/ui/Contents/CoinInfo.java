package com.jjaln.dailychart.ui.Contents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.Document;
import com.jjaln.dailychart.R;
import com.jjaln.dailychart.ui.Recycler.CoinList.Coin_List_RecyclerAdapter;
import com.jjaln.dailychart.ui.Recycler.NewsList.News_List_Data;
import com.jjaln.dailychart.ui.Recycler.NewsList.News_List_RecyclerAdapter;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Map;


public class CoinInfo extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private News_List_RecyclerAdapter mNewsAdapter;
    private LinearLayoutManager layoutManager;
    private ArrayList<News_List_Data> newsListData;
    private FirebaseFirestore database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_info);

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
        newsListData = new ArrayList<>();

        CollectionReference Ref = db.collection(coin_name);
        Ref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Map<String, Object> data = document.getData();
                    String title = data.get("title").toString();
                    String url = data.get("link").toString();
                    Log.d("To_String","title : " +title + "  URL : "+url);
                    newsListData.add(new News_List_Data(title, url));
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