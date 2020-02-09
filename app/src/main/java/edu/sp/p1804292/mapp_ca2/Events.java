package edu.sp.p1804292.mapp_ca2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class Events extends AppCompatActivity {

    private SharedPreferences mPrefs;
    private String sharedPrefFile = "edu.sp.p1804292.sharedPrefs";
    //keys for the shared prefs
    private final String DM_KEY = "darkMode";
    private boolean mDM;

    final String TAG = "Others";
    OtherListOpenHelper mDB;
    CampListOpenHelper mDBC;

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

        setContentView(R.layout.activity_events);
        queue = Volley.newRequestQueue(this);

        try{
        connectToInternetO();
        connectToInternetC();
        } catch(Exception e)
        {
            //Tells user to connect to internet so can take data and store it in DB
        }

    }

    public void launchCamp_Events(View view)	{
        Intent intent	=	new	Intent(this,	Camps.class);
        startActivity(intent);
    }

    public void launchAGM_Events(View view)	{
        Intent intent	=	new	Intent(this,	AGM.class);
        startActivity(intent);
    }


    public void launchTalks_Events(View view) {
        Intent intent	=	new	Intent(this,	Others.class);
        startActivity(intent);
    }

    void connectToInternetO() {

        String url = "https://mapp2-47d6c.firebaseio.com/Others.json";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        parseDataO(response);

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

    void parseDataO(String s){
        try {
            JSONObject jsonObject = new JSONObject(s);
            Log.d("Test","xd"+jsonObject);
            mDB = new OtherListOpenHelper(this);
           int NumObj = jsonObject.length();
           int NumEntry=  mDB.count();
           // If object > database then update database
            if( NumObj > NumEntry) {
                mDB.fillDatabaseWithData(jsonObject);
            }
        }
        catch(Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    void connectToInternetC() {

        String url = "https://assignmentca2-5ad43.firebaseio.com/camp/camps.json";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        parseDataC(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    void parseDataC(String s){
        try {
            JSONObject jsonObject = new JSONObject(s);
            Log.d("Test","xd"+jsonObject);
            mDBC = new CampListOpenHelper(this);
            int NumObj = jsonObject.length();
            int NumEntry=  mDBC.count();
            // If object > database then update database
            if( NumObj > NumEntry) {
                mDBC.fillDatabaseWithData(jsonObject);
            }
        }
        catch(Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
