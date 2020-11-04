package com.example.laescuela;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.laescuela.Models.ConexionSQLHelper;
import com.example.laescuela.Models.alumnoT;
import com.example.laescuela.Models.gradoT;
import com.example.laescuela.Utilidades.Utilidades;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class inscripcionActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private static final int CAMERA_REQUEST = 200;

    Button guardar;
    AutoCompleteTextView dropdown;
    EditText nombre, apellido, direccion, telefono, fecha;
    ImageView imgCalendar, nuevaImagen;

    ArrayList<String> listaGrado;
    ArrayList<gradoT> gradoList;

    ConexionSQLHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscripcion);

        conn = new ConexionSQLHelper(this,"bd_alumno", null,1);


        guardar = (Button) findViewById(R.id.btn1);

        nuevaImagen = (ImageView) findViewById(R.id.new_memory_selected_image);
        imgCalendar = (ImageView) findViewById(R.id.imageCalendar);

        nombre = (EditText) findViewById(R.id.etxt_nombre);
        apellido = (EditText) findViewById(R.id.etxt_apellido);
        direccion = (EditText) findViewById(R.id.etxt_direccion);
        telefono = (EditText) findViewById(R.id.etxt_telefono);
        dropdown = (AutoCompleteTextView) findViewById(R.id.etxt_grado);
        fecha = (EditText) findViewById(R.id.etxt_fecha);

        //aqui recibe de la clase de verAlumnoActivity los datos que estan en el recycler
        Bundle objetoEnviado = getIntent().getExtras();
        alumnoT alumno=null;

        if(objetoEnviado!=null){
             alumno =  (alumnoT) objetoEnviado.getSerializable("alumno");

             nombre.setText(alumno.getNombre().toString());
             apellido.setText(alumno.getApellido().toString());
             direccion.setText(alumno.getCorreo().toString());
             telefono.setText(alumno.getTelefono().toString());

        }

        consultarListaGrado();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,  android.R.layout.simple_dropdown_item_1line, listaGrado);

        dropdown.setAdapter(adaptador);

        //metodo para mostrar un calendario
        imgCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(inscripcionActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.setTitle("Calendario");
                datePickerDialog.show(getSupportFragmentManager(), "DatePicker ");
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inscribirAlumno();

            }
        });

    }



    public void inscribirAlumno(){



        //aqui se guarda la imagen previa antes de guardar

        ConexionSQLHelper conn = new ConexionSQLHelper(this,"bd_alumno", null,1);
        SQLiteDatabase db = conn.getWritableDatabase();
        Bitmap image = ((BitmapDrawable)nuevaImagen.getDrawable()).getBitmap();

        byte[] data = getBitmapAsByteArray(image);

        ContentValues values = new ContentValues();

        values.put(Utilidades.IMAGEN, data);
        values.put(Utilidades.NOMBREALUMNO,nombre.getText().toString());
        values.put(Utilidades.APELLIDOALUMNO,apellido.getText().toString());
        values.put(Utilidades.DIRECCIONALUMNO,direccion.getText().toString());
        values.put(Utilidades.TELEFONOALUMNO,telefono.getText().toString());
        values.put(Utilidades.GRADOSELECT,dropdown.getText().toString());
        values.put(Utilidades.FECHA,fecha.getText().toString());

        Long idResultante = db.insert(Utilidades.TABLA_INSCRIPCION, Utilidades.ID_INSCRIPCION, values);
        Toast.makeText(getApplicationContext(), "Registrado Exitosamente -  ID: " +idResultante, Toast.LENGTH_SHORT).show();

        finish();


    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    private void consultarListaGrado() {

        SQLiteDatabase db = conn.getReadableDatabase();
        gradoT grado = null;

        gradoList = new ArrayList<gradoT>();

        Cursor cursor = db.rawQuery( " SELECT * FROM " + Utilidades.TABLA_GRADO,null);

        while (cursor.moveToNext()){

            grado = new gradoT();
            grado.setGrado(cursor.getString(1));

            gradoList.add(grado);

        }

        obtenerLista();

    }

    private void obtenerLista() {

        listaGrado = new ArrayList<String>();

        for (int i=0; i<gradoList.size(); i++){

            listaGrado.add(gradoList.get(i).getGrado());

        }

    }

    //aqui se muestre el dato que seleccionamos en el editText
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        Toast.makeText(this, String.format("Selecionaste: %d/%d,%d", dayOfMonth, monthOfYear, year), Toast.LENGTH_SHORT).show();
        fecha.setText(dayOfMonth +"/"+ monthOfYear+ "/" +year);

    }

    //METODO DE MOSTRAR CAMARA

    public void openCamera(View view){

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(intent.resolveActivity(getPackageManager())!=null){
                startActivityForResult(intent, CAMERA_REQUEST);
            }

    }

    //mostrar imagen en el activity inscripcion
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK){

            Bundle extras = data.getExtras();
            Bitmap image = (Bitmap)extras.get("data");
            nuevaImagen.setImageBitmap(image);

        }

    }



}