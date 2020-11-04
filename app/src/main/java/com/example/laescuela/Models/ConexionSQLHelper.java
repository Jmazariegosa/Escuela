package com.example.laescuela.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.laescuela.Utilidades.Utilidades;

public class ConexionSQLHelper extends SQLiteOpenHelper {



    public ConexionSQLHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Utilidades.CREAR_TABLA_ALUMNO);
        db.execSQL(Utilidades.CREAR_TABLA_GRADO);
        db.execSQL(Utilidades.CREAR_TABLA_INSCRIPCION);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_ALUMNO);
        db.execSQL("DROP TABLE IF EXISTS "+ Utilidades.TABLA_GRADO);
        db.execSQL("DROP TABLE IF EXISTS "+ Utilidades.TABLA_INSCRIPCION);
        onCreate(db);

    }

}
