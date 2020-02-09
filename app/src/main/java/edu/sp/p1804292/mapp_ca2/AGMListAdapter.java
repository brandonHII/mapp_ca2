package edu.sp.p1804292.mapp_ca2;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

public class AGMListAdapter extends RecyclerView.Adapter<AGMListAdapter.AGMViewHolder> {
    private JSONObject items;
    LayoutInflater inflater;
    private JSONArray names;
    Context ctx;

    public AGMListAdapter(Context ctx, JSONObject obj){
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
    public AGMListAdapter.AGMViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the xml layout for single row item
        View itemView = inflater.inflate(R.layout.agm_item, parent, false);
        // create view holder and return it
        return new AGMListAdapter.AGMViewHolder(itemView, this);
        //return null
    }

    @Override
    public void onBindViewHolder(@NonNull AGMListAdapter.AGMViewHolder holder, int position){
        // retrieve data from array


        try {
            // get the json object at this row position
             JSONObject obj = items.getJSONObject(names.getString(position));


            // display the name in textview

           final double x = obj.getDouble("longitude");
           final double y = obj.getDouble("latitude");
           final String z = obj.getString("location");

            //Uri uri = Uri.parse(obj.getString("link"));
            //Intent signUp = new Intent(Intent.ACTION_VIEW, uri);

            holder.tv.setText(obj.getString("name")
                    + "\n" + obj.getString("desc")
                    + "\n\nDate: " + obj.getString("date")
                    + "\nLocation: " + obj.getString("location"));

            //holder.tv.setText(obj.getString("link").set);
            //ctx.startActivity(signUp);


            //get number of item
            int num = getItemCount();

            for(int i = 0; i < num; i++){
                if (position == i){
                    // name of drawable
                    String p = "a" + (1+i);
                    //get the id of drawable
                    int pic = ctx.getResources().getIdentifier(p, "drawable", ctx.getPackageName());
                    holder.img.setImageResource(pic);

                }
            }

            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                        public void onClick(View view) {
                    Intent intent = new Intent(ctx, MapsActivity.class);
                    intent.putExtra("Long", x);
                    intent.putExtra("Lati", y);
                    intent.putExtra("Location",z);

                    ctx.startActivity(intent);
                }
            });



        }catch(Exception e){
            Log.e("CampListAdapter ", e.getMessage());
        }
    }

    @Override
    public int getItemCount(){

        return items.length();
    }

    class AGMViewHolder extends RecyclerView.ViewHolder {
        //class CampViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
        TextView tv;
        AGMListAdapter mAdapter;
        ImageView img;
        Button btn;

        public AGMViewHolder(View itemView, AGMListAdapter adapter) {
            super(itemView);
            // get the textview for later use
            tv = itemView.findViewById(R.id.word);
            mAdapter = adapter;
            img = itemView.findViewById(R.id.imgplay);
            btn = itemView.findViewById(R.id.location);

        }


        /* @Override
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
        } */

    }
}
