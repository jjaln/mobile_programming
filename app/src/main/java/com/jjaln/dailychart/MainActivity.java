package com.jjaln.dailychart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.*;
import com.jjaln.dailychart.ui.dashboard.BoardList;
import com.jjaln.dailychart.ui.dashboard.DashboardFragment;
import com.jjaln.dailychart.ui.wallet.Api_Client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetworkThread thread = new NetworkThread();
        thread.start();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(
                this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbar_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings: // 메뉴 아이콘 선택시 이벤트 설정
                Intent settingIntent = new Intent(getApplicationContext(), Setting.class);
                startActivity(settingIntent);
                return true;
            case R.id.action_search: // 메뉴 아이콘 선택시 이벤트 설정
                Intent searchIntent = new Intent(getApplicationContext(), Search.class);
                startActivity(searchIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    class NetworkThread extends Thread {
        @Override
        public void run() {
            try {
                Api_Client api = new Api_Client("a10c1f984334fb14c30ebaf3e60ce998",
                        "32fe2aae6de50ec84b0ed4cf6093a95b");
                HashMap<String, String> rgParams = new HashMap<String, String>();
                rgParams.put("currency", "ALL");
                rgParams.put("payment_currency", "KRW");

                // API 를 이용하여 info-balance 의 결과값을 JSON 타입으로 가져오기
                final String result = api.callApi("/info/balance", rgParams);
                // JSONObject 객체에 담는다.
                JSONObject obj = new JSONObject(result);
                String status = obj.getString("status");
                // 'data' 객체는 Object
                JSONObject data_list = obj.getJSONObject("data");
                String total_krw = data_list.getString("total_krw");
                String in_use_krw = data_list.getString("in_use_krw");
                String available_krw = data_list.getString("available_krw");
                String total_btc = data_list.getString("total_btc");
                String in_use_btc = data_list.getString("in_use_btc");
                String available_btc = data_list.getString("available_btc");
                String total_eth = data_list.getString("total_eth");
                String in_use_eth = data_list.getString("in_use_eth");
                String available_eth = data_list.getString("available_eth");
                String total_xrp = data_list.getString("total_xrp");
                String in_use_xrp = data_list.getString("in_use_xrp");
                String available_xrp = data_list.getString("available_xrp");
                String total_dot = data_list.getString("total_dot");
                String in_use_dot = data_list.getString("in_use_dot");
                String available_dot = data_list.getString("available_dot");
                String total_ada = data_list.getString("total_ada");
                String in_use_ada = data_list.getString("in_use_ada");
                String available_ada = data_list.getString("available_ada");

                //double total_bithumb = Integer.parseInt(total_krw) +


                final String str2 = "Bithumb : " + total_krw;




                TextView text = (TextView)findViewById(R.id.text_person_data);
                Log.d(this.getClass().getName(), (String)text.getText());
                text.setText(str2);



                //MainActivity activity = (MainActivity) getActivity();
                /*activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        text.setText(str2);

                    }
                });*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}