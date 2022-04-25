package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import entidades.Equipos;

public class tblEquipo extends SQLiteOpenHelper {

    Context context;

    public tblEquipo(@Nullable Context context) {
        super(context, "BDQuinielas",null,1);

        this.context = context;
    }


    public long insertarEquipo(String nombreEq, String paisEq, String correoEq) {

        long id = 0;

        try {
            SQLite_OpenHelper dbHelper = new SQLite_OpenHelper(context,"BDQuinielas",null,1);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nomEquipo", nombreEq);
            values.put("pais", paisEq);
            values.put("correoEquipo", correoEq);

            id = db.insert("Equipo", null, values);
        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }

    public ArrayList<Equipos> mostrarEquipos() {

        SQLite_OpenHelper dbHelper = new SQLite_OpenHelper(context,"BDQuinielas",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Equipos> listaEquipos = new ArrayList<>();
        Equipos equipo;
        Cursor cursorEquipos;

        cursorEquipos = db.rawQuery("SELECT * FROM Equipo ORDER BY nomEquipo ASC", null);

        if (cursorEquipos.moveToFirst()) {
            do {
                equipo = new Equipos();
                equipo.setId(cursorEquipos.getInt(0));
                equipo.setnombreEq(cursorEquipos.getString(1));
                equipo.setpaisEq(cursorEquipos.getString(2));
                equipo.setcorreoEq(cursorEquipos.getString(3));
                listaEquipos.add(equipo);
            } while (cursorEquipos.moveToNext());
        }

        cursorEquipos.close();

        return listaEquipos;
    }

    public Equipos verEquipo(int id) {

        SQLite_OpenHelper dbHelper = new SQLite_OpenHelper(context,"BDQuinielas",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Equipos equipo = null;
        Cursor cursorEquipos;

        cursorEquipos = db.rawQuery("SELECT * FROM Equipo WHERE idEquipo = " + id + " LIMIT 1", null);

        if (cursorEquipos.moveToFirst()) {
            equipo = new Equipos();
            equipo.setId(cursorEquipos.getInt(0));
            equipo.setnombreEq(cursorEquipos.getString(1));
            equipo.setpaisEq(cursorEquipos.getString(2));
            equipo.setcorreoEq(cursorEquipos.getString(3));
        }

        cursorEquipos.close();

        return equipo;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean editarEquipo(int id, String nombreEq, String paisEq, String correoEq) {

        boolean correcto = false;

        SQLite_OpenHelper dbEq = new SQLite_OpenHelper(context,"BDQuinielas",null,1);
        SQLiteDatabase db = dbEq.getWritableDatabase();

        try {
            db.execSQL("UPDATE Equipo SET nomEquipo = '" + nombreEq + "', pais = '" + paisEq + "', correoEquipo = '" + correoEq + "' WHERE idEquipo='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarEquipo(int id) {

        boolean correcto = false;

        SQLite_OpenHelper dbHelper = new SQLite_OpenHelper(context,"BDQuinielas",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM Equipo WHERE idEquipo = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}

