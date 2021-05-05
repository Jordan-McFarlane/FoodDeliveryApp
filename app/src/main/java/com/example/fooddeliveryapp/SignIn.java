package com.example.fooddeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    EditText phoneView;
    EditText passwordView;
    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        FirebaseApp.initializeApp(this);


        phoneView = (EditText) findViewById(R.id.phoneNumber);
        passwordView = (EditText) findViewById(R.id.passwordView);
        signIn = (Button) findViewById(R.id.signIn);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference account_user = db.getReference("User");




        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final ProgressDialog dialog = new ProgressDialog(SignIn.this);
                dialog.setMessage("processing...");
                dialog.show();


                account_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child(phoneView.getText().toString()).exists()) {

                            dialog.dismiss();
                            User user = snapshot.child(phoneView.getText().toString()).getValue(User.class);
                            System.out.println("Password"+passwordView.getText().toString());

                            if (user.getPassword().equals(passwordView.getText().toString())) {
                                Toast.makeText(SignIn.this, "Sign in complete", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignIn.this, "Sorry, Please password correctly", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            dialog.dismiss();
                            Toast.makeText(SignIn.this, "Invalid Account info", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                switchActivities();

            }

        });









    }



    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, FrontPage.class);
        startActivity(switchActivityIntent);
    }


}