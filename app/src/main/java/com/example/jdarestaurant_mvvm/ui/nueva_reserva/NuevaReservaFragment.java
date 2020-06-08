package com.example.jdarestaurant_mvvm.ui.nueva_reserva;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.jdarestaurant_mvvm.Model.ReservaModel;
import com.example.jdarestaurant_mvvm.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class NuevaReservaFragment extends Fragment {

    private NuevaReservaViewModel mViewModel;
    private EditText fecha, comensales, comentarios, nombre, telefono;
    private Button enviar_reserva;
    private DatabaseReference mDatabase;
    NavController navController;

    public static NuevaReservaFragment newInstance() {
        return new NuevaReservaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = ViewModelProviders.of(this).get(NuevaReservaViewModel.class);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("comentarios");

        mViewModel.setContext(getActivity().getApplication());

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hacer_reserva, container, false);

        fecha = view.findViewById(R.id.fechaEditText);
        comensales = view.findViewById(R.id.comensalesEditText);
        comentarios = view.findViewById(R.id.comentariosEditText);
        nombre = view.findViewById(R.id.nombreEditText);
        telefono = view.findViewById(R.id.telefonoEditText);
        enviar_reserva = view.findViewById(R.id.reservaButton);

        enviar_reserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numeroPersonas = 0;
                String fechaString = fecha.getText().toString();
                String personas = comensales.getText().toString();
                try {
                    numeroPersonas = Integer.parseInt(personas);
                } catch (Exception e){
                    e.printStackTrace();
                    numeroPersonas = 0;
                }
                String name = nombre.getText().toString();
                String phone = telefono.getText().toString();
                mViewModel.inserReserva(new ReservaModel(fechaString, numeroPersonas, name, phone));
                String coment = comentarios.getText().toString();
                writeNewComentario(coment);
                navController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment);
                navController.navigate(R.id.nav_slideshow);
            }
        });

        return view;
    }

    private void writeNewComentario(String comentario) {
        mDatabase.child("comentario").setValue(comentario);
    }
}
