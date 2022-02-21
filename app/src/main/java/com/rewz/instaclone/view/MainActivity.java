package com.rewz.instaclone.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rewz.instaclone.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();

        FirebaseUser user = auth.getCurrentUser();

        if(user != null) {
            Intent intent = new Intent(this,FeedActivity.class);
            startActivity(intent);
            finish();
        }



    }

    public void signInClicked (View view) {

        String email = binding.emailText.getText().toString();
        String password = binding.passwordText.getText().toString();

        if(email.equals("") || password.equals("")) {
            Toast.makeText(this, "Enter email and password", Toast.LENGTH_SHORT).show();
        } else {
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(authResult -> {
               Intent intent= new Intent(this,FeedActivity.class);
               startActivity(intent);
               finish();

            }).addOnFailureListener(e -> {
                Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            });
        }


    }
    public void signUpClicked (View view) {

        String email = binding.emailText.getText().toString();
        String password = binding.passwordText.getText().toString();


        if(email.equals("") || password.equals("")) {
            Toast.makeText(this, "Enter email and password!", Toast.LENGTH_SHORT).show();
        } else {
            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(authResult -> {

                Intent intent = new Intent(MainActivity.this,FeedActivity.class);
                startActivity(intent);
                finish();

            }).addOnFailureListener(e -> {
                Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            });
        }

    }
}










