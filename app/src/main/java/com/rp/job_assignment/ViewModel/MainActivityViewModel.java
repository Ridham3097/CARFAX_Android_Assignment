package com.rp.job_assignment.ViewModel;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rp.job_assignment.Adapter.myAdapter;
import com.rp.job_assignment.Models.Car;
import com.rp.job_assignment.Repositories.CarRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<Car>> mCar;
    private CarRepository mRepo;
    public void init(Context context, LifecycleOwner owner){
        mRepo = CarRepository.getInstance();
        mCar = mRepo.getCars(context,owner);
    }
    public LiveData<List<Car>> getCars(){
        return mCar;
    }
}
