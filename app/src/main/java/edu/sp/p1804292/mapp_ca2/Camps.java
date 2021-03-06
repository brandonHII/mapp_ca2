package edu.sp.p1804292.mapp_ca2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.ArrayList;

public class Camps extends AppCompatActivity {

    private SharedPreferences mPrefs;
    private String sharedPrefFile = "edu.sp.p1804292.sharedPrefs";
    //keys for the shared prefs
    private final String DM_KEY = "darkMode";
    private boolean mDM;
    JSONObject items = new JSONObject();

   final String TAG = "Camps";
    RecyclerView recyclerView;
    CampListAdapter mAdapter;
   CampListOpenHelper mDB;
   ArrayList<CampItem> tex;



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

        setContentView(R.layout.activity_camps);

        try{
            setupRecycler();
        } catch (Exception e) {
            //Tell user to connect internet to see

        }
    }

    void setupRecycler(){

        recyclerView = findViewById(R.id.recyclerview);
        mDB = new CampListOpenHelper(this);
        tex = mDB.query();
        mAdapter= new CampListAdapter(this, tex);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));

    }

}
