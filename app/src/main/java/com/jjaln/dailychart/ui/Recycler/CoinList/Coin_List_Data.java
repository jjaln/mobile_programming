package com.jjaln.dailychart.ui.Recycler.CoinList;

import java.text.DecimalFormat;

public class Coin_List_Data {

    private int coin_img;
    private String coin_name;
    private String market_price;
    private String flucate_rate;
    private String flucate_price;
    DecimalFormat in = new DecimalFormat("###,###");
    DecimalFormat dot1 = new DecimalFormat("###.#");
    DecimalFormat dot2 = new DecimalFormat("###.##");

    public Coin_List_Data(int img, String text,String price,String flucate_price,String flucate_rate)
    {
//        if (Float.valueOf(price) >= 100)
//            price = in.format(price);
//        else if (Float.valueOf(price) <100 && Float.valueOf(price) >= 10)
//            price = dot1.format(price);
//        else
//            price = dot2.format(price);

        this.coin_img = img;
        this.coin_name = text;
        this.market_price = price;
        this.flucate_price = flucate_price;
        this.flucate_rate = flucate_rate;
    }

    public String getText()
    {
        return this.coin_name;
    }
    public int getImg()
    {
        return this.coin_img;
    }
    public String getMarket_price() {return this.market_price;}
    public String getFlucate_rate(){ return this.flucate_rate;}
    public String getFlucate_price(){ return this.flucate_price;}
}
