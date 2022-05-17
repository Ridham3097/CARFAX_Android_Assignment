package com.rp.job_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    TextView year,make,model,trim,price,mileage,location,exColor,inColor,driveType,transmission,bodyStyle,fuel,engine,phoneNumber;
    ImageView img;
    Button phoneCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        img = findViewById(R.id.img);
        location = findViewById(R.id.location);
        year = findViewById(R.id.txtYear);
        make = findViewById(R.id.txtMake);
        model = findViewById(R.id.txtModel);
        trim = findViewById(R.id.txtTrim);
        price = findViewById(R.id.txtPrice);
        mileage = findViewById(R.id.txtMileage);
        exColor = findViewById(R.id.exteriorColor);
        inColor = findViewById(R.id.interiorColor);
        driveType = findViewById(R.id.driveType);
        transmission = findViewById(R.id.transmission);
        bodyStyle = findViewById(R.id.bodyStyle);
        fuel = findViewById(R.id.fuel);
        engine = findViewById(R.id.engine);
        phoneCall = findViewById(R.id.call);

        year.setText(getIntent().getStringExtra("year"));
        make.setText(getIntent().getStringExtra("make"));
        model.setText(getIntent().getStringExtra("model"));
        trim.setText(getIntent().getStringExtra("trim"));
        price.setText(getIntent().getStringExtra("price"));
        mileage.setText(getIntent().getStringExtra("mileage"));
        exColor.setText(getIntent().getStringExtra("exColor"));
        inColor.setText(getIntent().getStringExtra("intColor"));
        driveType.setText(getIntent().getStringExtra("driveType"));
        transmission.setText(getIntent().getStringExtra("transmission"));
        bodyStyle.setText(getIntent().getStringExtra("bodyStyle"));
        fuel.setText(getIntent().getStringExtra("fuel"));
        engine.setText(getIntent().getStringExtra("engine"));

        Picasso.get().load(getIntent().getStringExtra("url"))
                .into(img);

        phoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                String tel = "tel:" + getIntent().getStringExtra("phoneNumber");
                callIntent.setData(Uri.parse(tel));
                startActivity(callIntent);
            }
        });



    }
}