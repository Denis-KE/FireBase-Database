package com.deno.myfirebasedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class ViewUsersActivity extends AppCompatActivity {
    ListView list;
    CustomAdapter adapter;
    ArrayList<ItemConstructor> users;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);
        list =findViewById(R.id.listYangu);
        users =new ArrayList<>();
        adapter =new CustomAdapter(this,users);
        dialog =new ProgressDialog(this);
        dialog.setTitle("Loading");
        dialog.setMessage("Please wait...");
        //We can connect to our Users table to get the data to display
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");

        //we can add value event listener to our ref to listen to data as it comes in the display
        dialog.show();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();
                for (DataSnapshot snap:dataSnapshot.getChildren()){
                    ItemConstructor user =snap.getValue(ItemConstructor.class);
                    users.add(user);
                    Collections.reverse(users);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ViewUsersActivity.this, "Sorry.DB Locked", Toast.LENGTH_SHORT).show();

            }
        });
        list.setAdapter(adapter);
    }
}
