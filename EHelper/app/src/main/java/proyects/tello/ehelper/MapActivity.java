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
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MapActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context context;
    Button button;
    TextView nombreUser, rutUser;

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
        Spinner spinnerRegion = findViewById(R.id.spinner_region);

        String[] paises = new String[] {"Bolivia", "Argentina"};
        String[] regiones = new String[]{"-"};
        String[] regionesArgentina = new String []{"Jujuy", "Salta", "Chaco", "Corrientes", "Formosa", "Misiones", ""};
        String[] regionesBolivia = new String []{"Oruro", "Potosi", "Pando", "Beni", "La Paz",
                "Tarija", "Chuquisaca", "Conchabamba", "Santa Cruz"};

        //Implemento el adapter con el contexto, layout, listaFrutas
        ArrayAdapter paisAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paises);
        ArrayAdapter regionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, regiones);
        //Cargo el spinner con los datos
        spinnerPais.setAdapter(paisAdapter);
        spinnerRegion.setAdapter(regionAdapter);

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
