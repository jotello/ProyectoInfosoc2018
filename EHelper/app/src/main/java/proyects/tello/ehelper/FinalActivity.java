package proyects.tello.ehelper;

import android.annotation.SuppressLint;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import proyects.tello.ehelper.Entidades.Resultado;
import proyects.tello.ehelper.Entidades.Sintoma;
import proyects.tello.ehelper.Logica.Malaria;
import proyects.tello.ehelper.Logica.Zika;

public class FinalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context context;
    TextView nombreUser, rutUser;
    DrawerLayout drawerLayout;
    List<String> tagsPaciente = new ArrayList<>();
    List<String> sintomasPaciente = new ArrayList<>();
    List<Sintoma> listaFinalSintomas = new ArrayList<>();
    Float riesgoVistoMalaria;
    Float riesgoVistoZika;
    Integer tiempoSintomas;
    Integer tiempoIncubacion;

    Float porcentajeMalaria;
    String avisoMalaria;
    Float porcentajeZika;
    String avisoZika;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        context = getApplicationContext();
        tagsPaciente = (List<String>) getIntent().getSerializableExtra("TagsPaciente");
        sintomasPaciente = (List<String>) getIntent().getSerializableExtra("SintomasPaciente");
        crearListaFinalSintomas(tagsPaciente, sintomasPaciente);
        riesgoVistoMalaria = getIntent().getFloatExtra("RiesgoMalaria", (float) 0.1);
        riesgoVistoZika = getIntent().getFloatExtra("RiesgoZika", (float) 0.1);
        tiempoSintomas = getIntent().getIntExtra("TiempoSintomas", 1);
        tiempoIncubacion = getIntent().getIntExtra("TiempoIncubacion", 1);

        // Action bar y drawer layout
        Toolbar toolbar = findViewById(R.id.toolbar_final);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout_final);
        ActionBarDrawerToggle mToggle = new ActionBarDrawerToggle(
                this, drawerLayout,toolbar, R.string.open_dl, R.string.close_dl);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view_final);
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

        Button button = findViewById(R.id.button_volver_inicio);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });

        calcularMalaria(listaFinalSintomas, riesgoVistoMalaria, tiempoSintomas, tiempoIncubacion);
        calcularZika(listaFinalSintomas, riesgoVistoZika, tiempoSintomas, tiempoIncubacion);

        TextView avisoMalariaTv = findViewById(R.id.aviso_malaria);
        TextView avisoZikaTv = findViewById(R.id.aviso_zika);
        TextView porcentajeMalariaTv = findViewById(R.id.porcentaje_malaria);
        TextView porcentajeZikaTv = findViewById(R.id.porcentaje_zika);

        LinearLayout linearLayoutMalaria = findViewById(R.id.linear_malaria);
        LinearLayout linearLayoutZika = findViewById(R.id.linear_zika);
        setColorAvisos(linearLayoutMalaria, linearLayoutZika);

        avisoMalariaTv.setText(avisoMalaria);
        avisoZikaTv.setText(avisoZika);
        porcentajeMalariaTv.setText(String.format("%.0f%%", porcentajeMalaria * 100));
        porcentajeZikaTv.setText(String.format("%.0f%%", porcentajeZika * 100));
    }

    private void setColorAvisos(LinearLayout linearLayoutMalaria, LinearLayout linearLayoutZika) {
        //Deuda tecnica: Dejar este metodo escalable para muchas enfermedades
        //Parte Malaria
        if(porcentajeMalaria < 0.5){
            linearLayoutMalaria.setBackgroundColor(getResources().getColor(R.color.verde));
        } else if (porcentajeMalaria >= 0.5 && porcentajeMalaria < 0.8){
            linearLayoutMalaria.setBackgroundColor(getResources().getColor(R.color.amarillo));
        } else {
            linearLayoutMalaria.setBackgroundColor(getResources().getColor(R.color.rojo));
        }
        //Parte Zika
        if(porcentajeZika < 0.5){
            linearLayoutZika.setBackgroundColor(getResources().getColor(R.color.verde));
        } else if (porcentajeZika >= 0.5 && porcentajeZika < 0.8){
            linearLayoutZika.setBackgroundColor(getResources().getColor(R.color.amarillo));
        } else {
            linearLayoutZika.setBackgroundColor(getResources().getColor(R.color.rojo));
        }
    }

    private void crearListaFinalSintomas(List<String> tagsPaciente, List<String> sintomasPaciente) {
        for(String s: tagsPaciente){
            listaFinalSintomas.add(new Sintoma(s, "Tag"));
        }
        for (String s: sintomasPaciente){
            listaFinalSintomas.add(new Sintoma(s, "Excluyente"));
        }

    }

    private void calcularZika(List<Sintoma> listaFinalSintomas,
                              Float riesgoVistoZika, Integer tiempoSintomas, Integer tiempoIncubacion) {

        Zika zika = new Zika(tiempoIncubacion, tiempoSintomas, listaFinalSintomas, riesgoVistoZika, context);
        Resultado resultado = zika.Comportamiento();
        avisoZika = resultado.getMensaje();
        porcentajeZika = resultado.getPorcentaje();
    }

    private void calcularMalaria(List<Sintoma> listaFinalSintomas, Float riesgoVistoMalaria,
                                 Integer tiempoSintomas, Integer tiempoIncubacion) {

        Malaria malaria = new Malaria(tiempoIncubacion,tiempoSintomas, listaFinalSintomas, riesgoVistoMalaria , context);
        Resultado resultado = malaria.Comportamiento();
        avisoMalaria = resultado.getMensaje();
        porcentajeMalaria = resultado.getPorcentaje();

    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
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

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
