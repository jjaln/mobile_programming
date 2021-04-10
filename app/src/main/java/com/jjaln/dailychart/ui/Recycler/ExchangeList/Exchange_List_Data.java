package com.jjaln.dailychart.ui.Recycler.ExchangeList;

public class Exchange_List_Data {

    private int img;
    private String uri;
    private String name;

    public Exchange_List_Data(int img, String uri, String name)
    {
        this.img = img;
        this.uri = uri;
        this.name = name;
    }


    public String getUri()
    {
        return this.uri;
    }
    public String getName() {return this.name;}
    public int getImg()
    {
        return this.img;
    }
}
