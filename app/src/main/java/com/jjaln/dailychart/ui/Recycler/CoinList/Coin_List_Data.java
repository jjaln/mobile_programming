package com.jjaln.dailychart.ui.Recycler.CoinList;

public class Coin_List_Data {

    private int coin_img;
    private String coin_name;
    private String market_price;

    public Coin_List_Data(int img, String text,String price)
    {
        this.coin_img = img;
        this.coin_name = text;
        this.market_price = price;
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
}
