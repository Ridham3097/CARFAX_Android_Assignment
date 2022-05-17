package com.rp.job_assignment.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CarModel {
     @PrimaryKey(autoGenerate = true) private int id;
     @ColumnInfo(name = "url") private String url;
     @ColumnInfo(name = "year") private String year;
     @ColumnInfo(name = "make") private String make;
     @ColumnInfo(name = "model") private String model;
     @ColumnInfo(name = "trim") private String trim;
     @ColumnInfo(name = "price") private String price;
     @ColumnInfo(name = "mileage") private String mileage;
     @ColumnInfo(name = "city") private String city;
     @ColumnInfo(name = "state") private String state;
     @ColumnInfo(name = "number") private String number;
     @ColumnInfo(name = "uniqueID") private String uid;

     public String getUid() {
          return uid;
     }

     public void setUid(String uid) {
          this.uid = uid;
     }

     public int getId() {
          return id;
     }

     public void setId(int id) {
          this.id = id;
     }

     public String getUrl() {
          return url;
     }

     public void setUrl(String url) {
          this.url = url;
     }

     public String getYear() {
          return year;
     }

     public void setYear(String year) {
          this.year = year;
     }

     public String getMake() {
          return make;
     }

     public void setMake(String make) {
          this.make = make;
     }

     public String getModel() {
          return model;
     }

     public void setModel(String model) {
          this.model = model;
     }

     public String getTrim() {
          return trim;
     }

     public void setTrim(String trim) {
          this.trim = trim;
     }

     public String getPrice() {
          return price;
     }

     public void setPrice(String price) {
          this.price = price;
     }

     public String getMileage() {
          return mileage;
     }

     public void setMileage(String mileage) {
          this.mileage = mileage;
     }

     public String getCity() {
          return city;
     }

     public void setCity(String city) {
          this.city = city;
     }

     public String getState() {
          return state;
     }

     public void setState(String state) {
          this.state = state;
     }

     public String getNumber() {
          return number;
     }

     public void setNumber(String number) {
          this.number = number;
     }
}
