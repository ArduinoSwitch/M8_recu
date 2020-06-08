package com.example.jdarestaurant_mvvm.ui.nueva_reserva;

import android.app.Application;
import androidx.lifecycle.ViewModel;

import com.example.jdarestaurant_mvvm.Model.ReservaModel;
import com.example.jdarestaurant_mvvm.room.Repository;

public class NuevaReservaViewModel extends ViewModel {

    private Repository repository;

    public void setContext(Application application){
        repository = new Repository(application);
    }

    public void inserReserva(final ReservaModel reservaModel){
        new Thread(){
            public void run(){
                repository.inserReserva(reservaModel);
            }
        }.start();
    }
}
