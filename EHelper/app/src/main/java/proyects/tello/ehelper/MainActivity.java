package proyects.tello.ehelper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import proyects.tello.ehelper.DB.ConexionHelper;
import proyects.tello.ehelper.DB.Utilidades;
import proyects.tello.ehelper.Entidades.Enfermedad;
import proyects.tello.ehelper.Entidades.Pais;
import proyects.tello.ehelper.Entidades.Pregunta;
import proyects.tello.ehelper.Entidades.Sintoma;
import proyects.tello.ehelper.Entidades.Zona;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context context;
    FloatingActionButton fab;
    List<Pregunta> preguntas = new ArrayList<>();
    List<String> tags = new ArrayList<>();
    List<Pais> paises = new ArrayList<>();
    List<Zona> zonas = new ArrayList<>();
    List<Sintoma> sintomas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        // Action bar y drawer layout
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout_main);
        ActionBarDrawerToggle mToggle = new ActionBarDrawerToggle(
                this, drawerLayout,toolbar, R.string.open_dl, R.string.close_dl);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view_main);
        navigationView.setNavigationItemSelectedListener(this);

        final SharedPreferences prefs =
                context.getSharedPreferences("proyects.tello.ehelper", Context.MODE_PRIVATE);

        if (prefs.getString("nombreMedico", "").equals("")){
            Intent intent = null;
            intent = new Intent(context, SignActivity.class);
            startActivity(intent);
        } else {
            View hView =  navigationView.getHeaderView(0);
            TextView nav_user = hView.findViewById(R.id.nombre_user);
            nav_user.setText(prefs.getString("nombreMedico", "a"));

            TextView nav_rut = hView.findViewById(R.id.rut_user);
            nav_rut.setText(prefs.getString("rutMedico", ""));
        }


        cargarListaSintomas();
        cargarListaPaises();
        cargarListaZonas();
        cargarListaPreguntas();
        cargarListaTags();

        //insertarDatos(); //Por el momento solo las enfermedades

        fab = findViewById(R.id.fab_main);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context, ExcActivity.class);
                intent.putExtra("Preguntas", (Serializable) preguntas);
                intent.putExtra("Tags", (Serializable) tags);
                intent.putExtra("Zonas", (Serializable) zonas);
                intent.putExtra("Paises", (Serializable) paises);
                startActivity(intent);
            }
        });
    }

    private void cargarListaTags() {
        for(Sintoma sint: sintomas ){
            if(sint.getTipo().equals("Tag")){
                tags.add(sint.getNombre());
            }
        }
    }

    private void cargarListaPreguntas() {
        int ctd = 0;
        for(Sintoma sint: sintomas ){
            if(sint.getTipo().equals("Excluyente")){
                ctd++;
                Pregunta p = new Pregunta(sint.getNombre(),(Integer.toString(ctd)+". ¿"+sint.getNombre()+"?"), "Si", "No" );
                preguntas.add(p);
            }
        }
    }

    private void cargarListaZonas() {
        //Bolivia
        zonas.add(new Zona("Beni", "Bolivia", (float) 0.8, (float) 0.5));
        zonas.add(new Zona("Chuquisaca", "Bolivia", (float) 0.35, (float) 0.5));
        zonas.add(new Zona("Cochabamba", "Bolivia", (float) 0.35, (float) 0.1));
        zonas.add(new Zona("La paz", "Bolivia", (float) 0.5, (float) 0.1));
        zonas.add(new Zona("Oruro", "Bolivia", (float) 0.2, (float) 0.1));
        zonas.add(new Zona("Pando", "Bolivia", (float) 0.6, (float) 0.5));
        zonas.add(new Zona("Potosí", "Bolivia", (float) 0.2, (float) 0.1));
        zonas.add(new Zona("Santa Cruz", "Bolivia", (float) 0.6, (float) 0.5));
        zonas.add(new Zona("Tarija", "Bolivia", (float) 0.5, (float) 0.5));
        //Peru
        zonas.add(new Zona("Loreto", "Peru", (float) 0.1, (float) 0.8));
        zonas.add(new Zona("Ucayali", "Peru", (float) 0.1, (float) 0.8));
        zonas.add(new Zona("Ayacucho", "Peru", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Piura", "Peru", (float) 0.1, (float) 0.2));
        zonas.add(new Zona("Cusco", "Peru", (float) 0.1, (float) 0.2));
        zonas.add(new Zona("La Libertad", "Peru", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Ica", "Peru", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("San Martin", "Peru", (float) 0.1, (float) 0.2));
        zonas.add(new Zona("Tumbes", "Peru", (float) 0.1, (float) 0.5));
        zonas.add(new Zona("Cajamarca", "Peru", (float) 0.1, (float) 0.2));
        zonas.add(new Zona("Lambayeque", "Peru", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Junin", "Peru", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Huanaco", "Peru", (float) 0.1, (float) 0.2));
        zonas.add(new Zona("Madre de dios", "Peru", (float) 0.1, (float) 0.5));
        zonas.add(new Zona("Lima", "Peru", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Amazonas", "Peru", (float) 0.1, (float) 0.5));
        zonas.add(new Zona("Pasco", "Peru", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Ancash", "Peru", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Arequipa", "Peru", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Tacna", "Peru", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Huancavelica", "Peru", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Moquegua", "Peru", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Puno", "Peru", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Callao", "Peru", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Purimac", "Peru", (float) 0.1, (float) 0.1));
        //Argentina
        zonas.add(new Zona("Jujuy", "Argentina", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Salta", "Argentina", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Formosa", "Argentina", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Chaco", "Argentina", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Corrientes", "Argentina", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Misiones", "Argentina", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Entre Rios", "Argentina", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Buenos Aires", "Argentina", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Santa Fe", "Argentina", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Santiago del Estereo", "Argentina", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Tucuman", "Argentina", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Catamarca", "Argentina", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("La Rioja", "Argentina", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Cordoba", "Argentina", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("San Juan", "Argentina", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("San Luis", "Argentina", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Mendoza", "Argentina", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("La Pampa", "Argentina", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Neuquen", "Argentina", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Rio Negro", "Argentina", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Chubut", "Argentina", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Santa Cruz", "Argentina", (float) 0.1, (float) 0.1));
        //Chile
        zonas.add(new Zona("Tarapacá", "Chile", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Antofagasta", "Chile", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Atacama", "Chile", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Coquimbo", "Chile", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Valparaiso", "Chile", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Libertador General Bernardo O'Higgins", "Chile", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Maule", "Chile", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Bío Bío", "Chile", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("La Araucanía", "Chile", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Los Rios", "Chile", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Los Lagos", "Chile", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Aysen del General Carlos Ibañes del Campo", "Chile", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Magallanes y Antartica Chilena", "Chile", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Metropolitana", "Chile", (float) 0.1, (float) 0.1));
        zonas.add(new Zona("Arica y Parinacota", "Chile", (float) 0.1, (float) 0.1));
    }

    private void cargarListaPaises() {

        paises.add(new Pais("Peru"));
        paises.add(new Pais("Argentina"));
        paises.add(new Pais("Bolivia"));
        paises.add(new Pais("Chile"));
        /**
         * //Paises por implementar
        paises.add(new Pais("Brasil"));
        paises.add(new Pais("Colombia"));
        paises.add(new Pais("Ecuador"));
        paises.add(new Pais("Paraguay"));
        paises.add(new Pais("Venezuela"));
        paises.add(new Pais("Guyana"));
         **/
    }

    private void cargarListaSintomas() {
        //Sintomas de Malaria
        sintomas.add(new Sintoma("Fiebre Baja (38 °C)", "Excluyente"));
        sintomas.add(new Sintoma("Dolor leve de cabeza", "Excluyente"));
        //Sintomas de Zika
        sintomas.add(new Sintoma("Vómito", "Excluyente"));
        sintomas.add(new Sintoma("Dolor leve articulaciones", "Excluyente"));
        sintomas.add(new Sintoma("Conjuntivitis", "Excluyente"));
        sintomas.add(new Sintoma("Ojos Rojos", "Excluyente"));
        sintomas.add(new Sintoma("Cefalea", "Excluyente"));
        sintomas.add(new Sintoma("Náuseas", "Excluyente"));
        //Tags de Malaria
        sintomas.add(new Sintoma("Escalofríos", "Tag"));
        sintomas.add(new Sintoma("Anemia grave", "Tag"));
        sintomas.add(new Sintoma("Sufrimiento respiratorio", "Tag"));
        //Tags de Zika
        sintomas.add(new Sintoma("Artritis o Artralgia", "Tag"));
        sintomas.add(new Sintoma("Falta de apetito", "Tag"));
        sintomas.add(new Sintoma("Diarrea", "Tag"));
        sintomas.add(new Sintoma("Dolor abdominal", "Tag"));
        sintomas.add(new Sintoma("Erupciones con puntos rojos y blancos en la piel", "Tag"));
        sintomas.add(new Sintoma("Sensibilidad a la luz", "Tag"));
        sintomas.add(new Sintoma("Aftas", "Tag"));

        /**
        //Sintomas por implementar
        sintomas.add(new Sintoma("Fiebre Alta (40 °C)", "Excluyente"));
        sintomas.add(new Sintoma("Dolor muscular", "Excluyente"));
        sintomas.add(new Sintoma("Dolor de espalda", "Excluyente"));
        sintomas.add(new Sintoma("Artritis", "Tag"));
        sintomas.add(new Sintoma("Parches de sangre bajo la piel", "Tag"));
        sintomas.add(new Sintoma("Sarpullido", "Tag"));
        sintomas.add(new Sintoma("Dolor retroocular y alrededor de los ojos", "Tag"));
        sintomas.add(new Sintoma("Sangrado de nariz y encía", "Tag"));
        sintomas.add(new Sintoma("Respiración acelerada", "Tag"));
        sintomas.add(new Sintoma("Hemorragia de encias", "Tag"));
        sintomas.add(new Sintoma("Fatiga", "Tag"));
        sintomas.add(new Sintoma("Inquietud", "Tag"));
        sintomas.add(new Sintoma("Sangre en el vomito", "Tag"));
        sintomas.add(new Sintoma("Erupciones cutáneas", "Tag"));
         **/

    }


    private void insertarDatos() {

        //Usare esta funcion para hardcodear los datos dentro de la base de datos, a futuro quiero
        //importar los datos desde un archivo .csv

        ConexionHelper conn = new ConexionHelper(this);
        SQLiteDatabase db = conn.getWritableDatabase();

        Enfermedad zika = new Enfermedad("Zika", 4, 7,
                2, 7);
        //Tiempo sintomas 2-7 dias
        Enfermedad dengue = new Enfermedad("Dengue", 10 , 7,
                2,7);
        //Tiempo incubacion: 4-10 dias, tiempo sintomas: 2-7 dias

        //Falta fiebre amarilla, chikungunya, malaria

        String insertZika = Utilidades.INSERTAR_ENFERMEDAD+zika.getNombre()+"' ,'"+
                Integer.toString(zika.getTiempoIncubacionMin())+"', '"+
                Integer.toString(zika.getTiempoSintomasMin())+"'  )";

        String insertDengue = Utilidades.INSERTAR_ENFERMEDAD+dengue.getNombre()+"' ,'"+
                Integer.toString(dengue.getTiempoIncubacionMin())+"', '"+
                Integer.toString(dengue.getTiempoSintomasMin())+"'  )";


        db.execSQL(insertZika);
        db.execSQL(insertDengue);
        db.close();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_main);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if ( id == R.id.home){
           //Ya esta en inicio
        } else if (id == R.id.ajustes) {
            Intent intent = new Intent(context, AjustesActivity.class);
            startActivity(intent);

        } else if (id == R.id.logout){
            System.exit(0);
        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout_main);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
