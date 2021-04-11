package com.jjaln.dailychart.ui.Contents;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.jjaln.dailychart.R;

import org.codehaus.jackson.smile.Tool;
import org.w3c.dom.Text;

public class CoinInfo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_info);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





        Intent intent = getIntent();
        final String coin_name = intent.getExtras().getString("coin_name");
        final int coin_img = intent.getExtras().getInt("coin_img");

        ImageView imageView = (ImageView)findViewById(R.id.Coinfo_img);
        TextView textView = (TextView)findViewById(R.id.Coinfo_name);

        imageView.setImageResource(coin_img);
        textView.setText(coin_name);
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