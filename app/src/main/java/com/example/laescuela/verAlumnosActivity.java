package com.example.laescuela;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laescuela.Models.ConexionSQLHelper;
import com.example.laescuela.Models.alumnoT;
import com.example.laescuela.Utilidades.Utilidades;

import java.util.ArrayList;

public class verAlumnosActivity extends AppCompatActivity {

    EditText txtBuscar;

    ArrayList<alumnoT> listaAlumno;
    RecyclerView recycler;


    ConexionSQLHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_alumnos);

        conn = new ConexionSQLHelper(getApplicationContext(), "bd_alumno", null, 1);

        txtBuscar = (EditText) findViewById(R.id.etxt_buscar_alumno);
        txtBuscar.addTextChangedListener(new TextWatcher() {
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



        listaAlumno = new ArrayList<>();

        recycler = (RecyclerView) findViewById(R.id.recyclerAlumno);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        consultarListaPersonas();

        adaptarDatosRecyclerAlumno adapter = new adaptarDatosRecyclerAlumno(listaAlumno);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "Nombre:  "+ listaAlumno.get(recycler.getChildAdapterPosition(v)).getNombre() +"\n"+
                        "Apellido:  "+ listaAlumno.get(recycler.getChildAdapterPosition(v)).getApellido()+"\n"+
                        "Direccion: "+ listaAlumno.get(recycler.getChildAdapterPosition(v)).getCorreo()+"\n"+
                        "Telefono:  "+ listaAlumno.get(recycler.getChildAdapterPosition(v)).getTelefono(),
                        Toast.LENGTH_SHORT).show();

                alumnoT alumno = listaAlumno.get(recycler.getChildAdapterPosition(v));

                Intent intent = new Intent(verAlumnosActivity.this, inscripcionActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("alumno", alumno);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        recycler.setAdapter(adapter);


    }

    public void filtrar(String texto){

        ArrayList<alumnoT> filtrarLista = new ArrayList<>();

        for (alumnoT alumno : listaAlumno){
            if (alumno.getNombre().toLowerCase().contains(texto.toLowerCase())||
                    alumno.getApellido().toLowerCase().contains(texto.toLowerCase())||
                    alumno.getCorreo().toLowerCase().contains(texto.toLowerCase())||
                    alumno.getTelefono().toLowerCase().contains(texto.toLowerCase())){
                filtrarLista.add(alumno);
            }
        }
        adaptarDatosRecyclerAlumno adaptador = new adaptarDatosRecyclerAlumno(listaAlumno);
        recycler.setAdapter(adaptador);
        adaptador.filtrar(filtrarLista);


    }

    private void consultarListaPersonas() {

        SQLiteDatabase db = conn.getReadableDatabase();

        alumnoT alumno = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_ALUMNO, null);

        while (cursor.moveToNext()){
    
            alumno = new alumnoT();
            alumno.setId(cursor.getInt(0));
            alumno.setNombre(cursor.getString(1));
            alumno.setApellido(cursor.getString(2));
            alumno.setCorreo(cursor.getString(3));
            alumno.setTelefono(cursor.getString(4));

            listaAlumno.add(alumno);
    }

}  


}