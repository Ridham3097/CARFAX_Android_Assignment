package com.rp.job_assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rp.job_assignment.Adapter.myAdapter;
import com.rp.job_assignment.Database.CarDatabase;
import com.rp.job_assignment.Database.CarModel;
import com.rp.job_assignment.Network.ConnectionLivedata;
import com.rp.job_assignment.Network.ConnectionModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private RequestQueue mQueue;
    RecyclerView rcv;
    myAdapter adapter;
    ArrayList<Car> userArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectionLivedata connectionLiveData = new ConnectionLivedata(getApplicationContext());
        connectionLiveData.observe(this, new androidx.lifecycle.Observer<ConnectionModel>() {
            @Override
            public void onChanged(ConnectionModel connectionModel) {
                if (connectionModel.getIsConnected()) {
                    jsonParse();
                } else {
                    offlineData();
                }
            }
        });

        mQueue = Volley.newRequestQueue(this);
        rcv = (RecyclerView) findViewById(R.id.rcv);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        userArrayList = new ArrayList<Car>();
        adapter = new myAdapter(userArrayList, MainActivity.this);
        rcv.setAdapter(adapter);


    }



    private void offlineData() {
        CarDatabase db = CarDatabase.getDbInstance(getApplicationContext());

        List<CarModel> carList = db.carDao().getAllCars();
        for(int i =0;i<carList.size();i++){
            Car car = new Car();
            String url = carList.get(i).getUrl();
            String year = carList.get(i).getYear();
            String make = carList.get(i).getMake();
            String model = carList.get(i).getModel();
            String trim = carList.get(i).getTrim();
            String price = carList.get(i).getPrice();
            String mileage = carList.get(i).getMileage();
            String city = carList.get(i).getCity();
            String state =carList.get(i).getState();
            String phoneNumber = carList.get(i).getNumber();
            String id = carList.get(i).getUid();
            String exColor =carList.get(i).getExColor();
            String inColor =carList.get(i).getInColor();
            String transmission =carList.get(i).getTransmission();
            String driveType =carList.get(i).getDriveType();
            String bodyType =carList.get(i).getBodyStyle();
            String engine =carList.get(i).getEngine();
            String fuel =carList.get(i).getFuel();


            car.setUrl(url);
            car.setYear(year);
            car.setMake(make);
            car.setModel(model);
            car.setTrim(trim);
            car.setPrice(price);
            car.setMileage(mileage);
            car.setCity(city);
            car.setState(state);
            car.setPhoneNumber(phoneNumber);
            car.setExColor(exColor);
            car.setInColor(inColor);
            car.setTransmission(transmission);
            car.setDriveType(driveType);
            car.setBodyStyle(bodyType);
            car.setEngine(engine);
            car.setFuel(fuel);
            userArrayList.add(car);
        }
        adapter.notifyDataSetChanged();
    }


    private void jsonParse() {
        userArrayList.clear();
        String url = "https://carfax-for-consumers.firebaseio.com/assignment.json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("listings");



                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject carDetails = jsonArray.getJSONObject(i);

                                String url = carDetails.getJSONObject("images").getJSONObject("firstPhoto").getString("large");
                                String year = carDetails.getString("year");
                                String make = carDetails.getString("make");
                                String model = carDetails.getString("model");
                                String trim = carDetails.getString("trim");
                                String price = carDetails.getString("currentPrice");
                                String mileage = carDetails.getString("mileage");
                                String city = carDetails.getJSONObject("dealer").getString("address");
                                String state = carDetails.getJSONObject("dealer").getString("state");
                                String phoneNumber = carDetails.getJSONObject("dealer").getString("phone");
                                String exColor = carDetails.getString("exteriorColor");
                                String inColor = carDetails.getString("interiorColor");
                                String driveType = carDetails.getString("drivetype");
                                String transmission = carDetails.getString("transmission");
                                String bodyStyle = carDetails.getString("bodytype");
                                String engine = carDetails.getString("engine");
                                String fuel = carDetails.getString("fuel");

                                String id = carDetails.getString("id");
                                Car car = new Car();
                                    car.setUrl(url);
                                    car.setYear(year);
                                    car.setMake(make);
                                    car.setModel(model);
                                    car.setTrim(trim);
                                    car.setPrice(price);
                                    car.setMileage(mileage);
                                    car.setCity(city);
                                    car.setState(state);
                                    car.setExColor(exColor);
                                    car.setInColor(inColor);
                                    car.setDriveType(driveType);
                                    car.setTransmission(transmission);
                                    car.setBodyStyle(bodyStyle);
                                    car.setEngine(engine);
                                    car.setFuel(fuel);
                                    car.setPhoneNumber(phoneNumber);
                                    car.setId(id);
                                userArrayList.add(car);
                                CarDatabase db= CarDatabase.getDbInstance(getApplicationContext());
                                if(db.carDao().getID(id) != id) {
                                    addNewCar(url, year, make, model, trim, price, mileage, city, state, phoneNumber, id
                                              ,exColor,inColor,driveType,transmission,bodyStyle,engine,fuel);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }

    private void addNewCar(String url, String year, String make, String model, String trim, String price, String mileage, String city, String state, String phoneNumber, String id, String exColor, String inColor, String driveType, String transmission, String bodyStyle, String engine, String fuel) {
        CarDatabase db= CarDatabase.getDbInstance(this.getApplicationContext());

        CarModel car = new CarModel();
        car.setUrl(url);
        car.setYear(year);
        car.setMake(make);
        car.setModel(model);
        car.setTrim(trim);
        car.setPrice(price);
        car.setMileage(mileage);
        car.setCity(city);
        car.setState(state);
        car.setNumber(phoneNumber);
        car.setUid(id);
        car.setExColor(exColor);
        car.setInColor(inColor);
        car.setDriveType(driveType);
        car.setTransmission(transmission);
        car.setEngine(engine);
        car.setFuel(fuel);
        car.setBodyStyle(bodyStyle);
        db.carDao().insertCar(car);
    }


}