package com.rp.job_assignment.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CarDao {

    @Query("SELECT * FROM carmodel")
    List<CarModel> getAllCars();

    @Query("SELECT uniqueID FROM Carmodel WHERE uniqueID= :num")
    String getID(String num);

    @Insert
    void insertCar(CarModel... carModels);

    @Query("DELETE  FROM CarModel")
    void deleteAll();

}
