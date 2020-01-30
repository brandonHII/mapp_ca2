package edu.sp.p1804292.mapp_ca2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

public class CampListAdapter extends RecyclerView.Adapter<CampListAdapter.CampViewHolder>{

    private JSONObject items;
    LayoutInflater inflater;
    private JSONArray names;
    Context ctx;

    public CampListAdapter(Context ctx, JSONObject obj){
        //store a reference to the loaded JSONArray from internet
        //items = obj
        setItems(obj);

        //get inflater for later use
        inflater = LayoutInflater.from(ctx);

        this.ctx = ctx;
    }

    public JSONObject getItems(){

        return items;
    }

    public void setItems(JSONObject items){
        this.items = items;
        // store the key names for easy use later to lessen processing the load
        names = items.names();
    }

    @NonNull
    @Override
    public CampListAdapter.CampViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the xml layout for single row item
        View itemView = inflater.inflate(R.layout.recycler_item, parent, false);
        // create view holder and return it
        return new CampListAdapter.CampViewHolder(itemView, this);
        //return null
    }

    @Override
    public void onBindViewHolder(@NonNull CampListAdapter.CampViewHolder holder, int position){
        // retrieve data from array
        try {
            // get the json object at this row position
            JSONObject obj = items.getJSONObject(names.getString(position));
            // display the name in textview
            holder.tv.setText(obj.getString("name") + "\n"
                    + "\n" + obj.getString("date")
                    + "\n" + obj.getString("desc")
                    + "\n" + obj.getString("regDate")
                    + "\n" + obj.getString("price")
                    + "\n" + obj.getString("location"));
        }catch(Exception e){
            Log.e("CampListAdapter ", e.getMessage());
        }
    }

    @Override
    public int getItemCount(){

        return items.length();
    }

    class CampViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv;
        CampListAdapter mAdapter;

        public CampViewHolder(View itemView, CampListAdapter adapter) {
            super(itemView);
            // get the textview for later use
            tv = itemView.findViewById(R.id.word);
            mAdapter = adapter;
            // set click listener
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            try{
                // get the json object at the clicked position
                JSONObject obj = items.getJSONObject(names.getString(this.getAdapterPosition()));
                // print details from json object in Logcat
                Log.d("CampViewHolder ", "Clicked: " +
                        this.getAdapterPosition() + " - " +
                        obj.getString("name"));

            }catch (Exception e){
                Log.e("CampViewHolder", e.getMessage());
            }
        }

    }
}
