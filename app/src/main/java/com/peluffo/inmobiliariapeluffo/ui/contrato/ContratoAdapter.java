package com.peluffo.inmobiliariapeluffo.ui.contrato;

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
import com.peluffo.inmobiliariapeluffo.request.ApiClient;

import java.util.List;

public class ContratoAdapter extends RecyclerView.Adapter<ContratoAdapter.ViewHolder> {
    private List<Inmueble> lista;
    private Context context;
    private LayoutInflater layoutInflater;

    public ContratoAdapter(List<Inmueble> lista, Context context, LayoutInflater layoutInflater) {
        this.lista = lista;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public ContratoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_contrato, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContratoAdapter.ViewHolder holder, int position) {
        ApiClient api = ApiClient.getApi();
        Inmueble inmueble = lista.get(position);
        Contrato contrato = api.obtenerContratoVigente(inmueble);
        holder.tvPrecioC.setText(String.valueOf(lista.get(position).getPrecio()));
        holder.tvDireccionC.setText(lista.get(position).getDireccion());
        Glide.with(context)
                .load(lista.get(position).getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivInmuebleC);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("contrato", contrato);
                Navigation.findNavController(view).navigate(R.id.contratoDetalleFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvDireccionC, tvPrecioC;
        private ImageView ivInmuebleC;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDireccionC = itemView.findViewById(R.id.tvDireccionContrato);
            tvPrecioC = itemView.findViewById(R.id.tvPrecioContrato);
            ivInmuebleC = itemView.findViewById(R.id.ivInmuebleContrato);
        }
    }
}
