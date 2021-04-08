package com.jjaln.dailychart.ui.Recycler;

public class Coin_List_Data {

    private int img;
    private String text;

    public Coin_List_Data(int img,String text)
    {
        this.img = img;
        this.text= text;
    }

    public String getText()
    {
        return this.text;
    }
    public int getImg()
    {
        return this.img;
    }
}
