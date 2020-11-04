package com.example.laescuela;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.laescuela.Models.ConexionSQLHelper;
import com.example.laescuela.Models.gradoT;
import com.example.laescuela.Models.inscripcionT;
import com.example.laescuela.Utilidades.Utilidades;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;


public class verInscritosActivity extends AppCompatActivity {

    EditText buscarInscitos;

    ArrayList<inscripcionT> listInscritos;
    RecyclerView recycler;

    ConexionSQLHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_inscritos);


        conn = new ConexionSQLHelper(getApplicationContext(), "bd_alumno", null, 1);


        buscarInscitos = (EditText) findViewById(R.id.etxt_buscar_inscritos);
        buscarInscitos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

            @Override
            public void afterTextChanged(Editable s) {

                filtrar(s.toString());

            }
        });


        listInscritos = new ArrayList<>();

        recycler = (RecyclerView) findViewById(R.id.recyclerInscritos);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        consultarListaInscritos();

        adapterRecyclerInscripcion adapter = new adapterRecyclerInscripcion(listInscritos);
        recycler.setAdapter(adapter);

    }


    public void filtrar(String texto){

        ArrayList<inscripcionT> filtrarLista = new ArrayList<>();

        for (inscripcionT inscritos : listInscritos){
            if (inscritos.getNombreAlumno().toLowerCase().contains(texto.toLowerCase())||
                    inscritos.getApellidoAlumno().toLowerCase().contains(texto.toLowerCase())||
                    inscritos.getGradoGrad().toLowerCase().contains(texto.toLowerCase())||
                    inscritos.getFechaInscrita().toLowerCase().contains(texto.toLowerCase())){
                filtrarLista.add(inscritos);
            }
        }
        adapterRecyclerInscripcion adaptador = new adapterRecyclerInscripcion(listInscritos);
        recycler.setAdapter(adaptador);
        adaptador.filtrar(filtrarLista);
    }

    private void consultarListaInscritos() {

        SQLiteDatabase db = conn.getReadableDatabase();


        inscripcionT inscripcion = null;


        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_INSCRIPCION, null);

        while (cursor.moveToNext()){

            inscripcion = new inscripcionT();
            inscripcion.setNombreAlumno(cursor.getString(1));
            inscripcion.setApellidoAlumno(cursor.getString(2));
            inscripcion.setGradoGrad(cursor.getString(5));
            inscripcion.setFechaInscrita(cursor.getString(6));
            byte[] imgBytes = inscripcion.setImagen(cursor.getBlob(7)) ;
            BitmapFactory.decodeByteArray(imgBytes,0,imgBytes.length);

            listInscritos.add(inscripcion);
        }

    }
}