package com.jjaln.dailychart.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjaln.dailychart.R;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    public Button button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);


        button = root.findViewById(R.id.writeButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity(), WriteBoard.class);
                getActivity().startActivity(myIntent);
            }
        });

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
}