package edu.sp.p1804292.mapp_ca2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;


import androidx.appcompat.app.AppCompatActivity;


public class SettingsActivity extends AppCompatActivity {

    private SharedPreferences mPrefs;
    private String sharedPrefFile = "edu.sp.p1804292.sharedPrefs";
    //keys for the shared prefs
    private final String DM_KEY = "darkMode";

    private boolean mDM;

    private  Switch DMSwitch;

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

        setContentView(R.layout.settings_activity);

        //get the views
        DMSwitch = (Switch)findViewById(R.id.DM_switch);

        //Check if the switch is true
        DMSwitch.setChecked(mDM);

        //Listens to the switch
        DMSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                mDM = isChecked;

                saveAll();

                Intent intent = getIntent();
                finish();
                startActivity(intent);

            }

        }
        );




    }

    void getAll(){
        mDM = DMSwitch.isChecked();

    }

    void saveAll(){
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putBoolean(DM_KEY, mDM);
        editor.apply();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getAll();
        saveAll();
    }
}