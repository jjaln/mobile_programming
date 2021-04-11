package com.jjaln.dailychart.ui.Recycler.CoinList;

public class Coin {
    public String key;
    public String acc_trade_value;
    public String acc_trade_value_24H;
    public String closing_price;
    public String current_price;
    public String fluctate_24H;
    public String fluctate_rate_24H;
    public String max_price;
    public String min_price;
    public String opening_price;
    public String prev_closing_price;
    public String units_traded;
    public String units_traded_24H;

    public Coin(){

    }

    public Coin(String acc_trade_value,String acc_trade_value_24H,
                String closing_price,String current_price,String fluctate_24H,String fluctate_rate_24H,
                String max_price, String min_price, String opening_price,
                String prev_closing_price, String units_traded, String units_traded_24H)
    {
        this.acc_trade_value = acc_trade_value;
        this.acc_trade_value_24H =acc_trade_value_24H;
        this.closing_price =closing_price;
        this.current_price = current_price;
        this.fluctate_24H = fluctate_24H;
        this.fluctate_rate_24H = fluctate_rate_24H;
        this.max_price =max_price;
        this.min_price = min_price;
        this.opening_price = opening_price;
        this.prev_closing_price = prev_closing_price;
        this.units_traded = units_traded;
        this.units_traded_24H = units_traded_24H;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public String getAcc_trade_value() {
        return acc_trade_value;
    }

    public String getAcc_trade_value_24H() {
        return acc_trade_value_24H;
    }

    public String getClosing_price() {
        return closing_price;
    }

    public String getCurrent_price() {
        return current_price;
    }

    public String getFluctate_24H() {
        return fluctate_24H;
    }

    public String getFluctate_rate_24H() {
        return fluctate_rate_24H;
    }

    public String getMax_price() {
        return max_price;
    }

    public String getMin_price() {
        return min_price;
    }

    public String getOpening_price() {
        return opening_price;
    }

    public String getPrev_closing_price() {
        return prev_closing_price;
    }

    public String getUnits_traded() {
        return units_traded;
    }

    public String getUnits_traded_24H() {
        return units_traded_24H;
    }

    public void setAcc_trade_value(String acc_trade_value) {
        this.acc_trade_value = acc_trade_value;
    }

    public void setAcc_trade_value_24H(String acc_trade_value_24H) {
        this.acc_trade_value_24H = acc_trade_value_24H;
    }

    public void setClosing_price(String closing_price) {
        this.closing_price = closing_price;
    }

    public void setCurrent_price(String current_price) {
        this.current_price = current_price;
    }

    public void setFluctate_24H(String fluctate_24H) {
        this.fluctate_24H = fluctate_24H;
    }

    public void setFluctate_rate_24H(String fluctate_rate_24H) {
        this.fluctate_rate_24H = fluctate_rate_24H;
    }

    public void setMax_price(String max_price) {
        this.max_price = max_price;
    }

    public void setMin_price(String min_price) {
        this.min_price = min_price;
    }

    public void setOpening_price(String opening_price) {
        this.opening_price = opening_price;
    }

    public void setPrev_closing_price(String prev_closing_price) {
        this.prev_closing_price = prev_closing_price;
    }

    public void setUnits_traded(String units_traded) {
        this.units_traded = units_traded;
    }

    public void setUnits_traded_24H(String units_traded_24H) {
        this.units_traded_24H = units_traded_24H;
    }
}
