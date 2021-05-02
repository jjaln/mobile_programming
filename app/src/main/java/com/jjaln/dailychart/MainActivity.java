package com.jjaln.dailychart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jjaln.dailychart.ui.Recycler.CoinList.Coin;
import com.jjaln.dailychart.ui.Recycler.CoinList.Coin_List_Data;
import com.jjaln.dailychart.ui.Recycler.CoinList.Coin_List_RecyclerAdapter;
import com.jjaln.dailychart.ui.Recycler.ExchangeList.Exchange_List_RecyclerAdapter;
import com.jjaln.dailychart.ui.home.HomeFragment;
import com.jjaln.dailychart.ui.wallet.Api_Client;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Coin> coins = SplashActivity.coins;
    public static ArrayList<Coin> prev;
    private RecyclerView mRecyclerView;
    private Exchange_List_RecyclerAdapter mExchangeAdapter;
    private Coin_List_RecyclerAdapter mCoinAdapter;
    private LinearLayoutManager mLayoutManager;
    Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetworkThread thread = new NetworkThread();
        Log.d("//////////", "Start");
        Log.d("//////////", "END");
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

        handler = new Handler();
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
            while (true) {
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

                    String[] coin_list = {"BTC", "ETH", "XRP", "ADA", "DOT"};
//                    coins = new ArrayList<>();
                    for (String coin : coin_list) {
                        final String res = api.callApi("/public/ticker/" + coin + "/KRW", rgParams);
                        JSONObject object = new JSONObject(res);
                        JSONObject dt_list = object.getJSONObject("data");

                        String acc_trade_value = dt_list.getString("acc_trade_value");
                        String acc_trade_value_24H = dt_list.getString("acc_trade_value_24H");
                        String closing_price = dt_list.getString("closing_price");
                        String fluctate_24H = dt_list.getString("fluctate_24H");
                        String fluctate_rate_24H = dt_list.getString("fluctate_rate_24H");
                        String max_price = dt_list.getString("max_price");
                        String min_price = dt_list.getString("min_price");
                        String opening_price = dt_list.getString("opening_price");
                        String prev_closing_price = dt_list.getString("prev_closing_price");
                        String units_traded = dt_list.getString("units_traded");
                        String units_traded_24H = dt_list.getString("units_traded_24H");


                        coins.get(Arrays.asList(coin_list).indexOf(coin)).update(acc_trade_value,acc_trade_value_24H,
                                closing_price,fluctate_24H,fluctate_rate_24H,max_price,min_price,opening_price,
                                prev_closing_price,units_traded,units_traded_24H);
                        Log.d(Arrays.asList(coin_list).indexOf(coin)+"aaaaaaaaaa",coins.get(Arrays.asList(coin_list).indexOf(coin)).getClosing_price());
                    }

                    TextView text = (TextView) findViewById(R.id.text_person_data);
                    text.setText(str2);
                } catch (Exception e) {
//                e.printStackTrace();
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//            handler.post(this::run);
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