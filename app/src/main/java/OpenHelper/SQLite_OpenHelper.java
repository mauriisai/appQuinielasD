package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLite_OpenHelper extends SQLiteOpenHelper {
        public SQLite_OpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db ) {
            String query = "CREATE TABLE Usuarios(_idUsuario integer primary key autoincrement, nomUsuario text, " +
                    "correo text, identificacion text, telefono text, password Text);";

            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        }

        //METODO PARA ABRIR BD
        public void abrir(){
            this.getWritableDatabase();
        }

        //METODO PARA CERRAR BD
        public void cerrar() {
            this.close();
        }

        //METODO PARA INSERTAR REGISTROS EN BD
        public void insertRegistros(String nom, String correo , String identificacion, String tel , String password) {
            ContentValues valores = new ContentValues();
            valores.put("nomUsuario", nom);
            valores.put("correo", correo);
            valores.put("identificacion", identificacion);
            valores.put("telefono", tel);
            valores.put("password", password);
            this.getWritableDatabase().insert("Usuarios",null,valores);
        }
        //METODO PARA VALIDAR EXISTENCIA DE USUARIO EN BD
        public Cursor ConsultarUsuario (String nomUsuario, String password) throws SQLException {
            Cursor mCursor = null;

            mCursor=this.getReadableDatabase().query("Usuarios", new String[]{"_idUsuario", "nomUsuario",
                    "correo", "identificacion", "telefono", "password"},"nomUsuario like '"+nomUsuario+"' and password like '"+
                    password+"'", null,null,null,null);
            return mCursor;
        }

    }