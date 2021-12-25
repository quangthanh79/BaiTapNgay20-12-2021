package com.pqt.phamquangthanh.web_service;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView ivImg;
    String URL= "https://lebavui.github.io";
    ArrayList<User> data = new ArrayList<>();
    RecyclerView rvUser;
    UserListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        String url = "https://lebavui.github.io/jsons/users.json";
        ReadJSON(url);
        mapView();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvUser.setLayoutManager(linearLayoutManager);
        rvUser.setAdapter(adapter);
        adapter.setOnUserItemClickListener(new UserListViewAdapter.OnUserItemClickListener() {
            @Override
            public void onUserItemClick(int id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }
    private void ReadJSON(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count =0;

                        for(int i=0 ; i< response.length() ; i++){
                            count++;
                            try {
                                JSONObject object = response.getJSONObject(i);
                                int id          = object.getInt("id");
                                String name     = object.getString("name");
                                String username = object.getString("username");
                                String email  = object.getString("email");
                                String phone    = object.getString("phone");
                                String website  = object.getString("website");

                                    // lấy địa chỉ avatar
                                JSONObject object1 = object.getJSONObject("avatar");
                                   String thumbnail = URL + object1.getString("thumbnail");
                                   String photo     = URL + object1.getString("photo");
                                data.add(new User(id,name,username,email,phone,website,photo));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Toast.makeText(MainActivity.this,count+"",Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }
    private void mapView(){
        rvUser = findViewById(R.id.rvUser);
        adapter = new UserListViewAdapter(MainActivity.this,data);
    }
}