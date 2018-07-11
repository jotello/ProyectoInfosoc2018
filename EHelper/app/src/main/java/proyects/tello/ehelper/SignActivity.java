package proyects.tello.ehelper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignActivity extends AppCompatActivity {

    EditText campoNombre, campoRut;
    String NOMBRE, RUT;
    Button button;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        context = getApplicationContext();

        final SharedPreferences prefs =
                context.getSharedPreferences("proyects.tello.ehelper", Context.MODE_PRIVATE);


        campoNombre = findViewById(R.id.nombre_sign);
        campoRut = findViewById(R.id.rut_sign);

        button = findViewById(R.id.button_sign);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NOMBRE = campoNombre.getText().toString();
                prefs.edit().putString("nombreMedico", NOMBRE).apply();
                RUT = campoRut.getText().toString();
                prefs.edit().putString("rutMedico", RUT).apply();
                Toast.makeText(context,
                        R.string.mensaje_completado, Toast.LENGTH_LONG).show();

                exeMain(); //Nose porque chucha tuve que hace un metodo para iniciar la MainActivity
            }
        });
    }

    void exeMain(){
        Intent intent = null;
        intent = new Intent(context, MainActivity.class);
        startActivity(intent);
    }
}
