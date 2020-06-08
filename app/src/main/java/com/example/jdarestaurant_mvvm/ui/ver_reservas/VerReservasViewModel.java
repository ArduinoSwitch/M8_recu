package com.example.jdarestaurant_mvvm.ui.ver_reservas;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.jdarestaurant_mvvm.Model.ReservaModel;
import com.example.jdarestaurant_mvvm.room.Repository;
import com.google.firebase.database.core.Repo;

import java.util.List;

public class VerReservasViewModel extends ViewModel {
    LiveData<List<ReservaModel>> list;
    private Repository repository;

    public VerReservasViewModel() {
    }

    void setContext(Application application) {
        repository = new Repository(application);
        list = repository.getAllReservas();
        System.out.println(list.getValue());
    }
}