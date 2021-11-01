package com.peluffo.inmobiliariapeluffo.ui.inmueble;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.peluffo.inmobiliariapeluffo.MainActivity;
import com.peluffo.inmobiliariapeluffo.databinding.FragmentInmuebleCrearBinding;
import com.peluffo.inmobiliariapeluffo.modelo.Inmueble;

import java.io.IOException;
import java.util.Base64;

public class InmuebleCrearFragment extends Fragment {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int COD_SELECCIONA = 10;
    private Bitmap bitmap;
    private Inmueble inmueble;
    private InmuebleCrearViewModel inmuebleCrearViewModel;
    private FragmentInmuebleCrearBinding binding;

    public static InmuebleCrearFragment newInstance() {
        return new InmuebleCrearFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInmuebleCrearBinding.inflate(inflater, container, false);
        inmuebleCrearViewModel = new ViewModelProvider(this).get(InmuebleCrearViewModel.class);
        inmueble = new Inmueble();
        guardaImagen();
        View root = binding.getRoot();
        final EditText etDireccion = binding.etDireccionInm;
        final EditText etUso = binding.etUsoInm;
        final EditText etTipo = binding.etTipoInm;
        final EditText etAmbientes = binding.etAmbientesInm;
        final EditText etPrecio = binding.etPrecioInm;
        final CheckBox cbEstado = binding.cbEstadoInm;
        final Button btCargarFoto = binding.btFotoInm;
        final Button btCrear = binding.btCrearInm;
        inmuebleCrearViewModel.getInmuebleM().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmuebleG) {
                //queria mostrarlo en detalles pero ocurre un error al querer volver hacia atrás :c
               /* Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble", inmuebleG);
                Navigation.findNavController(root).navigate(R.id.inmuebleDetalleFragment, bundle);*/
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
            }
        });
        btCargarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDialogOpciones();
            }
        });
        btCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inmueble.setId(0);
                inmueble.setDireccion(etDireccion.getText().toString());
                inmueble.setUso(etUso.getText().toString());
                inmueble.setTipo(etTipo.getText().toString());
                inmueble.setAmbientes(Integer.parseInt(etAmbientes.getText().toString()));
                inmueble.setPrecio(Double.parseDouble(etPrecio.getText().toString()));
                inmueble.setEstado(cbEstado.isChecked());
                inmueble.setPropietarioId(0);

                inmuebleCrearViewModel.crearInmueble(inmueble);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void guardaImagen(){
        inmuebleCrearViewModel.getByteM().observe(getViewLifecycleOwner(), new Observer<byte[]>() {
            @Override
            public void onChanged(byte[] bytes) {
                String nube = Base64.getEncoder().encodeToString(bytes);
                inmueble.setAvatarFile(nube);
            }
        });
    }
    private void mostrarDialogOpciones(){
        final CharSequence[] opciones={"Tomar foto", "Elegir de Galeria", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Elige una opción");
        builder.setItems(opciones, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                if(opciones[i].equals("Tomar foto")){
                    Intent tomarFotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if(tomarFotoIntent.resolveActivity(getActivity().getPackageManager()) != null){
                        startActivityForResult(tomarFotoIntent, REQUEST_IMAGE_CAPTURE);
                    }
                }else{
                    if(opciones[i].equals("Elegir de Galeria")){ //ACTION_PEAK
                        Intent intent = new Intent(Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        if(intent.resolveActivity(getActivity().getPackageManager()) != null){
                            startActivityForResult(intent, COD_SELECCIONA);
                        }
                    }else{
                        dialogInterface.dismiss();
                    }
                }
            }
        });
        builder.show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        final ImageView ivAvatar = binding.ivAvatarInm;
        switch(requestCode){
            case COD_SELECCIONA:
                Uri path=data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), path);
                    ivAvatar.setImageBitmap(bitmap); //imageView
                    inmuebleCrearViewModel.cargarImagen(requestCode, resultCode, bitmap, COD_SELECCIONA);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case REQUEST_IMAGE_CAPTURE:
                try {
                    Bundle bundle = data.getExtras();
                    bitmap = (Bitmap) bundle.get("data");
                    ivAvatar.setImageBitmap(bitmap); //imageView
                    inmuebleCrearViewModel.cargarImagen(requestCode, resultCode, bitmap, REQUEST_IMAGE_CAPTURE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }
}