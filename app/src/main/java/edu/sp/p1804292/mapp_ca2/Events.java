package edu.sp.p1804292.mapp_ca2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

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
        Intent intent	=	new	Intent(this,	Talks.class);
        startActivity(intent);
    }
}
