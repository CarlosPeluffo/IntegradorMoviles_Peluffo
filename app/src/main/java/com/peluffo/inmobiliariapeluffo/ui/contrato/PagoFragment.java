package com.peluffo.inmobiliariapeluffo.ui.contrato;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peluffo.inmobiliariapeluffo.databinding.FragmentPagoBinding;
import com.peluffo.inmobiliariapeluffo.modelo.Pago;

import java.util.List;

public class PagoFragment extends Fragment {
    private RecyclerView rvPago;
    private PagoAdapter pagoAdapter;
    private PagoViewModel pagoViewModel;
    private FragmentPagoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        pagoViewModel = new ViewModelProvider(this).get(PagoViewModel.class);
        binding = FragmentPagoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        rvPago = binding.rvPago;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1, LinearLayoutManager.VERTICAL, false);
        rvPago.setLayoutManager(gridLayoutManager);
        pagoViewModel.getPagosM().observe(getViewLifecycleOwner(), new Observer<List<Pago>>() {
            @Override
            public void onChanged(List<Pago> pagos) {
                pagoAdapter = new PagoAdapter(pagos, root.getContext(), inflater);
                rvPago.setAdapter(pagoAdapter);
            }
        });
        pagoViewModel.cargarPagos(getArguments());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}