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
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import proyects.tello.ehelper.Adapters.TagAdapter;

public class TagActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    Button button;
    RecyclerView recyclerView;
    Context context;
    List<String> myDataSet = new ArrayList<>();
    TagAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);
        context = getApplicationContext();

        // Action bar y drawer layout
        Toolbar toolbar = findViewById(R.id.toolbar_tag);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout_tag);
        ActionBarDrawerToggle mToggle = new ActionBarDrawerToggle(
                this, drawerLayout,toolbar, R.string.open_dl, R.string.close_dl);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view_tag);
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
        recyclerView = findViewById(R.id.rec_tag);
        // Usar esta línea para mejorar el rendimiento
        // si sabemos que el contenido no va a afectar
        // el tamaño del RecyclerView
        recyclerView.setHasFixedSize(true);

        // Nuestro RecyclerView usará un linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Asociamos un adapter e inicializamos un dataSet de prueba
        myDataSet.add("Parches de sangre bajo la piel");
        myDataSet.add("Disminución de apetito");
        myDataSet.add("Sarpullido");
        myDataSet.add("Manchas rojas");
        myDataSet.add("Puntos púrpuras en la piel");
        myDataSet.add("Picazón");
        myDataSet.add("Artritis o Artralgia");
        myDataSet.add("Diarrea");
        myDataSet.add("Dolor abdominal");
        myDataSet.add("Orina de color oscuro");
        myDataSet.add("Vomito");
        myDataSet.add("Hemorragia oral");
        myDataSet.add("Hemorragia nasal");
        myDataSet.add("Hemorragia ocular");
        myDataSet.add("Hemorragia gastrica");
        myDataSet.add("Escalofrios");
        myDataSet.add("Anemia grave");
        myDataSet.add("Sufrimiento respiratorio");

        mAdapter = new TagAdapter(context, myDataSet);
        recyclerView.setAdapter(mAdapter);

        button = findViewById(R.id.button_siguiente_tag);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MapActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_tag);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
        else if(id == R.id.ajustes){
            Intent intent = new Intent(context, AjustesActivity.class);
            startActivity(intent);
        }
        else{

        }
        DrawerLayout drawer =  findViewById(R.id.drawer_layout_tag);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
