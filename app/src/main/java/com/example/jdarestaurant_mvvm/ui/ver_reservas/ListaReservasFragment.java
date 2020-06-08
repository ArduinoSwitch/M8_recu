package com.example.jdarestaurant_mvvm.ui.ver_reservas;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jdarestaurant_mvvm.Model.Reserva;
import com.example.jdarestaurant_mvvm.Model.ReservaModel;
import com.example.jdarestaurant_mvvm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListaReservasFragment extends Fragment {

    private VerReservasViewModel verReservasViewModel;
    RecyclerView reservas_recycler;
    List<Reserva> reservas = new ArrayList<>();
    ReservaAdapter reservaAdapter;
    NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        verReservasViewModel =
                ViewModelProviders.of(this).get(VerReservasViewModel.class);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vista_reserva, container, false);

        verReservasViewModel.setContext(Objects.requireNonNull(getActivity()).getApplication());

        navController = Navigation.findNavController(Objects.requireNonNull(this.getActivity()), R.id.nav_host_fragment);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.nav_add_reserva);
            }
        });
        reservas_recycler = view.findViewById(R.id.reservas_Recycler);
        reservas_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        reservas_recycler.addItemDecoration(dividerItemDecoration);
        verReservasViewModel.list.observe(this, new Observer<List<ReservaModel>>() {
            @Override
            public void onChanged(List<ReservaModel> reservaModels) {
                System.out.println(reservaModels.size());
                reservaAdapter = new ReservaAdapter(reservaModels);
                reservas_recycler.setAdapter(reservaAdapter);
            }
        });
        return view;
    }


    public class ReservaViewHolder extends RecyclerView.ViewHolder{

        TextView fecha, personas;

        public ReservaViewHolder(@NonNull View itemView) {
            super(itemView);

            fecha = itemView.findViewById(R.id.fechaViewHolder);
            personas = itemView.findViewById(R.id.personasViewHolder);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }


    public class ReservaAdapter extends RecyclerView.Adapter<ReservaViewHolder> {

        List<ReservaModel> reservaList;

        public ReservaAdapter(List<ReservaModel> reservaList) {
            this.reservaList = reservaList;
        }

        @NonNull
        @Override
        public ReservaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View itemview = getLayoutInflater().inflate(R.layout.reserva_viewholder, viewGroup, false);
            return new ReservaViewHolder(itemview);
        }

        @Override
        public void onBindViewHolder(@NonNull ReservaViewHolder reservaViewHolder, int i) {
            reservaViewHolder.fecha.setText(reservaList.get(i).getFecha());
            reservaViewHolder.personas.setText(reservaList.get(i).getComensales()+"");
        }

        @Override
        public int getItemCount() {
            return reservaList.size();
        }
    }

}
