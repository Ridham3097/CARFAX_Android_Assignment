package com.rp.job_assignment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.rp.job_assignment.Models.Car;
import com.rp.job_assignment.DetailsActivity;
import com.rp.job_assignment.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myViewHolder> {

    List<Car> data;
    Context context;

    public myAdapter(List<Car> data, Context context) {
        this.data = data;
        this.context =context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.singlerow_car,parent,false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        final Car temp = data.get(position);

        holder.t1.setText(temp.getYear());
        holder.t2.setText(temp.getMake());
        holder.t3.setText(temp.getModel());
        holder.price.setText(temp.getPrice());
        holder.trim.setText(temp.getTrim());
        holder.mileage.setText(temp.getMileage());
        holder.city.setText(temp.getCity());
        holder.state.setText(temp.getState());

        holder.phoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    String tel = "tel:" + temp.getPhoneNumber();
                    callIntent.setData(Uri.parse(tel));
                    context.startActivity(callIntent);
            }
        });

        Picasso.get().load(temp.getUrl())
                .into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("url", temp.getUrl() );
                intent.putExtra("year",temp.getYear());
                intent.putExtra("make",temp.getMake());
                intent.putExtra("model",temp.getModel());
                intent.putExtra("trim",temp.getTrim());
                intent.putExtra("price",temp.getPrice());
                intent.putExtra("city",temp.getCity());
                intent.putExtra("state",temp.getState());
                intent.putExtra("mileage",temp.getMileage());
                intent.putExtra("exColor",temp.getExColor());
                intent.putExtra("intColor",temp.getInColor());
                intent.putExtra("driveType",temp.getDriveType());
                intent.putExtra("transmission",temp.getTransmission());
                intent.putExtra("bodyStyle",temp.getBodyStyle());
                intent.putExtra("engine",temp.getEngine());
                intent.putExtra("fuel",temp.getFuel());
                intent.putExtra("phoneNumber",temp.getPhoneNumber());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

class myViewHolder extends RecyclerView.ViewHolder {

    TextView t1;
    TextView t2 ;
    TextView t3;
    TextView price;
    TextView mileage;
    TextView trim;
    TextView city;
    TextView state;
    ImageView img;
    Button phoneCall;
    public myViewHolder(@NonNull View itemView) {
        super(itemView);

        t1 = (TextView) itemView.findViewById(R.id.year);
        t2 = itemView.findViewById(R.id.make);
        t3 = itemView.findViewById(R.id.model);
        price = itemView.findViewById(R.id.price);
        mileage = itemView.findViewById(R.id.mileage);
        trim = itemView.findViewById(R.id.trim);
        city = itemView.findViewById(R.id.city);
        state = itemView.findViewById(R.id.state);
        img = itemView.findViewById(R.id.img);
        phoneCall = itemView.findViewById(R.id.phone);

    }
}

