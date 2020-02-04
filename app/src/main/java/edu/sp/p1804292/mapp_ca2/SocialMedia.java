package edu.sp.p1804292.mapp_ca2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;

public class SocialMedia extends AppCompatActivity {


    private SharedPreferences mPrefs;
    private String sharedPrefFile = "edu.sp.p1804292.sharedPrefs";
    //keys for the shared prefs
    private final String DM_KEY = "darkMode";

    private boolean mDM;

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPrefs = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        //get preferences and set theme to Light/Dark Mode
        mDM = mPrefs.getBoolean(DM_KEY, false);


        if (mDM==true){
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.LightTheme);
        }
        setContentView(R.layout.activity_social_media);

        // get resources from the array to be sent to the adapter
        Resources res = getResources();
        String [] schoolNameArray = res.getStringArray(R.array.school_Name);
        String [] SMLinkArray = res.getStringArray(R.array.SM_Links);
        initializeView(schoolNameArray,SMLinkArray);
    }

    private void initializeView( String []schoolNames, String [] SMLinks) {

        recyclerView = findViewById(R.id.SMrecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SocialMedia.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        SMAdapter myAdapter = new SMAdapter(SocialMedia.this,schoolNames, SMLinks);
        recyclerView.setAdapter(myAdapter);
    }
}

