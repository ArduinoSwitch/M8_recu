package com.example.jdarestaurant_mvvm.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.jdarestaurant_mvvm.Model.ReservaModel;
import com.example.jdarestaurant_mvvm.Model.RoomDB;

import java.util.List;

public class Repository {

    private RegisterDao registerDao;
    private LiveData<List<ReservaModel>> mlist;

    public Repository(Application application) {
        RoomDB RoomDb = RoomDB.getDatabase(application.getApplicationContext());
        registerDao = RoomDb.registerDao();
        mlist = registerDao.getAllReservas();
    }

    public void inserReserva(ReservaModel reservaModel) {
        registerDao.insertRegister(reservaModel);
    }

    public LiveData<List<ReservaModel>> getAllReservas() {
        return mlist;
    }
}
