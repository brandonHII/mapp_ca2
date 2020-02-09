package edu.sp.p1804292.mapp_ca2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class Others extends AppCompatActivity {

    private SharedPreferences mPrefs;
    private String sharedPrefFile = "edu.sp.p1804292.sharedPrefs";
    //keys for the shared prefs
    private final String DM_KEY = "darkMode";

    private boolean mDM;

    RecyclerView recyclerView;
    OtherListAdapter mAdapter;
    OtherListOpenHelper mDB;
    ArrayList<OtherItem> tex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPrefs = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        //get preferences
        mDM = mPrefs.getBoolean(DM_KEY, false);


        if (mDM == true) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.LightTheme);
        }
        setContentView(R.layout.activity_others);

        try{
        setupRecycler();
        } catch (Exception e) {
            //Tell user to connect internet to see

        }

    }


    void setupRecycler() {

        recyclerView = findViewById(R.id.OtherRecyclerView);
        mDB = new OtherListOpenHelper(this);
        tex = mDB.query();
         mAdapter= new OtherListAdapter(this, tex);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));
    }



}
