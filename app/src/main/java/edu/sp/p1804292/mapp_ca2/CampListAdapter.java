package edu.sp.p1804292.mapp_ca2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class CampListAdapter extends RecyclerView.Adapter<CampListAdapter.CampViewHolder>{


    LayoutInflater inflater;
    Context ctx;
    ArrayList<CampItem> obj;

    public CampListAdapter(Context ctx, ArrayList<CampItem> obj){


        //get inflater for later use

        inflater=LayoutInflater.from(ctx);
        this.obj = obj;
        this.ctx=ctx;
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
    public void onBindViewHolder(@NonNull CampListAdapter.CampViewHolder holder, int position) {
        // retrieve data from array


        try {

            CampItem test = obj.get(position);


            holder.tv.setText(
                    test.getCname()
                            + "\n" + test.getCdesc()
                            + "\n\nDate: " + test.getCdate()
                            + "\nSign up here: " + test.getCregDate()
                            + "\nPrice: " + test.getCprice()
                            + "\nPayment Location: " + test.getCloc()
                            + "\nSign up here: " + test.getClink());


            //get number of item
            int num = getItemCount();

            for (int i = 0; i < num; i++) {
                if (position == i) {
                    // name of drawable
                    String p = "c" + (1 + i);
                    //get the id of drawable
                    int pic = ctx.getResources().getIdentifier(p, "drawable", ctx.getPackageName());
                    holder.img.setImageResource(pic);
                }
            }
        } catch (Exception e) {

        }


    }


    @Override
    public int getItemCount(){

        return obj.size();
    }

    class CampViewHolder extends RecyclerView.ViewHolder {
        //class CampViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
        TextView tv;
        CampListAdapter mAdapter;
        ImageView img;

        public CampViewHolder(View itemView, CampListAdapter adapter) {
            super(itemView);
            // get the textview for later use
            tv = itemView.findViewById(R.id.word);
            mAdapter = adapter;
            img = itemView.findViewById(R.id.imgplay);
            // set click listener
            //itemView.setOnClickListener(this);
        }




    }
}
