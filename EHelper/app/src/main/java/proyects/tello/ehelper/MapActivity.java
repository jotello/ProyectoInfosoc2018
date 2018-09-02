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
import android.widget.Spinner;
import android.widget.TextView;

public class MapActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context context;
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        context = getApplicationContext();

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


        final String[] paises = new String[] {"Bolivia", "Argentina"};
        String[] regiones = new String[]{"-", "-"};
        String[] regionesArgentina = new String []{"Jujuy", "Salta", "Chaco", "Corrientes",
                "Formosa", "Misiones", "Entre Ríos", "Buenos Aires", "Santa Fe", "Santiago del Estero"
        , "Tucuman", "Catamarca", "La Rioja", "Cordoba", "San Juan", "San Luis", "Mendoza", "La Pampa", "Neuquen",
        "Rio Negro", "Chubut", "Santa Cruz"};
        String[] regionesBolivia = new String []{"Oruro", "Potosi", "Pando", "Beni", "La Paz",
                "Tarija", "Chuquisaca", "Conchabamba", "Santa Cruz"};

        //Implemento el adapter con el contexto, layout,
        final ArrayAdapter paisAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paises);
        final ArrayAdapter regionAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, regiones);
        final ArrayAdapter boliviaAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, regionesBolivia);
        final ArrayAdapter argenAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, regionesArgentina);

        //Cargo el spinner con los datos
        spinnerPais.setAdapter(paisAdapter);


        spinnerPais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0){
                    Spinner spinnerRegion = findViewById(R.id.spinner_region);
                    spinnerRegion.setAdapter(boliviaAdapter);
                } else{
                    Spinner spinnerRegion = findViewById(R.id.spinner_region);
                    spinnerRegion.setAdapter(argenAdapter);
                }
            }
        });

        button = findViewById(R.id.button_siguiente_map);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FinalActivity.class);
                startActivity(intent);
            }
        });


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
