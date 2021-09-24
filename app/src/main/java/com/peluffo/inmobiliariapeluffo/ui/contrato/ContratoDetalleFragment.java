package com.peluffo.inmobiliariapeluffo.ui.contrato;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.peluffo.inmobiliariapeluffo.R;
import com.peluffo.inmobiliariapeluffo.databinding.FragmentContratoDetalleBinding;
import com.peluffo.inmobiliariapeluffo.modelo.Contrato;

public class ContratoDetalleFragment extends Fragment {
    private ContratoDetalleViewModel contratoDetalleViewModel;
    private FragmentContratoDetalleBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        contratoDetalleViewModel = new ViewModelProvider(this).get(ContratoDetalleViewModel.class);
        binding = FragmentContratoDetalleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView tvCodigoContrato = binding.tvCodigoContrato;
        final TextView tvInicioContrato = binding.tvInicioContrato;
        final TextView tvFinCOntrato = binding.tvFinContrato;
        final TextView tvMontoContrato = binding.tvMontoContrato;
        final TextView tvInquilinoContrato = binding.tvInquilinoContrato;
        final TextView tvInmuebleContrato = binding.tvInmuebleContrato;
        final Button btPagos = binding.btPagos;
        contratoDetalleViewModel.getContratoM().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {
                tvCodigoContrato.setText(contrato.getIdContrato() + "");
                tvInicioContrato.setText(contrato.getFechaInicio());
                tvFinCOntrato.setText(contrato.getFechaFin());
                tvMontoContrato.setText("$ " + contrato.getMontoAlquiler());
                tvInquilinoContrato.setText(contrato.getInquilino().getNombreGarante()+" "+ contrato.getInquilino().getApellido());
                tvInmuebleContrato.setText("Inmueble en" + contrato.getInmueble().getDireccion());
                btPagos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("pagos", contrato);
                        Navigation.findNavController(view).navigate(R.id.pagoFragment, bundle);
                    }
                });
            }
        });
        contratoDetalleViewModel.cargar(getArguments());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}