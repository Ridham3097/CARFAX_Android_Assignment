package com.rp.job_assignment;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rp.job_assignment.Adapter.myAdapter;
import com.rp.job_assignment.Database.CarDatabase;
import com.rp.job_assignment.Database.CarModel;
import com.rp.job_assignment.Models.Car;
import com.rp.job_assignment.Network.ConnectionLivedata;
import com.rp.job_assignment.Network.ConnectionModel;
import com.rp.job_assignment.ViewModel.MainActivityViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    RecyclerView rcv;
    myAdapter adapter;
    private MainActivityViewModel mMainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mMainActivityViewModel.init(getApplicationContext(),this);
        mMainActivityViewModel.getCars().observe(this, new Observer<List<Car>>() {
            @Override
            public void onChanged(List<Car> cars)
            {
                    adapter.notifyDataSetChanged();
            }
        });

        initRecyclerView();

    }
    private void initRecyclerView() {
        rcv = (RecyclerView) findViewById(R.id.rcv);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new myAdapter(mMainActivityViewModel.getCars().getValue(), MainActivity.this);
        rcv.setAdapter(adapter); }
}