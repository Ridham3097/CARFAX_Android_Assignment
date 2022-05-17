package com.rp.job_assignment.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CarModel.class}, version =  1)
public abstract  class CarDatabase  extends RoomDatabase {
    public abstract CarDao carDao();
    private static CarDatabase INSTANCE;

    public static CarDatabase getDbInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),CarDatabase.class,"Cars")
                    .allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

}
