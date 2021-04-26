package com.jjaln.dailychart.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jjaln.dailychart.R;

import java.util.HashMap;
import java.util.Map;

public class WriteBoard extends AppCompatActivity {
    private DatabaseReference mDatabase;
    EditText ft, fb, id, pw;
    FloatingActionButton fab;
    private String[] boardList = DashboardFragment.boardList;

    private int boardNum;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_board);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();
        boardNum = intent.getExtras().getInt("boardPosition");

        fab = findViewById(R.id.fabSubmitPost);
        fab.setOnClickListener((v -> { submitPost(); }));
    }

    private void submitPost() {
        ft = findViewById(R.id.fieldTitle);
        fb = findViewById(R.id.fieldBody);
        id = findViewById(R.id.userName);
        pw = findViewById(R.id.userPassword);

        final String title = ft.getText().toString();
        final String body = fb.getText().toString();
        final String name = id.getText().toString();
        final String password = pw.getText().toString();

        // Title is required
        if (TextUtils.isEmpty(title)) {
            ft.setError("REQUIRED");
            return;
        }
        // Body is required
        if (TextUtils.isEmpty(body)) {
            fb.setError("REQUIRED");
            return;
        }
        // User name is required
        if (TextUtils.isEmpty(name)) {
            id.setError("REQUIRED");
            return;
        }
        // Password is required
        if (TextUtils.isEmpty(password)) {
            pw.setError("REQUIRED");
            return;
        }

        setEditingEnabled(false);
        Toast.makeText(getApplicationContext(), "Posting...", Toast.LENGTH_SHORT).show();

        writeNewPost(name, password, title, body);
        setEditingEnabled(true);

        finish();
    }
    private void writeNewPost(String username, String password, String title, String body) {
        // post 차일드 생성 후 게시글 정보 푸시
        String key = mDatabase.child("posts").push().getKey();
        Post post = new Post(username, password, title, body);
        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/posts/" + boardList[boardNum] + "/" + key, postValues);

        mDatabase.updateChildren(childUpdates);
    }

    private void setEditingEnabled(boolean enabled) { // 글 작성중 조작 불가
        ft.setEnabled(enabled);
        fb.setEnabled(enabled);
        id.setEnabled(enabled);
        pw.setEnabled(enabled);

        if (enabled) {
            fab.show();
        } else {
            fab.hide();
        }
    }

}