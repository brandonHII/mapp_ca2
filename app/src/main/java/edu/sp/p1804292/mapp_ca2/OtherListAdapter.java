package edu.sp.p1804292.mapp_ca2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class OtherListAdapter extends RecyclerView.Adapter<OtherListAdapter.OtherViewHolder>{


    LayoutInflater inflater;
    Context ctx;
    ArrayList<OtherItem> obj;



public OtherListAdapter(Context ctx, ArrayList<OtherItem> obj){

        //get inflater for later use
        inflater=LayoutInflater.from(ctx);
        this.obj = obj;
        this.ctx=ctx;
        }


@NonNull
@Override
public OtherListAdapter.OtherViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
        // inflate the xml layout for single row item
        View itemView=inflater.inflate(R.layout.others_items,parent,false);
        // create view holder and return it
        return new OtherListAdapter.OtherViewHolder(itemView,this);

        }

@Override
public void onBindViewHolder(@NonNull OtherListAdapter.OtherViewHolder holder,int position){
        // retrieve data from array

        try{

            OtherItem test = obj.get(position);


        holder.tv.setText(
                test.getCname()
                        +"\n"+test.getCdate()
                        +"\n\nDate: "+test.getCdesc()
                        +"\nSign up here: "+test.getClink());



        }catch(Exception e){

        }
        }

    @Override
    public int getItemCount(){

            return obj.size();
            }

    class OtherViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        OtherListAdapter mAdapter;


        public OtherViewHolder(View itemView, OtherListAdapter adapter) {
            super(itemView);

            tv = itemView.findViewById(R.id.word);
            mAdapter = adapter;

        }
}
}
