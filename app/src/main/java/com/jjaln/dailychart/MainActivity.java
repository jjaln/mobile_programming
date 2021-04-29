package com.jjaln.dailychart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.auth0.android.jwt.JWT;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.*;
import com.jjaln.dailychart.ui.Recycler.CoinList.Coin;
import com.jjaln.dailychart.ui.Recycler.CoinList.Coin_List_Data;
import com.jjaln.dailychart.ui.Recycler.CoinList.Coin_List_RecyclerAdapter;
import com.jjaln.dailychart.ui.dashboard.BoardList;
import com.jjaln.dailychart.ui.dashboard.DashboardFragment;
import com.jjaln.dailychart.ui.wallet.Api_Client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    @Override 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetworkThread thread = new NetworkThread();

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


        thread.start();


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
                String xcoin_last_btc = data_list.getString("xcoin_last_btc");
                String xcoin_last_eth = data_list.getString("xcoin_last_eth");
                String xcoin_last_xrp = data_list.getString("xcoin_last_xrp");
                String xcoin_last_dot = data_list.getString("xcoin_last_dot");
                String xcoin_last_ada = data_list.getString("xcoin_last_ada");

                //double total_bithumb = Integer.parseInt(total_krw) +

                float balance = Float.parseFloat(total_krw) + (Float.parseFloat(xcoin_last_btc) * Float.parseFloat(total_btc)) +
                        (Float.parseFloat(xcoin_last_eth) * Float.parseFloat(total_eth)) +
                        (Float.parseFloat(xcoin_last_xrp) * Float.parseFloat(total_xrp)) +
                        (Float.parseFloat(xcoin_last_ada) * Float.parseFloat(total_ada)) +
                        (Float.parseFloat(xcoin_last_dot) * Float.parseFloat(total_dot));
                final String str2 = "Bithumb : " + balance;

                final String result2 = api.callApi("/public/ticker/BTC/KRW", rgParams);
                JSONObject obj2 = new JSONObject(result2);
                JSONObject data_list2 = obj2.getJSONObject("data");

                String opening_price_btc = data_list2.getString("opening_price");
                String closing_price_btc = data_list2.getString("closing_price");
                String min_price_btc = data_list2.getString("min_price");
                String max_price_btc = data_list2.getString("max_price");
                String units_traded_btc = data_list2.getString("units_traded");
                String acc_trade_value_btc = data_list2.getString("acc_trade_value");
                String prev_closing_price_btc = data_list2.getString("prev_closing_price");
                String units_traded_24H_btc = data_list2.getString("units_traded_24H");
                String acc_trade_value_24H_btc = data_list2.getString("acc_trade_value_24H");
                String fluctate_24H_btc = data_list2.getString("fluctate_24H");
                String fluctate_rate_24H_btc = data_list2.getString("fluctate_rate_24H");
                String date_btc = data_list2.getString("date");

                final String result3 = api.callApi("/public/ticker/ETH/KRW", rgParams);
                JSONObject obj3 = new JSONObject(result3);
                JSONObject data_list3 = obj3.getJSONObject("data");

                String opening_price_eth = data_list3.getString("opening_price");
                String closing_price_eth = data_list3.getString("closing_price");
                String min_price_eth = data_list3.getString("min_price");
                String max_price_eth = data_list3.getString("max_price");
                String units_traded_eth = data_list3.getString("units_traded");
                String acc_trade_value_eth = data_list3.getString("acc_trade_value");
                String prev_closing_price_eth = data_list3.getString("prev_closing_price");
                String units_traded_24H_eth = data_list3.getString("units_traded_24H");
                String acc_trade_value_24H_eth = data_list3.getString("acc_trade_value_24H");
                String fluctate_24H_eth = data_list3.getString("fluctate_24H");
                String fluctate_rate_24H_eth = data_list3.getString("fluctate_rate_24H");
                String date_eth = data_list3.getString("date");

                final String result4 = api.callApi("/public/ticker/XRP/KRW", rgParams);
                JSONObject obj4 = new JSONObject(result4);
                JSONObject data_list4 = obj4.getJSONObject("data");

                String opening_price_xrp = data_list4.getString("opening_price");
                String closing_price_xrp = data_list4.getString("closing_price");
                String min_price_xrp = data_list4.getString("min_price");
                String max_price_xrp = data_list4.getString("max_price");
                String units_traded_xrp = data_list4.getString("units_traded");
                String acc_trade_value_xrp = data_list4.getString("acc_trade_value");
                String prev_closing_price_xrp = data_list4.getString("prev_closing_price");
                String units_traded_24H_xrp = data_list4.getString("units_traded_24H");
                String acc_trade_value_24H_xrp = data_list4.getString("acc_trade_value_24H");
                String fluctate_24H_xrp = data_list4.getString("fluctate_24H");
                String fluctate_rate_24H_xrp = data_list4.getString("fluctate_rate_24H");
                String date_xrp = data_list4.getString("date");

                final String result5 = api.callApi("/public/ticker/ADA/KRW", rgParams);
                JSONObject obj5 = new JSONObject(result2);
                JSONObject data_list5 = obj5.getJSONObject("data");

                String opening_price_ada = data_list5.getString("opening_price");
                String closing_price_ada = data_list5.getString("closing_price");
                String min_price_ada = data_list5.getString("min_price");
                String max_price_ada = data_list5.getString("max_price");
                String units_traded_ada = data_list5.getString("units_traded");
                String acc_trade_value_ada = data_list5.getString("acc_trade_value");
                String prev_closing_price_ada = data_list5.getString("prev_closing_price");
                String units_traded_24H_ada = data_list5.getString("units_traded_24H");
                String acc_trade_value_24H_ada = data_list5.getString("acc_trade_value_24H");
                String fluctate_24H_ada = data_list5.getString("fluctate_24H");
                String fluctate_rate_24H_ada = data_list5.getString("fluctate_rate_24H");
                String date_ada = data_list5.getString("date");

                final String result6 = api.callApi("/public/ticker/DOT/KRW", rgParams);
                JSONObject obj6 = new JSONObject(result6);
                JSONObject data_list6 = obj6.getJSONObject("data");

                String opening_price_dot = data_list6.getString("opening_price");
                String closing_price_dot = data_list6.getString("closing_price");
                String min_price_dot = data_list6.getString("min_price");
                String max_price_dot = data_list6.getString("max_price");
                String units_traded_dot = data_list6.getString("units_traded");
                String acc_trade_value_dot = data_list6.getString("acc_trade_value");
                String prev_closing_price_dot = data_list6.getString("prev_closing_price");
                String units_traded_24H_dot = data_list6.getString("units_traded_24H");
                String acc_trade_value_24H_dot = data_list6.getString("acc_trade_value_24H");
                String fluctate_24H_dot = data_list6.getString("fluctate_24H");
                String fluctate_rate_24H_dot = data_list6.getString("fluctate_rate_24H");
                String date_dot = data_list6.getString("date");








                TextView text = (TextView)findViewById(R.id.text_person_data);
                Log.d(this.getClass().getName(), (String)text.getText());
                text.setText(str2);




            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*class NetworkThread2 extends Thread {

        public void run() {


            try {

                String accessKey = ("bQkwgecfgKUUItt0ZK1enCugmtp5sBPjx8EF4AVM");
                String secretKey = ("cV0QumxDAWVmJSw5UFpMnQOFQIpskGMisfXsQmSd");
                String serverUrl = ("https://api.upbit.com");

                
                Algorithm algorithm = Algorithm.HMAC256(secretKey);
                String jwtToken = JWT.create()
                        .withClaim("access_key", accessKey)
                        .withClaim("nonce", UUID.randomUUID().toString())
                        .sign(algorithm);

                String authenticationToken = "Bearer " + jwtToken;
                HttpClient client = HttpClientBuilder.create().build();
                HttpGet request = new HttpGet(serverUrl + "/v1/accounts");
                request.setHeader("Content-Type", "application/json");
                request.addHeader("Authorization", authenticationToken);

                HttpResponse response = client.execute(request);
                HttpEntity entity = response.getEntity();


                System.out.println(EntityUtils.toString(entity, "UTF-8"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }*/
}