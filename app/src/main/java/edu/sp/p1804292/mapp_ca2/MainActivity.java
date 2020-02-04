package edu.sp.p1804292.mapp_ca2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mPrefs;
    private String sharedPrefFile = "edu.sp.p1804292.sharedPrefs";
    //keys for the shared prefs
    private final String DM_KEY = "darkMode";

    private boolean mDM;


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

        setContentView(R.layout.activity_main);

    }

    //Button For social media
    public	void launchSocialMedia(View view)	{
        Intent intent	=	new	Intent(this,	SocialMedia.class);
        startActivity(intent);
    }

    public	void launchEvents(View view)	{
        Intent intent	=	new	Intent(this,	Events.class);
        startActivity(intent);
    }

    //Menu button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }
    // Actions to take for when settings and menu is clicked
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.aboutUs:
                startActivity(new Intent(this,AboutPage.class));
                return true;
            default:
// Do nothing
        }
        return super.onOptionsItemSelected(item);
    }
}
