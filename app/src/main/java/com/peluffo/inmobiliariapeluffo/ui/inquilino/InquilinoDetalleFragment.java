package com.peluffo.inmobiliariapeluffo.ui.inquilino;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peluffo.inmobiliariapeluffo.databinding.FragmentInquilinoDetalleBinding;
import com.peluffo.inmobiliariapeluffo.modelo.Inquilino;

public class InquilinoDetalleFragment extends Fragment {
    private InquilinoDetalleViewModel inquilinoDetalleViewModel;
    private FragmentInquilinoDetalleBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inquilinoDetalleViewModel = new ViewModelProvider(this).get(InquilinoDetalleViewModel.class);
        binding = FragmentInquilinoDetalleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView tvCodigoI = binding.tvCodigoInquilino;
        final TextView tvNombreI = binding.tvNombreInquilino;
        final TextView tvApellidoI = binding.tvApellidoInquilino;
        final TextView tvDniI = binding.tvDniInquilino;
        final TextView tvMailI = binding.tvMailInquilino;
        final TextView tvTelI = binding.tvTelInquilino;
        final TextView tvGaranteI = binding.tvGaranteInquilino;
        final TextView tvTelG = binding.tvTelGarante;
        inquilinoDetalleViewModel.getInquilino().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {
                tvCodigoI.setText(inquilino.getIdInquilino() + "");
                tvNombreI.setText(inquilino.getNombre());
                tvApellidoI.setText(inquilino.getApellido());
                tvDniI.setText(inquilino.getDNI()+"");
                tvMailI.setText(inquilino.getEmail());
                tvTelI.setText(inquilino.getTelefono());
                tvGaranteI.setText(inquilino.getNombreGarante());
                tvTelG.setText(inquilino.getTelefonoGarante());
            }
        });
        inquilinoDetalleViewModel.cargar(getArguments());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}