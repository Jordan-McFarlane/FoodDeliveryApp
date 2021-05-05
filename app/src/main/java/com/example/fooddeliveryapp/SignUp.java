package com.example.fooddeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class SignUp extends AppCompatActivity {
    EditText name;
    EditText passwordView;
    EditText phoneView;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = (EditText) findViewById(R.id.nameView);
        phoneView = (EditText) findViewById(R.id.phoneNumber);
        passwordView = (EditText) findViewById(R.id.passwordView);
        signUp = (Button) findViewById(R.id.signUpView);


        FirebaseApp.initializeApp(this);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference account_user = db.getReference("User");



        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                final ProgressDialog dialog = new ProgressDialog(SignUp.this);
                dialog.setMessage("processing...");
                dialog.show();

                account_user.addValueEventListener(
                    new ValueEventListener()
                    {@Override

                        public void onDataChange(@NonNull DataSnapshot snapshot){
                        if(snapshot.child(phoneView.getText().toString()).exists()){
                            dialog.dismiss();
                            Toast.makeText(SignUp.this, "Can't add existing phone numbers", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            dialog.dismiss();
                            User user = new User(name.getText().toString(), passwordView.getText().toString());
                            account_user.child(phoneView.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this, "Sign Up complete", Toast.LENGTH_SHORT).show();
                            finish();

                        }



                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }


                    }

                );




            }
        });
    }
}