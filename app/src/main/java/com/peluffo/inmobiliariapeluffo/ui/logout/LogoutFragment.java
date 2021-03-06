package com.peluffo.inmobiliariapeluffo.ui.logout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.peluffo.inmobiliariapeluffo.Login;
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
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getActivity().onBackPressed();
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //borrar shared preference
                        SharedPreferences sp = c.getSharedPreferences("Usuarios", 0);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.clear();
                        //Error vuelve a abrir un fragment que hizo una peticion anterior
                        //android.os.Process.killProcess(android.os.Process.myPid()); //Su funcion mata la aplicación
                        Intent intent = new Intent(getActivity(), Login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }).show();
    }
}
