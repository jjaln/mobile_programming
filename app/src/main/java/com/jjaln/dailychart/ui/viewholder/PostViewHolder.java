package com.jjaln.dailychart.ui.viewholder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jjaln.dailychart.R;
import com.jjaln.dailychart.ui.dashboard.Post;

public class PostViewHolder extends RecyclerView.ViewHolder {

    public TextView titleView;
    public TextView authorView;
//    public ImageView starView;
//    public TextView numStarsView;
    public TextView bodyView;

    public PostViewHolder(View itemView) {
        super(itemView);

        titleView = itemView.findViewById(R.id.postTitle);
        authorView = itemView.findViewById(R.id.postAuthor);
//        starView = itemView.findViewById(R.id.star);
//        numStarsView = itemView.findViewById(R.id.postNumStars);
        bodyView = itemView.findViewById(R.id.postBody);
    }

    public void bindToPost(Post post) {
        titleView.setText(post.title);
        authorView.setText(post.author);
//        numStarsView.setText(String.valueOf(post.starCount));
//        starView.setOnClickListener(starClickListener);
        bodyView.setText(post.body);

    }
}
