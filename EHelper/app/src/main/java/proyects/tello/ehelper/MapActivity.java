package proyects.tello.ehelper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import proyects.tello.ehelper.Entidades.Pais;
import proyects.tello.ehelper.Entidades.Pregunta;
import proyects.tello.ehelper.Entidades.Zona;

public class MapActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context context;
    Button button;
    List<Pais> paisesImportados = new ArrayList<>();
    List<Zona> zonasImportadas  = new ArrayList<>();
    List<String> sintomasPaciente = new ArrayList<>();
    List<String> tags  = new ArrayList<>();
    Float riesgoVistoMalaria ;
    Float riesgoVistoZika;

    EditText tiempoSintomas ;
    EditText tiempoIncubacion ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        context = getApplicationContext();

        tags = (List<String>) getIntent().getSerializableExtra("Tags");
        paisesImportados = (List<Pais>) getIntent().getSerializableExtra("Paises");
        zonasImportadas = (List<Zona>) getIntent().getSerializableExtra("Zonas");
        sintomasPaciente = (List<String>) getIntent().getSerializableExtra("SintomasPaciente");

        // Action bar y drawer layout
        Toolbar toolbar = findViewById(R.id.toolbar_map);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout_map);
        ActionBarDrawerToggle mToggle = new ActionBarDrawerToggle(
                this, drawerLayout,toolbar, R.string.open_dl, R.string.close_dl);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view_map);
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

        Spinner spinnerPais = findViewById(R.id.spinner_pais);
        List<String> paises = new ArrayList<>();

        cargarPaises(paises);
        String[] regiones = new String[]{"-", "-"}; //Esto se queda siempre

        List<String> regionesArgentina = new ArrayList<>();
        List<String> regionesBolivia = new ArrayList<>();
        List<String> regionesChile = new ArrayList<>();
        List<String> regionesPeru = new ArrayList<>();
        cargarZonas(regionesArgentina, regionesBolivia, regionesPeru, regionesChile);



        //Implemento el adapter con el contexto, layout,
        final ArrayAdapter paisAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paises);

        final ArrayAdapter regionAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, regiones);

        final ArrayAdapter boliviaAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, regionesBolivia);
        final ArrayAdapter argenAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, regionesArgentina);
        final ArrayAdapter peruAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, regionesPeru);
        final ArrayAdapter chileAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, regionesChile);

        //Cargo el spinner con los datos
        spinnerPais.setAdapter(paisAdapter);
        final Spinner spinnerRegion = findViewById(R.id.spinner_region);

        spinnerPais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String paisSeleccionado = (String) adapterView.getItemAtPosition(i);
                if(paisSeleccionado.equals("Peru")){ //PERU
                    spinnerRegion.setAdapter(peruAdapter);
                } else if(paisSeleccionado.equals("Argentina")){//ARGENTINA
                    spinnerRegion.setAdapter(argenAdapter);
                } else if (paisSeleccionado.equals("Bolivia")){ //BOLIVIA
                    spinnerRegion.setAdapter(boliviaAdapter);
                }
                else{ //CHILE
                    spinnerRegion.setAdapter(chileAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinnerRegion.setAdapter(regionAdapter);
            }
        });

        spinnerRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String zonaSeleccionada = (String) adapterView.getItemAtPosition(i);
                for(Zona z: zonasImportadas){
                    if(z.getNombre().equals(zonaSeleccionada)){
                        riesgoVistoMalaria = z.getRiesgoMalaria();
                        riesgoVistoZika = z.getRiesgoZika();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Hacer algo cuando ninguna region es seleccionada, como un mensaje retando al usuario
            }
        });

        tiempoSintomas = findViewById(R.id.tiempo_sintomas);
        tiempoIncubacion = findViewById(R.id.tiempo_incubacion);


        button = findViewById(R.id.button_siguiente_map);

    }

    public void exeGoTagAct(View view) {

        if(tiempoSintomas.getText().toString().equals("")){
            Toast.makeText(context, "Ingrese hace cuanto tiene sintomas. ", Toast.LENGTH_SHORT).show();
        } else{
            if(tiempoIncubacion.getText().toString().equals("")){
                Toast.makeText(context, "Ingrese el tiempo de incubación. ", Toast.LENGTH_SHORT).show();
            } else{
                Integer tiempoSintomasPaciente = Integer.valueOf(tiempoSintomas.getText().toString());
                Integer tiempoIncubacionPaciente = Integer.valueOf(tiempoIncubacion.getText().toString());

                Intent intent = new Intent(context, TagActivity.class);
                intent.putExtra("Tags", (Serializable) tags);
                intent.putExtra("RiesgoMalaria", riesgoVistoMalaria);
                intent.putExtra("RiesgoZika", riesgoVistoZika);
                intent.putExtra("SintomasPaciente", (Serializable) sintomasPaciente);
                intent.putExtra("TiempoSintomas", tiempoSintomasPaciente);
                intent.putExtra("TiempoIncubacion", tiempoIncubacionPaciente);
                startActivity(intent);
            }
        }

    }

    private void cargarZonas(List<String> regionesArgentina, List<String> regionesBolivia,
                             List<String> regionesPeru, List<String> regionesChile) {
        for(Zona z: zonasImportadas){
            if(z.getPais().equals("Argentina")){
                regionesArgentina.add(z.getNombre());
            }
            else if(z.getPais().equals("Bolivia")){
                regionesBolivia.add(z.getNombre());
            }
            else if(z.getPais().equals("Chile")){
                regionesChile.add(z.getNombre());
            }  else if (z.getPais().equals("Peru")){
                regionesPeru.add(z.getNombre());
            }
        }
    }

    private void cargarPaises(List<String> paises) {
        for(Pais p: paisesImportados){
            paises.add(p.getNombre());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /**
         MenuInflater menuInflater = getMenuInflater();
         menuInflater.inflate(R.menu.menu_search, menu);
         MenuItem item = menu.findItem(R.id.app_bar_search);
         SearchView searchView = (SearchView) item.getActionView();
         **/
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_map);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if ( id == R.id.home){
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.ajustes) {
            Intent intent = new Intent(context, AjustesActivity.class);
            startActivity(intent);

        } else if (id == R.id.logout){
            // jjijijij
        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout_map);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
