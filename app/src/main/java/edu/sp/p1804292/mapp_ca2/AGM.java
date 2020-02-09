package edu.sp.p1804292.mapp_ca2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class AGM extends AppCompatActivity {

    private SharedPreferences mPrefs;
    private String sharedPrefFile = "edu.sp.p1804292.sharedPrefs";
    //keys for the shared prefs
    private final String DM_KEY = "darkMode";
    private boolean mDM;

    final String TAG = "AGM";
    RecyclerView recyclerView;
    AGMListAdapter mAdapter;
    JSONObject items = new JSONObject();

    // store RequestQueue as static to be shared in this app
    public static RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPrefs = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        //get preferences
        mDM = mPrefs.getBoolean(DM_KEY, false);


        if (mDM==true){
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.LightTheme);
        }

        setContentView(R.layout.activity_agm);
        queue = Volley.newRequestQueue(this);

        setupRecycler();
        connectToInternet();
    }

    void setupRecycler(){
        recyclerView = findViewById(R.id.AGMrecyclerView);
        mAdapter = new AGMListAdapter(this, items);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    void connectToInternet(){
        String url ="https://mappca-ad56b.firebaseio.com/AGM.json";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        parseData(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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
