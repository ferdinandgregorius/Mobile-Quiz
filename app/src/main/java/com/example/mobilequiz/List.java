package com.example.mobilequiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.HashMap;

public class List extends AppCompatActivity {

    ArrayList <User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        users = new ArrayList<User>();

        String url = "https://reqres.in/api/users";

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(getStringRequest(url));

    }

    private void prepareListView() {
        RecyclerView rv = findViewById(R.id.list_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        DataModelAdapter dataModelAdapter = new DataModelAdapter(users);

        rv.setLayoutManager(layoutManager);
        rv.setAdapter(dataModelAdapter);
    }

    private StringRequest getStringRequest(String url){
        return new StringRequest(Request.Method.GET, url, (response) -> {
            try{
                JSONObject object = new JSONObject(response);
                JSONArray array = object.getJSONArray("data");

                System.out.println("json size is : "+array.length());

                for (int i = 0; i<array.length(); i++){
                    JSONObject user = array.getJSONObject(i);
                    String firstName = user.getString("first_name");
                    String lastName = user.getString("last_name");
                    String email = user.getString("email");
                    String avatar = user.getString("avatar");

                    users.add(new User(email, firstName, lastName, avatar));

                    prepareListView();
                }

            } catch (JSONException e){
                e.printStackTrace();
            }

        }, (error) -> {
            System.out.println(error);
        });
    }
}

class DataModelAdapter extends RecyclerView.Adapter<DataModelViewHolder> {

    ArrayList<User> users;

    public DataModelAdapter(ArrayList<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public DataModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item_card_view, parent, false);

        return new DataModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataModelViewHolder holder, int position) {
        User user = users.get(position);

        holder.firstName.setText(user.getFirstname());
        holder.lastName.setText(user.getLastname());
        holder.email.setText(user.getEmail());
        Picasso.get().load(user.getAvatar()).into(holder.avatar);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Details.class);
                intent.putExtra("selectedUser", new Gson().toJson(user));
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}

class DataModelViewHolder extends RecyclerView.ViewHolder {
    View parent;
    TextView firstName;
    TextView lastName;
    TextView email;
    ImageView avatar;

    public DataModelViewHolder(@NonNull View itemView) {
        super(itemView);
        parent = itemView;
        firstName = itemView.findViewById(R.id.first_name);
        lastName = itemView.findViewById(R.id.last_name);
        email = itemView.findViewById(R.id.email_text);
        avatar = itemView.findViewById(R.id.avatar);
    }
}