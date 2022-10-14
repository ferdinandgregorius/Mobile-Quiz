package com.example.mobilequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button loginBtn;
    private Button registerBtn;
    private EditText inputUsername;
    private EditText inputPassword;
    private String usernameString;
    private String passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = findViewById(R.id.login_btn);
        registerBtn = findViewById(R.id.register_btn);

        inputUsername = findViewById(R.id.username_field);
        inputPassword = findViewById(R.id.password_field);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usernameString = inputUsername.getText().toString();
                passwordString = inputPassword.getText().toString();

                if(!usernameString.equalsIgnoreCase("Admin") && !passwordString.equalsIgnoreCase("Password")){
                    Toast.makeText(getApplicationContext(), "Wrong Username and Password", Toast.LENGTH_SHORT).show();
                }else if(!usernameString.equalsIgnoreCase("Admin") || !passwordString.equalsIgnoreCase("Password")){
                    Toast.makeText(getApplicationContext(), "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(view.getContext(), List.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("username", "admin");
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Register.class);
                startActivity(intent);
            }
        });
    }
}