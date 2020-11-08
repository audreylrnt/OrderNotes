package com.example.ordernotes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterRV extends RecyclerView.Adapter<AdapterRV.ViewHolder> {
    Context context;

    public AdapterRV(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.notes_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        NotesViewedAtRV notesViewedAtRV = NotesDataDB.notesData.get(position);
        String platform = notesViewedAtRV.getPlatform();
        String invoiceNo = notesViewedAtRV.getInvoiceNo();
        String namaPembeli = notesViewedAtRV.getNamaPembeli();
        String namaBarang = notesViewedAtRV.getNamaBarang();
        String tglOrder = notesViewedAtRV.getTglOrder();
        String status = notesViewedAtRV.getStatus();

        holder.platform.setText(platform);
        holder.tglOrder.setText(tglOrder);
        holder.invoiceNo.setText(invoiceNo);
        holder.namaPembeli.setText(namaPembeli);
        holder.namaBarang.setText(namaBarang);
        if(status.equals("Lunas")){
            holder.invoiceNo.setTextColor(Color.parseColor("#1e90ff"));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toEdit = new Intent(context, EditNotes.class);
                String id = holder.invoiceNo.getText().toString();
                toEdit.putExtra("leID", id);
                context.startActivity(toEdit);
                Log.d("Adapter", "id: " + id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return NotesDataDB.notesData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView platform, invoiceNo, namaPembeli, namaBarang, tglOrder;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            platform = itemView.findViewById(R.id.notesPlatform);
            invoiceNo = itemView.findViewById(R.id.notesNoInvoice);
            namaPembeli = itemView.findViewById(R.id.notesNamaPembeli);
            namaBarang = itemView.findViewById(R.id.notesBarang);
            tglOrder = itemView.findViewById(R.id.notesTglOrder);
        }
    }
}
