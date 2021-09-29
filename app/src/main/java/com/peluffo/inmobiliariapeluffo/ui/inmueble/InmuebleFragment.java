package com.peluffo.inmobiliariapeluffo.ui.inmueble;

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
import androidx.recyclerview.widget.RecyclerView;

import com.peluffo.inmobiliariapeluffo.databinding.FragmentInmuebleBinding;
import com.peluffo.inmobiliariapeluffo.modelo.Inmueble;

import java.util.List;

public class InmuebleFragment extends Fragment{
    private RecyclerView rvInmueble;
    private InmuebleAdapter inmuebleAdapter;
    private InmuebleViewModel inmuebleViewModel;
    private FragmentInmuebleBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        inmuebleViewModel = new ViewModelProvider(this).get(InmuebleViewModel.class);
        binding = FragmentInmuebleBinding.inflate(inflater, container,false);
        View root = binding.getRoot();
        rvInmueble = binding.rvInmuebles;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        rvInmueble.setLayoutManager(gridLayoutManager);
        inmuebleViewModel.getLista().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                inmuebleAdapter = new InmuebleAdapter(inmuebles, root.getContext(), inflater);
                rvInmueble.setAdapter(inmuebleAdapter);
            }
        });
        inmuebleViewModel.cargarInmueble();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
