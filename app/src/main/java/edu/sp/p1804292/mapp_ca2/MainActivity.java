package edu.sp.p1804292.mapp_ca2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //reset the style back to the app original theme to remove launch/splash screen
        setTheme(R.style.AppTheme);
        Log.d("SplashScreen", "Splash screen works!");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
