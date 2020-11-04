package com.example.laescuela;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.laescuela.Models.ConexionSQLHelper;
import com.example.laescuela.Utilidades.Utilidades;

public class nuevoAlumnoActivity extends AppCompatActivity {

    EditText campo_nombre, campo_apellido,campo_direccion,campo_telefono;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_alumno);


        campo_nombre = (EditText) findViewById(R.id.etxt_nombre);
        campo_apellido = (EditText) findViewById(R.id.etxt_apellido);
        campo_direccion = (EditText) findViewById(R.id.etxt_direccion);
        campo_telefono = (EditText) findViewById(R.id.etxt_telefono);



    }

    public void onClick(View view){


        Intent miIntent= null;

        switch (view.getId()){
            case R.id.btn1:
                registrarAlumno();
                miIntent = new Intent(this, nuevoAlumnoActivity.class);
                break;
            case R.id.btn2:
                miIntent= new Intent(nuevoAlumnoActivity.this, verAlumnosActivity.class);
                break;

        }startActivity(miIntent);

    }

    private void registrarAlumno() {

        ConexionSQLHelper conn = new ConexionSQLHelper(this,"bd_alumno", null,1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(Utilidades.NOMBRE,campo_nombre.getText().toString());
        values.put(Utilidades.APELLIDO,campo_apellido.getText().toString());
        values.put(Utilidades.DIRECCION,campo_direccion.getText().toString());
        values.put(Utilidades.TELEFONO,campo_telefono.getText().toString());

        Long idResultante = db.insert(Utilidades.TABLA_ALUMNO, Utilidades.ID_ALUMNO, values);

        Toast.makeText(getApplicationContext(), "Registrado Exitosamente - ID: " +idResultante, Toast.LENGTH_SHORT).show();

    }


}