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
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import proyects.tello.ehelper.Adapters.QuestionAdapter;
import proyects.tello.ehelper.Entidades.Pais;
import proyects.tello.ehelper.Entidades.Pregunta;
import proyects.tello.ehelper.Entidades.Zona;

public class ExcActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context context;
    Button button;
    List<Pregunta> myDataSet = new ArrayList<Pregunta>();
    List<String> sintomasPaciente = new ArrayList<>();
    List<String> tags = new ArrayList<>();
    List<Pais> paises = new ArrayList<>();
    List<Zona> zonas = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exc);
        context = getApplicationContext();

        myDataSet = (ArrayList<Pregunta>) getIntent().getSerializableExtra("Preguntas");
        tags = (List<String>) getIntent().getSerializableExtra("Tags");
        paises = (List<Pais>) getIntent().getSerializableExtra("Paises");
        zonas = (List<Zona>) getIntent().getSerializableExtra("Zonas");

        Toolbar toolbar = findViewById(R.id.toolbar_exc);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout_exc);
        ActionBarDrawerToggle mToggle = new ActionBarDrawerToggle(
                this, drawerLayout,toolbar, R.string.open_dl, R.string.close_dl);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view_exc);
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

        // Instanciacion de RecyclerView
        RecyclerView mRecyclerView = findViewById(R.id.rec_exc);
        // Usar esta línea para mejorar el rendimiento
        // si sabemos que el contenido no va a afectar
        // el tamaño del RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // Nuestro RecyclerView usará un linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        final QuestionAdapter mAdapter = new QuestionAdapter(context, myDataSet);
        mRecyclerView.setAdapter(mAdapter);

        button = findViewById(R.id.button_siguiente_exc);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sintomasPaciente = mAdapter.getSintomasPaciente();
                Intent intent = new Intent(context, MapActivity.class);
                intent.putExtra("Paises", (Serializable) paises);
                intent.putExtra("Tags", (Serializable) tags);
                intent.putExtra("Zonas", (Serializable) zonas);
                intent.putExtra("SintomasPaciente", (Serializable) sintomasPaciente);
                startActivity(intent);
            }
        });
        

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_exc);
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

        if(id == R.id.home){
           Intent intent = new Intent(context, MainActivity.class);
           startActivity(intent);
        }
        if(id == R.id.ajustes){
           Intent intent = new Intent(context, AjustesActivity.class);
           startActivity(intent);
        }
        else{

        }
        DrawerLayout drawer =  findViewById(R.id.drawer_layout_exc);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
