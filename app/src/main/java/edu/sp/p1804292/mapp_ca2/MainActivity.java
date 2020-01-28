package edu.sp.p1804292.mapp_ca2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //reset the style back to the app original theme to remove launch/splash screen
        setTheme(R.style.AppTheme);
        Log.d("SplashScreen", "Splash screen works!");

        super.onCreate(savedInstanceState);
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
            case R.id.settings: //Does nothing yet

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
