package com.jjaln.dailychart.ui.dashboard;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Comment {

    public String author;
    public String password;
    public String text;

    public Comment() {
        // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
    }

    public Comment(String author, String password, String text) {
        this.author = author;
        this.password = password;
        this.text = text;
    }

}
