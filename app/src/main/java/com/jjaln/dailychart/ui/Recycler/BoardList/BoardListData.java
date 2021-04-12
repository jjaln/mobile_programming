package com.jjaln.dailychart.ui.Recycler.BoardList;

public class BoardListData {

    private int board_icon;
    private String board_name;

    public BoardListData(int img, String text)
    {
        this.board_icon = img;
        this.board_name = text;
    }

    public int getImg() { return this.board_icon; }
    public String getText()
    {
        return this.board_name;
    }
}