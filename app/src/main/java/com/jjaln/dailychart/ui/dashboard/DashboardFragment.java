package com.jjaln.dailychart.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.jjaln.dailychart.R;
import com.jjaln.dailychart.ui.viewholder.PostViewHolder;

public class DashboardFragment extends Fragment {

    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]

    private FirebaseRecyclerAdapter<Post, PostViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    public static String[] boardList = new String[] {"비트코인", "이더리움", "리플", "에이다", "폴카닷"};
    private int boardNum = -1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            boardNum = bundle.getInt("boardPosition");
        }

        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]

        mRecycler = root.findViewById(R.id.messagesList);
        mRecycler.setHasFixedSize(true);

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                AppCompatActivity activity = (AppCompatActivity)getContext();
                Fragment myFragment = new BoardList();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.action_container, myFragment).addToBackStack(null).commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getActivity(), callback);
        // The callback can be enabled or disabled here or in handleOnBackPressed()

//        // database 오브젝트화
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//
//        // DB에 있는 message 항목 참조
//        DatabaseReference myRef = database.getReference("message");
//        ArrayList<String> value = new ArrayList<>();
//
//
//        // DB에 있는 데이터 읽어서 value에 추가
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                value.add(dataSnapshot.getValue(String.class));
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//            }
//        });


        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set up Layout Manager, reverse layout
        mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        Query postsQuery = mDatabase.child("posts/" + boardList[boardNum]);

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Post>()
                .setQuery(postsQuery, Post.class)
                .build();


        mAdapter = new FirebaseRecyclerAdapter<Post, PostViewHolder>(options) {

            @Override
            public PostViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                return new PostViewHolder(inflater.inflate(R.layout.item_post, viewGroup, false));
            }

            @Override
            protected void onBindViewHolder(PostViewHolder viewHolder, int position, final Post model) {
                final DatabaseReference postRef = getRef(position);

                // Set click listener for the whole post view
                final String postKey = postRef.getKey();
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle arg = new Bundle();
                        arg.putInt("boardPosition", boardNum);
                        arg.putString(PostDetailFragment.EXTRA_POST_KEY, postKey);

                        AppCompatActivity activity = (AppCompatActivity)view.getContext();
                        Fragment myFragment = new PostDetailFragment();
                        myFragment.setArguments(arg);
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.action_container, myFragment).addToBackStack(null).commit();


                    }
                });

//                // Determine if the current user has liked this post and set UI accordingly
//                if (model.stars.containsKey(getUid())) {
//                    viewHolder.starView.setImageResource(R.drawable.ic_toggle_star_24);
//                } else {
//                    viewHolder.starView.setImageResource(R.drawable.ic_toggle_star_outline_24);
//                }
//
//                // Bind Post to ViewHolder, setting OnClickListener for the star button
//                viewHolder.bindToPost(model, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View starView) {
//                        // Need to write to both places the post is stored
//                        DatabaseReference globalPostRef = mDatabase.child("posts").child(postRef.getKey());
//
//                        onStarClicked(globalPostRef);
//                    }
//                });
                viewHolder.bindToPost(model);
            }
        };
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAdapter != null) {
            mAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.dashboard_actions,menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_posting:
                Intent myIntent = new Intent(getActivity(), WriteBoard.class);
                myIntent.putExtra("boardPosition", boardNum);
                getActivity().startActivity(myIntent);
                return true;
            case R.id.action_search:
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}