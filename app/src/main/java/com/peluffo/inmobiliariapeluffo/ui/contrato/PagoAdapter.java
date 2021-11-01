package com.peluffo.inmobiliariapeluffo.ui.contrato;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.peluffo.inmobiliariapeluffo.R;
import com.peluffo.inmobiliariapeluffo.modelo.Convertfechas;
import com.peluffo.inmobiliariapeluffo.modelo.Pago;

import java.util.List;

public class PagoAdapter extends RecyclerView.Adapter<PagoAdapter.ViewHolder>{
    private List<Pago> lista;
    private Context context;
    private LayoutInflater layoutInflater;

    public PagoAdapter(List<Pago> lista, Context context, LayoutInflater layoutInflater) {
        this.lista = lista;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public PagoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_pago, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PagoAdapter.ViewHolder holder, int position) {
        Convertfechas convert = new Convertfechas();
        holder.tvCodigoP.setText(String.valueOf(lista.get(position).getId()));
        holder.tvNroPago.setText(String.valueOf(lista.get(position).getNroPago()));
        holder.tvCodigoCP.setText(String.valueOf(lista.get(position).getContrato().getId()));
        holder.tvImportP.setText(String.valueOf(lista.get(position).getMonto()));
        holder.tvfechaP.setText(convert.convertFecha(lista.get(position).getFechaPago()+""));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCodigoP, tvNroPago, tvCodigoCP, tvImportP, tvfechaP;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCodigoP = itemView.findViewById(R.id.tvCodigoPago);
            tvNroPago = itemView.findViewById(R.id.tvNroPago);
            tvCodigoCP = itemView.findViewById(R.id.tvCodigoCPago);
            tvImportP = itemView.findViewById(R.id.tvImportePago);
            tvfechaP = itemView.findViewById(R.id.tvFechaPago);
        }
    }
}
