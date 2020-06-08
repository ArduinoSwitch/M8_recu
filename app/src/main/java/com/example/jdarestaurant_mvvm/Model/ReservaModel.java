package com.example.jdarestaurant_mvvm.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reservas")
public class ReservaModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String fecha;
    private int comensales;
    private String nombre;
    private String telefono;

    public ReservaModel() {}

    public ReservaModel(String fecha, int comensales, String nombre, String telefono) {
        this.comensales = comensales;
        this.fecha = fecha;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getComensales() {
        return comensales;
    }

    public void setComensales(int comensales) {
        this.comensales = comensales;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
