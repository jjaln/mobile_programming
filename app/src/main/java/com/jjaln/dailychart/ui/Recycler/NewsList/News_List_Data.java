package com.jjaln.dailychart.ui.Recycler.NewsList;

public class News_List_Data {

    private String title;
    private String desc;
    private String url;

    public News_List_Data(String title,String desc, String url)
    {
        this.desc = desc;
        this.title = title;
        this.url = url;
    }


    public String getUrl()
    {
        return this.url;
    }
    public String getDesc() {return this.desc;}
    public String getTitle() {return this.title;}
}
