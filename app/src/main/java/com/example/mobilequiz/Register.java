package com.example.mobilequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private Button registerBtn2;
    private Button backBtn;
    private EditText inputUsername;
    private EditText inputPassword;
    private EditText confirmPassword;
    private String usernameString;
    private String passwordString;
    private String confirmString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerBtn2 = findViewById(R.id.register_btn2);
        backBtn = findViewById(R.id.back_btn);

        inputUsername = findViewById(R.id.username_field);
        inputPassword = findViewById(R.id.password_field);
        confirmPassword = findViewById(R.id.confirm_field);

        registerBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usernameString = inputUsername.getText().toString();
                passwordString = inputPassword.getText().toString();
                confirmString = confirmPassword.getText().toString();

                if(usernameString.equalsIgnoreCase("") && passwordString.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "Please enter a Username and Password!", Toast.LENGTH_SHORT).show();
                }else if(!confirmString.equalsIgnoreCase(passwordString)){
                    Toast.makeText(getApplicationContext(), "Password confirmation does not match!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "User Registered!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}