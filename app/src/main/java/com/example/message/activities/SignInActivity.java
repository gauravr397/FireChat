package com.example.message.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.message.R;
import com.example.message.databinding.ActivitySignInBinding;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.remote.FirestoreChannel;

import java.util.HashMap;

public class SignInActivity extends AppCompatActivity {
    private ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }
    private void setListeners(){
        binding.textCreateNewAccount.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class)));
        binding.buttonSignIn.setOnClickListener(v -> addDataToFirestore());
    }
    private void addDataToFirestore(){
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        HashMap<String,Object> data =new HashMap<>();
        data.put("first_name","gaurav");
        data.put("last_name", "raghav");
        database.collection("users")
                .add(data)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(getApplicationContext(), "Data inserted", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(exception -> {
                    Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}