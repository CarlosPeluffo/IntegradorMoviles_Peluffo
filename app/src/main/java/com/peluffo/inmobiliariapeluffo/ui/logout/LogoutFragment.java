package com.peluffo.inmobiliariapeluffo.ui.logout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.peluffo.inmobiliariapeluffo.R;
import com.peluffo.inmobiliariapeluffo.databinding.FragmentLogoutBinding;

public class LogoutFragment extends Fragment {
    private FragmentLogoutBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLogoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        cerrarAplicacion(root.getContext());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void cerrarAplicacion(Context c) {
        new AlertDialog.Builder(c)
                .setIcon(R.drawable.ic_logout)
                .setTitle("¿Realmente desea cerrar sesión?")
                .setCancelable(false)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {// un listener que al pulsar, cierre la aplicacion
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        android.os.Process.killProcess(android.os.Process.myPid()); //Su funcion mata la aplicación
                        //finish(); Si solo quiere mandar la aplicación a segundo plano
                    }
                }).show();
    }
}
