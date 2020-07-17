package com.example.githubapi;

/**
 * Author:    Swaraj Deshmukh
 * Created:   01.06.2019
 *
 **/

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.githubapi.Adapter.Adapter;
import com.example.githubapi.Model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    RecyclerView.Adapter adapter;
    List<User> user;
    String URL_Data = "https://api.github.com/users";
    RequestQueue reqQue; // Volley queue mein krta hai

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);

        rv.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
//        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));



        user = new ArrayList<>();
        loadurl();

    }

    private void loadurl() {

        JsonArrayRequest stringRequest = new JsonArrayRequest(URL_Data,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        getvalue(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        reqQue = Volley.newRequestQueue(this);

        reqQue.add(stringRequest);
    }

    public void getvalue(JSONArray array){

        for (int i=0;i<array.length();i++){

            User userlist = new User();

            JSONObject json = null;
            try {
                json = array.getJSONObject(i);

                userlist.setLogin(json.getString("login"));

                userlist.setAvatarUrl(json.getString("avatar_url"));
            }catch (JSONException e){

                e.printStackTrace();

            }
            user.add(userlist);
        }

       adapter = new Adapter(user,this);

        rv.setAdapter(adapter);
    }
}