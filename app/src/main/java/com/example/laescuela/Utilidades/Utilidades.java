package com.example.laescuela.Utilidades;

import android.os.ParcelUuid;
import android.service.autofill.FillEventHistory;

import java.sql.Blob;

public class Utilidades {

        //TABLA ALUMNO
        public static String TABLA_ALUMNO = "alumno";
        public static String ID_ALUMNO = "id";
        public static String NOMBRE = "nombre";
        public static String APELLIDO = "apellido";
        public static String DIRECCION = "direccion";
        public static String TELEFONO = "telefono";

        public static final String CREAR_TABLA_ALUMNO = " CREATE TABLE "+ TABLA_ALUMNO +
                " ( "+ ID_ALUMNO +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                NOMBRE +" TEXT, "+
                APELLIDO +" TEXT, "+
                DIRECCION+" TEXT, "+
                TELEFONO+" TEXT) ";

        //TABLA GRADO
        public static String TABLA_GRADO = "gradoActivity";
        public static String ID_GRADO = "id_grado";
        public static String GRADO = "nombre_grado";

        public static final String CREAR_TABLA_GRADO = " CREATE TABLE "+ TABLA_GRADO +
                " ( "+ ID_GRADO +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                GRADO +" TEXT) ";

        //TABLA INSCRIPCION
        public static String TABLA_INSCRIPCION = "inscripcion";
        public static String ID_INSCRIPCION = "id_inscripcion";
        public static String NOMBREALUMNO = "nombre";
        public static String APELLIDOALUMNO = "apellido";
        public static String DIRECCIONALUMNO = "direccion";
        public static String TELEFONOALUMNO = "telefono";
        public static String GRADOSELECT = "grado";
        public static String FECHA = "fecha";
        public static String IMAGEN = "imagen";

        public static final String CREAR_TABLA_INSCRIPCION = " CREATE TABLE "+ TABLA_INSCRIPCION +
                " ( "+ ID_INSCRIPCION +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                NOMBREALUMNO +" TEXT, " +
                APELLIDOALUMNO + " TEXT, " +
                DIRECCIONALUMNO + " TEXT, " +
                TELEFONOALUMNO +" TEXT, "+
                GRADOSELECT + " TEXT, "+
                FECHA + " TEXT, "+
                IMAGEN + " BLOB NOT NULL ) ";

}
