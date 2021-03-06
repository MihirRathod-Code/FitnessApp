package com.example.musclemonster_fitnessapp.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musclemonster_fitnessapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class Admin_Del_Prod extends AppCompatActivity {

    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_del_prod);
        progressDialog = new ProgressDialog(Admin_Del_Prod.this);
                progressDialog.setTitle("Advertisement is Deleting....");
                progressDialog.show();
        String ChildKey = getIntent().getStringExtra("ItemKey").toString();
        FirebaseDatabase.getInstance().getReference("Product_Detail_Database").child(ChildKey).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                Toast.makeText(getApplicationContext(),"Advertisement Deleted" ,Toast.LENGTH_LONG).show();
                finish();
                Intent intent = new Intent(getApplicationContext(), MyProducts_Admin.class);
                progressDialog.dismiss();
                startActivity(intent);

            }
        });

    }
}