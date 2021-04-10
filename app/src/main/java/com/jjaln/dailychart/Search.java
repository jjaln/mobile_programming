package com.jjaln.dailychart;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class Search extends AppCompatActivity{

    public class Setting extends AppCompatActivity {
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_post_search);
            overridePendingTransition(R.anim.vertical_enter,R.anim.none);
            getSupportActionBar().setTitle("게시글 검색");
        }

        @Override
        public void overridePendingTransition(int enterAnim, int exitAnim) {
            super.overridePendingTransition(enterAnim, exitAnim);

        }

        @Override
        public void onBackPressed() {
            super.onBackPressed();
            overridePendingTransition(R.anim.none,R.anim.vertical_exit);
        }
    }
}
