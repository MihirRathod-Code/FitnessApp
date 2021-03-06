package com.example.musclemonster_fitnessapp.Admin;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musclemonster_fitnessapp.Admin.AdapterClasses.Adapter_View_Users_Admin;
import com.example.musclemonster_fitnessapp.POJOClasses.UsersPojo;
import com.example.musclemonster_fitnessapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class ActivityViewUsers extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference database;
    Adapter_View_Users_Admin AdapterUser;
    ArrayList<UsersPojo> list;
    ImageButton btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);

        getSupportActionBar().setTitle("Users");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4b134f")));

        firebaseDatabase=FirebaseDatabase.getInstance();
        //database=firebaseDatabase.getReference();
        recyclerView=findViewById(R.id.recyclerUserAdmin);
        database=FirebaseDatabase.getInstance().getReference("Users");
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<UsersPojo>();
        AdapterUser=new Adapter_View_Users_Admin(this,list);
        recyclerView.setAdapter(AdapterUser);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        //  call the constructor of CustomAdapter to send the reference and data to Adapter



        /*Query query=FirebaseDatabase.getInstance().getReference("Users").orderByKey();
        String ema ;
        FirebaseUser firebaseUser;
        FirebaseAuth firebaseAuth;

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    if (dataSnapshot.exists()) {

                        //String K = dataSnapshot.getKey();
                        //list.add(Description);
                        //*for (DataSnapshot DtSnapshot : dataSnapshot.getChildren()) {
                        UsersPojo Obj = new UsersPojo();
                        // DtSnapshot.getValue(ProductUpload_POJO.class);
                        // Obj.setFKey(dataSnapshot.getKey());
                        Obj.setFirstName((dataSnapshot.child("firstName").getValue(String.class)));
                        Obj.setEmail((dataSnapshot.child("email").getValue(String.class)));
                        Obj.setLastName((dataSnapshot.child("lastName").getValue(String.class)));
                        Obj.setPhoneNumber((dataSnapshot.child("phoneNumber").getValue(String.class)));
                        if(dataSnapshot.child("imageUri").getValue(String.class)!=null) {
                            Obj.setImageUrl((dataSnapshot.child("imageUri").getValue(String.class)));
                        }else{
                            Obj.setImageUrl("null");
                        }
                        // Obj.setSflag((dataSnapshot.child("sflag").getValue(String.class)));
                        list.add(Obj);
                    } else {
                        Log.i("Result UnSuccessfull", "NO Data : ");
                    }
                }
                // set the Adapter to RecyclerView
                AdapterUser.notifyDataSetChanged();

                Log.i("Product Adapter ", "Product Binded ");
            }

            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {
            }
        });*/

    }
    @Override
    protected void onStop() {
        super.onStop();
        list.clear();
        AdapterUser.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        AdapterUser.notifyDataSetChanged();
        getData();
    }

    private void getData() {

        Query query=FirebaseDatabase.getInstance().getReference("Users").orderByKey();
        String ema ;
        FirebaseUser firebaseUser;
        FirebaseAuth firebaseAuth;

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    if (dataSnapshot.exists()) {

                        //String K = dataSnapshot.getKey();
                        //list.add(Description);
                        //*for (DataSnapshot DtSnapshot : dataSnapshot.getChildren()) {
                        UsersPojo Obj = new UsersPojo();
                        // DtSnapshot.getValue(ProductUpload_POJO.class);
                        Obj.setSflag(dataSnapshot.getKey());
                        Obj.setFirstName((dataSnapshot.child("firstName").getValue(String.class)));
                        Obj.setEmail((dataSnapshot.child("email").getValue(String.class)));
                        Obj.setLastName((dataSnapshot.child("lastName").getValue(String.class)));
                        Obj.setPhoneNumber((dataSnapshot.child("phoneNumber").getValue(String.class)));
                        if(dataSnapshot.child("imageUri").getValue(String.class)!=null) {
                            Obj.setImageUrl((dataSnapshot.child("imageUri").getValue(String.class)));
                        }else{
                            Obj.setImageUrl("null");
                        }
                        // Obj.setSflag((dataSnapshot.child("sflag").getValue(String.class)));
                        list.add(Obj);
                    } else {
                        Log.i("Result UnSuccessfull", "NO Data : ");
                    }
                }
                // set the Adapter to RecyclerView
                AdapterUser.notifyDataSetChanged();

                Log.i("Product Adapter ", "Product Binded ");
            }

            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {
            }
        });

    }
}