package com.example.laescuela;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laescuela.Models.alumnoT;

import java.io.Serializable;
import java.util.ArrayList;

public class adaptarDatosRecyclerAlumno
        extends RecyclerView.Adapter<adaptarDatosRecyclerAlumno.ViewHolderDatos>
        implements View.OnClickListener {


    ArrayList<alumnoT> listUsuario;

    //serializamos el onclick
    private View.OnClickListener listener;

    public adaptarDatosRecyclerAlumno(ArrayList<alumnoT>listUsuario){

        this.listUsuario = listUsuario;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.personalizado_alumno,null, false);

        //aqui escuchamos el evento de seleccion
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {

        holder.idAlumno.setText(listUsuario.get(position).getId().toString());
        holder.nombre.setText(listUsuario.get(position).getNombre());
        holder.apellido.setText(listUsuario.get(position).getApellido());
        holder.direccion.setText(listUsuario.get(position).getCorreo());
        holder.telefono.setText(listUsuario.get(position).getTelefono());


    }

    @Override
    public int getItemCount() {

        return listUsuario.size();
    }

    public void setOnClickListener(View.OnClickListener listener){

        this.listener= listener;

    }

    //metodo para dar click en el Recycler
    @Override
    public void onClick(View v) {

        if(listener != null){

            listener.onClick(v);

        }

    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView idAlumno, nombre, apellido, direccion, telefono;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            idAlumno = (TextView) itemView.findViewById(R.id.id_id);
            nombre = (TextView) itemView.findViewById(R.id.id_nombretxt);
            apellido = (TextView) itemView.findViewById(R.id.id_apellidotxt);
            direccion = (TextView) itemView.findViewById(R.id.id_direcciontext);
            telefono = (TextView) itemView.findViewById(R.id.id_telefonotxt);
        }

    }

    //filtrar datos segun buscador
    public void filtrar(ArrayList<alumnoT> filtroAlumno){
        this.listUsuario = filtroAlumno;
        notifyDataSetChanged();
    }
}
