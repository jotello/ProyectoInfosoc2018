package proyects.tello.ehelper.DB;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bd_infosoc.db";

    public ConexionHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_ENFERMEDAD);
        db.execSQL(Utilidades.CREAR_TABLA_PREGUNTA);
        db.execSQL(Utilidades.CREAR_TABLA_PAIS);
        db.execSQL(Utilidades.CREAR_TABLA_SINTOMA);
        db.execSQL(Utilidades.CREAR_TABLA_ZONA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ Utilidades.TABLA_ENFERMEDAD+ ";" );
        db.execSQL("DROP TABLE IF EXISTS "+ Utilidades.TABLA_PREGUNTA+ ";" );
        db.execSQL("DROP TABLE IF EXISTS "+ Utilidades.TABLA_PAIS+ ";" );
        db.execSQL("DROP TABLE IF EXISTS "+ Utilidades.TABLA_SINTOMA+ ";" );
        db.execSQL("DROP TABLE IF EXISTS "+ Utilidades.TABLA_ZONA+ ";" );
        onCreate(db);

    }
}
