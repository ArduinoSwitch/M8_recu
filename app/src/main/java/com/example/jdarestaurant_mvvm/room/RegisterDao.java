package com.example.jdarestaurant_mvvm.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.jdarestaurant_mvvm.Model.ReservaModel;

import java.util.List;

@Dao
public interface RegisterDao {

    @Insert
    void insertRegister(ReservaModel register);

    @Query("SELECT * from reservas")
    LiveData<List<ReservaModel>> getAllReservas();
}