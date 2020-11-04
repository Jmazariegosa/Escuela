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

public class gradoActivity extends AppCompatActivity {

    EditText campo_grado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grado);

        campo_grado = (EditText) findViewById(R.id.etxt_grado);

    }

    public void onClick(View view){

        Intent miIntent= null;

        switch (view.getId()){
            case R.id.btn1:
                registrarGrado();
                miIntent = new Intent(this, gradoActivity.class);
                break;
            case R.id.btn2:
                miIntent= new Intent(gradoActivity.this, verGrado.class);
                break;

        }startActivity(miIntent);
        finish();
    }

    private void registrarGrado() {

        ConexionSQLHelper conn = new ConexionSQLHelper(this,"bd_alumno", null,1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(Utilidades.GRADO,campo_grado.getText().toString());

        Long idResultante = db.insert(Utilidades.TABLA_GRADO, Utilidades.ID_GRADO, values);

        Toast.makeText(getApplicationContext(), "Registrado Exitosamente -  ID: " +idResultante, Toast.LENGTH_SHORT).show();

    }

}