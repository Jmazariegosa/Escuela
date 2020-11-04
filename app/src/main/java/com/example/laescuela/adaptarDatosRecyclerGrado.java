package com.example.laescuela;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laescuela.Models.alumnoT;
import com.example.laescuela.Models.gradoT;

import java.util.ArrayList;

public class adaptarDatosRecyclerGrado extends RecyclerView.Adapter<adaptarDatosRecyclerGrado.ViewHolderDatos> {


    ArrayList<gradoT> listGrado;

    public adaptarDatosRecyclerGrado(ArrayList<gradoT>listUsuario){

        this.listGrado = listUsuario;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.personalizado_grado,null, false);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {

        holder.idGrado.setText(listGrado.get(position).getId_grado().toString());
        holder.grado.setText(listGrado.get(position).getGrado());


    }

    @Override
    public int getItemCount() {

        return listGrado.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView idGrado, grado;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            idGrado = (TextView) itemView.findViewById(R.id.id_id);
            grado = (TextView) itemView.findViewById(R.id.id_gradotxt);
        }

    }

    public void filtrar(ArrayList<gradoT> filtroGrado){
        this.listGrado = filtroGrado;
        notifyDataSetChanged();
    }

}
