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
     @ColumnInfo(name = "exteriorColor") private String exColor;
     @ColumnInfo(name = "interiorColor") private String inColor;
     @ColumnInfo(name = "driveType") private String driveType;
     @ColumnInfo(name = "bodyStyle") private String bodyStyle;
     @ColumnInfo(name = "engine") private String engine;
     @ColumnInfo(name = "fuel") private String fuel;
     @ColumnInfo(name = "transmission") private String transmission;


     public String getExColor() {
          return exColor;
     }

     public void setExColor(String exColor) {
          this.exColor = exColor;
     }

     public String getInColor() {
          return inColor;
     }

     public void setInColor(String inColor) {
          this.inColor = inColor;
     }

     public String getDriveType() {
          return driveType;
     }

     public void setDriveType(String driveType) {
          this.driveType = driveType;
     }

     public String getBodyStyle() {
          return bodyStyle;
     }

     public void setBodyStyle(String bodyStyle) {
          this.bodyStyle = bodyStyle;
     }

     public String getEngine() {
          return engine;
     }

     public void setEngine(String engine) {
          this.engine = engine;
     }

     public String getFuel() {
          return fuel;
     }

     public void setFuel(String fuel) {
          this.fuel = fuel;
     }

     public String getTransmission() {
          return transmission;
     }

     public void setTransmission(String transmission) {
          this.transmission = transmission;
     }

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
