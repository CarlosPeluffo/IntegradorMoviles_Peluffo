package com.peluffo.inmobiliariapeluffo.ui.perfil;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.peluffo.inmobiliariapeluffo.databinding.FragmentPerfilBinding;
import com.peluffo.inmobiliariapeluffo.modelo.Propietario;

public class PerfilFragment extends Fragment {
    private Button btEditar;
    private PerfilViewModel perfilViewModel;
    private FragmentPerfilBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textPerfil;
        final EditText etCodigo = binding.etCodigo;
        final EditText etDni = binding.etDni;
        final EditText etNombre = binding.etNombre;
        final EditText etApellido = binding.etApellido;
        final EditText etTelefono = binding.etTelefono;
        final EditText etEmail = binding.etEMail;
        final EditText etContra = binding.etContra;
        final ImageView ivAvatar = binding.ivAvatarProp;
        final TextView tvAvatar = binding.tvAvatar;

        editar();
        perfilViewModel.getmText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });
        perfilViewModel.getEstadoM().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                etNombre.setEnabled(aBoolean);
                etApellido.setEnabled(aBoolean);
                etTelefono.setEnabled(aBoolean);
            }
        });
        perfilViewModel.getPropietarioM().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @SuppressLint("ResourceType")
            @Override
            public void onChanged(Propietario propietario) {
                etCodigo.setText(String.format(String.valueOf(propietario.getId())));
                etDni.setText(propietario.getDni());
                etNombre.setText(propietario.getNombre());
                etApellido.setText(propietario.getApellido());
                etTelefono.setText(propietario.getTelefono());
                etEmail.setText(propietario.getMail());
                etContra.setText(propietario.getClave());
                Glide.with(root.getContext())
                        .load("http://192.168.1.105:5001"+propietario.getAvatar())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(ivAvatar);
                tvAvatar.setText(propietario.getAvatar() + "");
            }
        });
        perfilViewModel.getTextB().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                btEditar.setText(s);
            }
        });
        perfilViewModel.cargarProp();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void editar(){
        btEditar = binding.btEditar;
        final EditText etCodigo = binding.etCodigo;
        final EditText etDni = binding.etDni;
        final EditText etNombre = binding.etNombre;
        final EditText etApellido = binding.etApellido;
        final EditText etTelefono = binding.etTelefono;
        final EditText etEmail = binding.etEMail;
        final EditText etContra = binding.etContra;
        final TextView tvAvatar = binding.tvAvatar;
        btEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String texto = ((Button)view).getText().toString();
                    Propietario p = new Propietario(
                            Integer.parseInt(etCodigo.getText().toString()),
                            etApellido.getText().toString(),
                            etNombre.getText().toString(),
                            etDni.getText().toString(),
                            etEmail.getText().toString(),
                            etTelefono.getText().toString(),
                            etContra.getText().toString(),
                            //Integer.parseInt( tvAvatar.getText().toString())
                            tvAvatar.getText().toString()

                    );
                    perfilViewModel.cambiarEstado(texto, p);
                }
        });
    }
}
