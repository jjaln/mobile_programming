package com.jjaln.dailychart.ui.Recycler.NewsList;

public class News_List_Data {

    private String title;
    private String url;

    public News_List_Data(String title, String url)
    {
        this.title = title;
        this.url = url;
    }


    public String getUrl()
    {
        return this.url;
    }
    public String getTitle() {return this.title;}
}
