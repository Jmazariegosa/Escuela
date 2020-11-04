package com.example.laescuela;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ViewTarget;
import com.example.laescuela.Models.ConexionSQLHelper;
import com.example.laescuela.Models.inscripcionT;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

public class adapterRecyclerInscripcion extends RecyclerView.Adapter<adapterRecyclerInscripcion.viewHolderDatos> {

      ArrayList<inscripcionT> listInscritos;

    public adapterRecyclerInscripcion(ArrayList<inscripcionT> listInscripcion) {
        this.listInscritos = listInscripcion;
    }

    @NonNull
    @Override
    public viewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.personalizado_inscripcion,null, false);

        return new viewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderDatos holder, int position) {

       // inscripcionT imagen = listInscritos.get(position);
        //viewHolderDatos image = (viewHolderDatos)holder;

        Glide.with(holder.foto.getContext()).load(listInscritos.get(position).getImagen()).into(holder.foto);
        holder.nombreAlumno.setText(listInscritos.get(position).getNombreAlumno());
        holder.gradoInscrito.setText(listInscritos.get(position).getGradoGrad());
        holder.fechaInscripcion.setText(listInscritos.get(position).getFechaInscrita());
        holder.apellidoAlumno.setText(listInscritos.get(position).getApellidoAlumno());



    }

    @Override
    public int getItemCount() {

        return listInscritos.size();
    }



    public class viewHolderDatos extends RecyclerView.ViewHolder {

        ImageView foto;
        TextView nombreAlumno, gradoInscrito, fechaInscripcion, apellidoAlumno;

        public viewHolderDatos(@NonNull View itemView) {
            super(itemView);


            foto = (ImageView) itemView.findViewById(R.id.idPhoto);
            nombreAlumno = (TextView) itemView.findViewById(R.id.nombreAlumno);
            gradoInscrito = (TextView) itemView.findViewById(R.id.gradoAlumno);
            fechaInscripcion = (TextView) itemView.findViewById(R.id.fechaInscripcion);
            apellidoAlumno = (TextView) itemView.findViewById(R.id.apellidoAlumno);


        }
    }

    public void filtrar(ArrayList<inscripcionT> filtroInscritos){
        this.listInscritos = filtroInscritos;
        notifyDataSetChanged();
    }

}
