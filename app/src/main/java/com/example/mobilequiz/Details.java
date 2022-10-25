package com.example.mobilequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.mobilequiz.Models.User;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Details extends AppCompatActivity {

    TextView firstname;
    TextView lastname;
    TextView email;
    ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        firstname = findViewById(R.id.first_name_text_view);
        lastname = findViewById(R.id.last_name_text_view);
        email = findViewById(R.id.email_view);
        avatar = findViewById(R.id.image_view);

        Bundle userBundle = getIntent().getExtras();
        String jsonObject = userBundle.getString("selectedUser");

        User user = new Gson().fromJson(jsonObject, User.class);

        firstname.setText(user.getFirstname());
        lastname.setText(user.getLastname());
        email.setText(user.getEmail());
        Picasso.get().load(user.getAvatar()).into(avatar);
    }
}