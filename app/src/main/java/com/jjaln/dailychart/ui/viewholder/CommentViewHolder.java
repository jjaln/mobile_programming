package com.jjaln.dailychart.ui.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jjaln.dailychart.R;

public class CommentViewHolder extends RecyclerView.ViewHolder {
    public TextView authorView;
    public TextView bodyView;

    public CommentViewHolder(View itemView) {
        super(itemView);
        authorView = itemView.findViewById(R.id.commentAuthor);
        bodyView = itemView.findViewById(R.id.commentBody);
    }
}
