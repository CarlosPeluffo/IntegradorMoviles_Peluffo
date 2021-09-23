package com.peluffo.inmobiliariapeluffo.ui.inmueble;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.peluffo.inmobiliariapeluffo.databinding.FragmentInmuebleDetalleBinding;
import com.peluffo.inmobiliariapeluffo.modelo.Inmueble;

public class InmuebleDetalleFragment extends Fragment {
    private InmuebleDetalleViewModel inmuebleDetalleViewModel;
    private FragmentInmuebleDetalleBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInmuebleDetalleBinding.inflate(inflater, container, false);
        inmuebleDetalleViewModel = new ViewModelProvider(this).get(InmuebleDetalleViewModel.class);
        View root = binding.getRoot();
        final TextView tvCodigo = binding.tvCodigoInmueble;
        final TextView tvAmbientes = binding.tvAmbiestesInmueble;
        final TextView tvDireccion = binding.tvDireccionInmueble;
        final TextView tvPrecio = binding.tvPrecioInmueble;
        final TextView tvUso = binding.tvUsoInmueble;
        final TextView tvTipo = binding.tvTipo;
        final CheckBox cbEstado = binding.cbEstadoInmueble;
        final ImageView imageInmueble = binding.ivFotoInmueble;
        inmuebleDetalleViewModel.getInmuebleM().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {
                tvCodigo.setText(inmueble.getIdInmueble() + "");
                tvAmbientes.setText(inmueble.getAmbientes() + "");
                tvDireccion.setText(inmueble.getDireccion());
                tvPrecio.setText(String.valueOf(inmueble.getPrecio()));
                tvUso.setText(inmueble.getUso());
                tvTipo.setText(inmueble.getTipo());
                cbEstado.setChecked(inmueble.isEstado());
                Glide.with(getContext())
                        .load(inmueble.getImagen())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageInmueble);
                cbEstado.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        inmuebleDetalleViewModel.guardarEstado(cbEstado.isChecked());
                    }
                });
            }
        });
        inmuebleDetalleViewModel.cargar(getArguments());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
