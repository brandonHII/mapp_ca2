package edu.sp.p1804292.mapp_ca2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class Camps extends AppCompatActivity {

    final String TAG = "Camps";
    RecyclerView recyclerView;
    CampListAdapter mAdapter;
    JSONObject items = new JSONObject();

    // store RequestQueue as static to be shared in this app
    public static RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camps);

        queue = Volley.newRequestQueue(this);

        setupRecycler();
        connectToInternet();
    }

    void setupRecycler(){
        recyclerView = findViewById(R.id.recyclerview);
        mAdapter = new CampListAdapter(this, items);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //ImageView img = (ImageView)findViewById(R.id.imgplay);
        // need to use mAdapter
        //img.setImageResource(R.drawable.sp1);
    }

    void connectToInternet(){
        String url ="https://assignmentca2-5ad43.firebaseio.com/camp/camps.json";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        // Log.d(TAG, "Response is: "+ response.substring(0,500));
                        Log.d(TAG, "Response is: "+ response);

                        parseData(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    void parseData(String s){
        try {
            JSONObject jsonObject = new JSONObject(s);
            // once data is loaded, update adapter for RecyclerView
            mAdapter.setItems(jsonObject);
            mAdapter.notifyDataSetChanged();
        }
        catch(Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
