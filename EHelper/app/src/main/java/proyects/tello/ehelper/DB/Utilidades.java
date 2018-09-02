package proyects.tello.ehelper.DB;

import static android.provider.BaseColumns._ID;

public class Utilidades{

    //Campos tabla enfermedad

    public static final String TABLA_ENFERMEDAD = "enfermedad";
    private static final String CAMPO_ID_ENFERMEDAD = _ID;
    private static final String CAMPO_NOMBRE = "nombre";
    private static final String CAMPO_TIEMPO_INCUBACION_MIN = "tiempo_incubacion_min";
    private static final String CAMPO_TIEMPO_INCUBACION_MAX = "tiempo_incubacion_max";
    private static final String CAMPO_TIEMPO_SINTOMAS_MIN = "tiempo_sintomas_min";
    private static final String CAMPO_TIEMPO_SINTOMAS_MAX = "tiempo_sintomas_max";

    public static final String CREAR_TABLA_ENFERMEDAD = "CREATE TABLE "+TABLA_ENFERMEDAD+
            " ("+ CAMPO_ID_ENFERMEDAD+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT"+", "+CAMPO_NOMBRE+
            "TEXT, "+CAMPO_TIEMPO_INCUBACION_MIN+" TEXT,"+ CAMPO_TIEMPO_SINTOMAS_MIN+" TEXT);";

    public static final String INSERTAR_ENFERMEDAD = "INSERT INTO "+ TABLA_ENFERMEDAD+"( "+
            CAMPO_NOMBRE+","+CAMPO_TIEMPO_INCUBACION_MIN+","+CAMPO_TIEMPO_SINTOMAS_MIN+") VALUES ('";

    //Campos tabla pais

    public static final String TABLA_PAIS = "pais";
    private static final String CAMPO_ID_PAIS = "_id_p";
    private static final String CAMPO_NOMBRE_PAIS = "nombre_pais";

    public static final String CREAR_TABLA_PAIS = "CREATE TABLE "+TABLA_PAIS+
            " ("+ CAMPO_ID_PAIS+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT"+", "+CAMPO_NOMBRE_PAIS+
            "TEXT);";

    public static final String INSERTAR_PAIS = "INSERT INTO "+ TABLA_PAIS+"( "+
            CAMPO_NOMBRE_PAIS+") VALUES ('";

    //Campos tabla pregunta

    public static final String TABLA_PREGUNTA = "pais";
    private static final String CAMPO_ID_PREGUNTA = "_id_pr";
    private static final String CAMPO_NOMBRE_SINTOMA = "nombre_sintoma";

    public static final String CREAR_TABLA_PREGUNTA = "CREATE TABLE "+TABLA_PREGUNTA+
            " ("+ CAMPO_ID_PREGUNTA+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT"+", "+CAMPO_NOMBRE_SINTOMA+
            "TEXT);";

    public static final String INSERTAR_PREGUNTA = "INSERT INTO "+ TABLA_PREGUNTA+"( "+
            CAMPO_NOMBRE_SINTOMA+") VALUES ('";

    //Campos tabla sintoma

    public static final String TABLA_SINTOMA = "sintoma";
    private static final String CAMPO_ID_SINTOMA = "_id_s";
    private static final String CAMPO_TIPO_SINTOMA = "tipo";

    public static final String CREAR_TABLA_SINTOMA = "CREATE TABLE "+TABLA_SINTOMA+
            " ("+ CAMPO_ID_SINTOMA+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT"+", "+CAMPO_NOMBRE_SINTOMA+
            "TEXT,"+CAMPO_TIPO_SINTOMA+" TEXT);";

    public static final String INSERTAR_SINTOMA = "INSERT INTO "+ TABLA_SINTOMA+"( "+
            CAMPO_NOMBRE_SINTOMA+","+CAMPO_TIPO_SINTOMA+") VALUES ('";

    //Campos tabla zona

    public static final String TABLA_ZONA = "zona";
    private static final String CAMPO_ID_ZONA = "_id_z";
    private static final String CAMPO_NOMBRE_ZONA = "nombre_zona";
    private static final String CAMPO_RIESGO_ZIKA = "riesgo_zika";
    private static final String CAMPO_RIESGO_MALARIA = "riesgo_malaria";

    public static final String CREAR_TABLA_ZONA = "CREATE TABLE "+TABLA_ZONA+
            " ("+ CAMPO_ID_ZONA+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT"+", "+CAMPO_NOMBRE_ZONA+
            " TEXT, "+CAMPO_NOMBRE_PAIS+" TEXT, "+CAMPO_RIESGO_ZIKA+" TEXT, "+CAMPO_RIESGO_MALARIA+" TEXT);";

    public static final String INSERTAR_ZONA = "INSERT INTO "+ TABLA_ZONA+"( "+
            CAMPO_NOMBRE_ZONA+") VALUES ('";




}

