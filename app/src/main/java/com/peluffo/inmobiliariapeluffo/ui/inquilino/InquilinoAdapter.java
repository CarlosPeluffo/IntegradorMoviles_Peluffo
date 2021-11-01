package com.peluffo.inmobiliariapeluffo.ui.inquilino;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.peluffo.inmobiliariapeluffo.R;
import com.peluffo.inmobiliariapeluffo.modelo.Contrato;
import com.peluffo.inmobiliariapeluffo.modelo.Inmueble;
import com.peluffo.inmobiliariapeluffo.modelo.Inquilino;

import java.util.List;

public class InquilinoAdapter extends RecyclerView.Adapter<InquilinoAdapter.ViewHolder>{
    private List<Contrato> lista ;
    private Context context;
    private LayoutInflater layoutInflater;

    public InquilinoAdapter(List<Contrato> lista, Context context, LayoutInflater layoutInflater) {
        this.lista = lista;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public InquilinoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_inquilino, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InquilinoAdapter.ViewHolder holder, int position) { ;
        Contrato contrato = lista.get(position);
        Inmueble inmueble = contrato.getInmueble();
        Inquilino inquilino = contrato.getInquilino();
        holder.tvPrecioInquilino.setText(String.valueOf(inmueble.getPrecio()));
        holder.tvDireccionInquilino.setText(inmueble.getDireccion());
        Glide.with(context)
                .load("http://192.168.1.105:5001"+inmueble.getAvatar())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivInmuebleInquilino);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inquilino", inquilino);
                Navigation.findNavController(view).navigate(R.id.inquilinoDetalleFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvDireccionInquilino, tvPrecioInquilino;
        private ImageView ivInmuebleInquilino;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDireccionInquilino = itemView.findViewById(R.id.tvDireccionInquilino);
            tvPrecioInquilino = itemView.findViewById(R.id.tvPrecioInquilino);
            ivInmuebleInquilino = itemView.findViewById(R.id.ivInmuebleInquilino);
        }
    }
}
