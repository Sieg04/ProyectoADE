package com.example.gestiondatos;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ChicktionaryDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Chicktionary.db";
    private static final int DATABASE_VERSION = 1;

    public ChicktionaryDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla Usuario
        db.execSQL("CREATE TABLE Usuario (" +
                "id_User INTEGER PRIMARY KEY, " +
                "nombre TEXT, " +
                "password TEXT, " +
                "nivel INTEGER);");

        // Crear tabla Palabra
        db.execSQL("CREATE TABLE Palabra (" +
                "id_Palabra INTEGER PRIMARY KEY, " +
                "palabra_es TEXT, " +
                "palabra_en TEXT, " +
                "dificultad TEXT, " +
                "definicion TEXT, " +
                "palabra_audio TEXT);");

        // Crear tabla Palabras_Acertadas
        db.execSQL("CREATE TABLE Palabras_Acertadas (" +
                "id_User INTEGER, " +
                "id_Palabra INTEGER, " +
                "acertada INTEGER DEFAULT 0 CHECK (acertada IN (0, 1)), " +
                "PRIMARY KEY (id_User, id_Palabra), " +
                "FOREIGN KEY (id_User) REFERENCES Usuario(id_User), " +
                "FOREIGN KEY (id_Palabra) REFERENCES Palabra(id_Palabra));");

        // Crear tabla Logros
        db.execSQL("CREATE TABLE Logros (" +
                "id_Logro INTEGER PRIMARY KEY, " +
                "nombre TEXT, " +
                "icono TEXT, " +
                "descripcion TEXT);");

        // Crear tabla Logros_desbloqueados
        db.execSQL("CREATE TABLE Logros_desbloqueados (" +
                "id_User INTEGER, " +
                "id_Logro INTEGER, " +
                "fecha_desbloqueo TEXT, " +
                "desbloqueado INTEGER DEFAULT 0 CHECK (desbloqueado IN (0, 1)), " +
                "PRIMARY KEY (id_User, id_Logro), " +
                "FOREIGN KEY (id_User) REFERENCES Usuario(id_User), " +
                "FOREIGN KEY (id_Logro) REFERENCES Logros(id_Logro));");

        // Crear tabla Mundo
        db.execSQL("CREATE TABLE Mundo (" +
                "id_Mundo INTEGER PRIMARY KEY, " +
                "nombre TEXT);");

        // Crear tabla Actividad
        db.execSQL("CREATE TABLE Actividad (" +
                "id_Actividad INTEGER PRIMARY KEY, " +
                "id_Mundo INTEGER, " +
                "tipo TEXT, " +
                "FOREIGN KEY (id_Mundo) REFERENCES Mundo(id_Mundo));");

        // Crear tabla Progreso
        db.execSQL("CREATE TABLE Progreso (" +
                "id_Progreso INTEGER PRIMARY KEY, " +
                "id_Actividad INTEGER, " +
                "id_User INTEGER, " +
                "una_Estrella INTEGER DEFAULT 0 CHECK (una_Estrella IN (0, 1)), " +
                "dos_Estrellas INTEGER DEFAULT 0 CHECK (dos_Estrellas IN (0, 1)), " +
                "tres_Estrellas INTEGER DEFAULT 0 CHECK (tres_Estrellas IN (0, 1)), " +
                "FOREIGN KEY (id_User) REFERENCES Usuario(id_User), " +
                "FOREIGN KEY (id_Actividad) REFERENCES Actividad(id_Actividad));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Progreso;");
        db.execSQL("DROP TABLE IF EXISTS Actividad;");
        db.execSQL("DROP TABLE IF EXISTS Mundo;");
        db.execSQL("DROP TABLE IF EXISTS Logros_desbloqueados;");
        db.execSQL("DROP TABLE IF EXISTS Logros;");
        db.execSQL("DROP TABLE IF EXISTS Palabras_Acertadas;");
        db.execSQL("DROP TABLE IF EXISTS Palabra;");
        db.execSQL("DROP TABLE IF EXISTS Usuario;");
        onCreate(db);
    }
}
