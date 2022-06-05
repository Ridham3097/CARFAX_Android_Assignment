package com.rp.job_assignment.Repositories;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CarRepository  {

    private RequestQueue mQueue;
    static  CarRepository instance;
    private List<Car> dataset = new ArrayList<Car>();
    private List<Car> data = new ArrayList<Car>();
    private Context context;

    public static CarRepository getInstance(){
        if(instance == null){
            instance = new CarRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Car>> getCars(Context context, LifecycleOwner owner){
        this.context =context;

        ConnectionLivedata connectionLiveData = new ConnectionLivedata(context);
        connectionLiveData.observe(owner, new Observer<ConnectionModel>() {
            @Override
            public void onChanged(ConnectionModel connectionModel) {
                if (connectionModel.getIsConnected()) {

                    setOnlineData(context);
                    Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
                } else if(!connectionModel.getIsConnected()){
                    setOfflineData(context);
                    Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        MutableLiveData<List<Car>> data = new MutableLiveData<>();
        data.setValue(dataset);
        return data;

    }

    private void setOfflineData(Context context){
        CarDatabase db = CarDatabase.getDbInstance(context);

        List<CarModel> carList = db.carDao().getAllCars();
        for(int i =0;i<carList.size();i++){

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
            car.setPhoneNumber(phoneNumber);
            car.setExColor(exColor);
            car.setInColor(inColor);
            car.setTransmission(transmission);
            car.setDriveType(driveType);
            car.setBodyStyle(bodyType);
            car.setEngine(engine);
            car.setFuel(fuel);

            dataset.add(car);
        }
        Toast.makeText(context, String.valueOf(dataset.size()), Toast.LENGTH_SHORT).show();
    }
    private void setOnlineData(Context context){
        Car c = new Car();
        dataset.add(c);
        mQueue = Volley.newRequestQueue(context);
        mQueue.getCache().clear();
        String url = "https://carfax-for-consumers.firebaseio.com/assignment.json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("listings");

                            Toast.makeText(context, "Start : " + String.valueOf(dataset.size()), Toast.LENGTH_SHORT).show();
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

                                dataset.add(car);


                                CarDatabase db= CarDatabase.getDbInstance(context);
                                if(db.carDao().getID(id) != id) {
                                    addNewCar(url, year, make, model, trim, price, mileage, city, state, phoneNumber, id
                                            ,exColor,inColor,driveType,transmission,bodyStyle,engine,fuel);
                                }
                            }

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
        CarDatabase db= CarDatabase.getDbInstance(context);

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
