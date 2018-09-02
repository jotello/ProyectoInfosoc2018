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
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AjustesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        context = getApplicationContext();

        // Action bar y drawer layout
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout_ajustes);
        ActionBarDrawerToggle mToggle = new ActionBarDrawerToggle(
                this, drawerLayout,toolbar, R.string.open_dl, R.string.close_dl);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view_ajustes);
        navigationView.setNavigationItemSelectedListener(this);

        final EditText nombre_user = findViewById(R.id.nombre_medico_ajustes);
        final EditText rut_user = findViewById(R.id.rut_medico_ajustes);

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
            nombre_user.setHint(prefs.getString("nombreMedico", "a"));

            TextView nav_rut = hView.findViewById(R.id.rut_user);
            nav_rut.setText(prefs.getString("rutMedico", ""));
            rut_user.setHint(prefs.getString("rutMedico", ""));
        }

        LinearLayout seccionNombre = findViewById(R.id.seccion_nombre);
        LinearLayout seccionRut = findViewById(R.id.seccion_rut);

        final Button buttonGuardar = findViewById(R.id.button_guardar);

        seccionNombre.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               buttonGuardar.setVisibility(View.VISIBLE); //Hacemos visible el boton para guardar
           }
        });

       seccionRut.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               buttonGuardar.setVisibility(View.VISIBLE);
           }
       });

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String RUT = rut_user.getText().toString();
                String NOMBRE = nombre_user.getText().toString();
                if (!RUT.equals("")) {
                    prefs.edit().putString("rutMedico", RUT).apply();
                }
                if (!NOMBRE.equals("")){
                    prefs.edit().putString("nombreMedico", NOMBRE).apply();
                }


                Toast.makeText(context,
                        R.string.mensaje_actualizado, Toast.LENGTH_LONG).show();
                buttonGuardar.setVisibility(View.GONE);
            }
        });

        TextView borrarCuenta = findViewById(R.id.borrar_cuenta);
        borrarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context, SignActivity.class);
                startActivity(intent);
            }
        });








    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if ( id == R.id.home){
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.ajustes) {
            //Ya esta en ajustes
        } else if (id == R.id.logout){
            System.exit(0);
        }
        DrawerLayout drawer =  findViewById(R.id.drawer_layout_ajustes);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_ajustes);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
