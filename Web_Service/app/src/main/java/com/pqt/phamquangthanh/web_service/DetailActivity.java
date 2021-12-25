package com.pqt.phamquangthanh.web_service;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity extends AppCompatActivity {
    TextView name,user_name,email,address,zipcode,geo,phone,name_company,catchPhraseCompany,bsOfCompany;
    CircleImageView profile_img;

    int id_user = 0;
    String URL= "https://lebavui.github.io";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mapView();
        Intent intent = getIntent();
        id_user = intent.getIntExtra("id",0);
        String url = "https://lebavui.github.io/jsons/users.json";
        ReadJSON(url);


    }
    private void mapView(){
        name               = findViewById(R.id.name);
        user_name          = findViewById(R.id.user_name);
        email              = findViewById(R.id.email);
        address            = findViewById(R.id.address);
        zipcode            = findViewById(R.id.zipcode);
        geo                = findViewById(R.id.geo);
        phone              = findViewById(R.id.phone);
        name_company       = findViewById(R.id.name_company);
        catchPhraseCompany = findViewById(R.id.catchPhraseCompany);
        bsOfCompany        = findViewById(R.id.bsOfCompany);
        profile_img        = findViewById(R.id.profile_img);
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

                                if(id == id_user){
                                    JSONObject object1 = object.getJSONObject("avatar");
                                    String photo     = URL + object1.getString("photo");
                                    Glide.with(DetailActivity.this)
                                            .load(photo)
                                            .into(profile_img);

                                    String name_user     = object.getString("name");
                                    String username_user = object.getString("username");
                                    String email_user    = object.getString("email");
                                    String phone_user    = object.getString("phone");


                                    JSONObject address_user = object.getJSONObject("address");
                                    String address1        = address_user.getString("street")+", "+address_user.getString("suite")+", "+address_user.getString("city");
                                    String address2        = "Zipcode: "+address_user.getString("zipcode");
                                    JSONObject address3    = address_user.getJSONObject("geo");
                                    String address31       ="Geo: lat="+ address3.getString("lat")+", lng = "+ address3.getString("lng");
                                    JSONObject company_user    = object.getJSONObject("company");
                                    String company_name        = company_user.getString("name");
                                    String company_catchPhrase = company_user.getString("catchPhrase");
                                    String company_bs          = company_user.getString("bs");


                                    catchPhraseCompany.setText(company_catchPhrase);
                                    name_company.setText(company_name);
                                    geo.setText(address31);
                                    zipcode.setText(address2);
                                    address.setText(address1);
                                    bsOfCompany.setText(company_bs);
                                    name.setText(name_user);
                                    user_name.setText(username_user);
                                    email.setText(email_user);
                                    phone.setText(phone_user);


                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }
}