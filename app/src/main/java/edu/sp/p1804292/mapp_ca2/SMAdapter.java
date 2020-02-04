package edu.sp.p1804292.mapp_ca2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class SMAdapter extends RecyclerView.Adapter<PlaceViewHolder> {

    private Context context;
    private String[] schoolName;
    private String[] smLinks;


    SMAdapter(Context context, String[] schoolName, String[] smLinks){
        this.context = context;
        this.schoolName = schoolName;
        this.smLinks = smLinks;

    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.socialmedia_item, parent, false);
        return new PlaceViewHolder(view);

    }



    @Override
    public void onBindViewHolder(final PlaceViewHolder holder, int position) {

        int num = getItemCount();
        for (int i =0; i<num; i++) {
            if (position==i){
                String x = "icon"+(1+i);
                int pic = context.getResources().getIdentifier(x, "drawable", context.getPackageName());
                holder.xx.setImageResource(pic);
            }

        }


        holder.schoolName.setText(schoolName[position]);
        holder.visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(smLinks[holder.getAdapterPosition()]));
                context.startActivity(intent);
            }


        });
    }

    @Override
    public int getItemCount() {
        return schoolName.length;
    }
}

class PlaceViewHolder extends RecyclerView.ViewHolder {

    TextView schoolName ;
    Button visit;
    ImageView xx;

    PlaceViewHolder(View itemView) {
        super(itemView);

        xx = itemView.findViewById(R.id.SchoolImg);
        schoolName = itemView.findViewById(R.id.PageName);
        visit = itemView.findViewById(R.id.btnVisit);
    }
}

