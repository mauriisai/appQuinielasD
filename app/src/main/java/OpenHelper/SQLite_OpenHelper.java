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
            String queryUsuarios = "CREATE TABLE if not exists Usuario(" +
                    "_idUsuario integer primary key autoincrement," +
                    "nomUsuario text UNIQUE, " +
                    "correo text UNIQUE, " +
                    "identificacion text UNIQUE," +
                    " telefono text," +
                    " password Text);";

            String queryTorneos ="CREATE TABLE if not exists Torneo(" +
                    "idTorneo INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nomTorneo TEXT," +
                    "region TEXT," +
                    "numParticipantes INTEGER," +
                    "fechaInicio DATE," +
                    "fechaFin DATE" +
                    ");";

            String queryEquipos ="CREATE TABLE if not exists Equipo(" +
                    "idEquipo INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nomEquipo TEXT," +
                    "pais TEXT," +
                    "campeonatos INTEGER" +
                    ");";

            String queryEquipoTorneos ="CREATE TABLE if not exists EquipoTorneo(" +
                    "idEquipoTorneo INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "idTorneo INTEGER NOT NULL," +
                    "idEquipo INTEGER," +
                    "es_activo BOOLEAN," +
                    "FOREIGN KEY(idTorneo) REFERENCES Torneo(idTorneo),"+
                    "FOREIGN KEY(idEquipo) REFERENCES Equipo(idEquipo)"+
                    ");";

            String queryEncuentros = "CREATE TABLE if not exists Encuentro(" +
                    "idEncuentro INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "visitante INTEGER NOT NULL," +
                    "local INTEGER NOT NULL," +
                    "scoreLocal INTEGER," +
                    "scoreVisitante INTEGER," +
                    "fecha DATE," +
                    "hora TIME," +
                    "FOREIGN KEY(visitante) REFERENCES Equipo(idEquipo),"+
                    "FOREIGN KEY(local) REFERENCES Equipo(idEquipo)"+
                    ");";

            String queryOfertas = "CREATE TABLE if not exists Oferta(" +
                    "idOferta INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "idEncuentro INTEGER," +
                    "puntoLocal DECIMAL(10, 5),"+
                    "puntoVisitante DECIMAL(10, 5),"+
                    "puntoEmpate DECIMAL(10, 5),"+
                    "FOREIGN KEY(idEncuentro) REFERENCES Encuentro(idEncuentro)"+
                    ");";

            String queryResultados = "CREATE TABLE if not exists Resultado(" +
                    "idResultado INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "idEncuentro INTEGER," +
                    "equipoGanador INTEGER," +
                    "FOREIGN KEY(idEncuentro) REFERENCES Encuentro(idEncuentro),"+
                    "FOREIGN KEY(equipoGanador) REFERENCES Equipo(idEquipo)"+
                    ");";

            String queryApuestas = "CREATE TABLE if not exists Apuesta(" +
                    "idApuesta INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "idEncuentro INTEGER," +
                    "idUsuario INTEGER," +
                    "montoApuesta DECIMAL(10,2)," +
                    "resultadoApuesta INTEGER," +
                    "montooRetribucion DECIMAL(10,2)," +
                    "AQueAposto INTEGER," +
                    "FOREIGN KEY(idEncuentro) REFERENCES Encuentro(idEncuentro),"+
                    "FOREIGN KEY(idUsuario) REFERENCES Usuario(idusuario)"+
                    ");";

            db.execSQL(queryUsuarios);
            db.execSQL(queryTorneos);
            db.execSQL(queryEquipos);
            db.execSQL(queryEquipoTorneos);
            db.execSQL(queryEncuentros);
            db.execSQL(queryOfertas);
            db.execSQL(queryResultados);
            db.execSQL(queryApuestas);
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
            this.getWritableDatabase().insert("Usuario",null,valores);
        }
        //METODO PARA VALIDAR EXISTENCIA DE USUARIO EN BD
        public Cursor ConsultarUsuario (String nomUsuario, String password) throws SQLException {
            Cursor mCursor;

            mCursor=this.getReadableDatabase().query("Usuario", new String[]{"_idUsuario", "nomUsuario",
                    "correo", "identificacion", "telefono", "password"},"nomUsuario like '"+nomUsuario+"' and password like '"+
                    password+"'", null,null,null,null);
            return mCursor;
        }

    }