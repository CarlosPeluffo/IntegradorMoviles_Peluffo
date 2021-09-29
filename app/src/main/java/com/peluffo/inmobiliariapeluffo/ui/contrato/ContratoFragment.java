package com.peluffo.inmobiliariapeluffo.ui.contrato;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.peluffo.inmobiliariapeluffo.databinding.FragmentContratoBinding;
import com.peluffo.inmobiliariapeluffo.modelo.Inmueble;

import java.util.List;

public class ContratoFragment extends Fragment {
    private RecyclerView rvContrato;
    private ContratoAdapter contratoAdapter;
    private ContratoViewModel contratoViewModel;
    private FragmentContratoBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contratoViewModel = new ViewModelProvider(this).get(ContratoViewModel.class);
        binding = FragmentContratoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        rvContrato = binding.rvContratos;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
        rvContrato.setLayoutManager(gridLayoutManager);
        contratoViewModel.getLista().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                contratoAdapter = new ContratoAdapter(inmuebles, root.getContext(), inflater);
                rvContrato.setAdapter(contratoAdapter);
            }
        });
        contratoViewModel.cargarInmueblesAlquilados();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
