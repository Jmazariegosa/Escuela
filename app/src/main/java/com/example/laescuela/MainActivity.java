package com.example.laescuela;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.laescuela.Models.ConexionSQLHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSQLHelper conn = new ConexionSQLHelper(this,"bd_alumno", null,1);
    }


    public void onClick(View view){

        Intent miIntent= null;

        switch (view.getId()){
            case R.id.lin1:
                miIntent= new Intent(MainActivity.this, nuevoAlumnoActivity.class);
                break;
            case R.id.lin2:
                miIntent= new Intent(MainActivity.this, gradoActivity.class);
                break;
            case R.id.lin3:
                miIntent= new Intent(MainActivity.this, inscripcionActivity.class);
                break;
            case R.id.lin4:
                miIntent= new Intent(MainActivity.this, verInscritosActivity.class);
                break;
        }startActivity(miIntent);
    }
}