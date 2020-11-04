package com.example.laescuela;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.laescuela.Models.ConexionSQLHelper;
import com.example.laescuela.Models.alumnoT;
import com.example.laescuela.Models.gradoT;
import com.example.laescuela.Utilidades.Utilidades;

import java.util.ArrayList;

public class verGrado extends AppCompatActivity {

    EditText buscarGrado;

    ArrayList<gradoT> listaGrado;
    RecyclerView recycler;

    ConexionSQLHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_grado);

        conn = new ConexionSQLHelper(getApplicationContext(), "bd_alumno", null, 1);


        buscarGrado = (EditText) findViewById(R.id.etxt_buscar_grado);
        buscarGrado.addTextChangedListener(new TextWatcher() {
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

        listaGrado = new ArrayList<>();

        recycler = (RecyclerView) findViewById(R.id.recyclerGrado);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        consultarListaGrado();

        adaptarDatosRecyclerGrado adapter = new adaptarDatosRecyclerGrado(listaGrado);
        recycler.setAdapter(adapter);


    }


    public void filtrar(String texto){

        ArrayList<gradoT> filtrarLista = new ArrayList<>();

        for (gradoT grado : listaGrado){
            if (grado.getGrado().toLowerCase().contains(texto.toLowerCase())){
                filtrarLista.add(grado);
            }
        }
        adaptarDatosRecyclerGrado adaptador = new adaptarDatosRecyclerGrado(listaGrado);
        recycler.setAdapter(adaptador);
        adaptador.filtrar(filtrarLista);
    }

    private void consultarListaGrado() {

        SQLiteDatabase db = conn.getReadableDatabase();

        gradoT grado = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_GRADO, null);

        while (cursor.moveToNext()){

            grado = new gradoT();
            grado.setId_grado(cursor.getInt(0));
            grado.setGrado(cursor.getString(1));

            listaGrado.add(grado);
        }

    }


}