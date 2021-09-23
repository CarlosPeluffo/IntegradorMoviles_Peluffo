package com.peluffo.inmobiliariapeluffo.ui.inmueble;

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
import com.peluffo.inmobiliariapeluffo.modelo.Inmueble;

import java.util.List;

public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.ViewHolder> {
    private List<Inmueble> lista ;
    private Context context;
    private LayoutInflater layoutInflater;

    public InmuebleAdapter(List<Inmueble> lista, Context context, LayoutInflater layoutInflater) {
        this.lista = lista;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public InmuebleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = layoutInflater.inflate(R.layout.item_inmueble, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull InmuebleAdapter.ViewHolder holder, int position) {
        Inmueble inmueble = lista.get(position);
        holder.tvPrecio.setText(String.valueOf(lista.get(position).getPrecio()));
        holder.tvDireccion.setText(lista.get(position).getDireccion());
        Glide.with(context)
                .load(lista.get(position).getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivInmueble);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble", inmueble );
                Navigation.findNavController(view).navigate(R.id.inmuebleDetalleFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvDireccion, tvPrecio;
        private ImageView ivInmueble;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            ivInmueble = itemView.findViewById(R.id.ivInmueble);
        }
    }
}
