package proyects.tello.ehelper.Logica;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import proyects.tello.ehelper.Entidades.Resultado;
import proyects.tello.ehelper.Entidades.Sintoma;
import proyects.tello.ehelper.Entidades.Zona;

public class Zika extends EnfermedadAb {

    public Zika(int tiempoIncubacion, int duracionSintomas, List<Sintoma> sintomas, float geografia, Context context) {
        super(tiempoIncubacion, duracionSintomas, sintomas, context);
        setGeografiaBD(geografia);
        List<Sintoma> verdaderosSintomasZika = new ArrayList<>();
        verdaderosSintomasZika.add(new Sintoma("Vómito", "Excluyente"));
        verdaderosSintomasZika.add(new Sintoma("Dolor leve articulaciones", "Excluyente"));
        verdaderosSintomasZika.add(new Sintoma("Conjuntivitis", "Excluyente"));
        verdaderosSintomasZika.add(new Sintoma("Ojos Rojos", "Excluyente"));
        verdaderosSintomasZika.add(new Sintoma("Cefalea", "Excluyente"));
        verdaderosSintomasZika.add(new Sintoma("Náuseas", "Excluyente"));
        //Tags de Zika
        verdaderosSintomasZika.add(new Sintoma("Artritis o Artralgia", "Tag"));
        verdaderosSintomasZika.add(new Sintoma("Falta de apetito", "Tag"));
        verdaderosSintomasZika.add(new Sintoma("Diarrea", "Tag"));
        verdaderosSintomasZika.add(new Sintoma("Dolor abdominal", "Tag"));
        verdaderosSintomasZika.add(new Sintoma("Erupciones con puntos rojos y blancos en la piel", "Tag"));
        verdaderosSintomasZika.add(new Sintoma("Sensibilidad a la luz", "Tag"));
        verdaderosSintomasZika.add(new Sintoma("Aftas", "Tag"));

        setVerdaderosSintomas(verdaderosSintomasZika);

        List<Integer> intervaloIncubacionZika = new ArrayList<>();
        intervaloIncubacionZika.add(4); // Min
        intervaloIncubacionZika.add(5); // Max
        setIntervaloIncubacion(intervaloIncubacionZika);

        List<Integer> intervaloSintomasZika = new ArrayList<>();
        intervaloSintomasZika.add(2); //Min
        intervaloSintomasZika.add(7); //Max
        setIntervaloSintomas(intervaloSintomasZika);
        setTotalSintomas(13);

    }

    @Override
    public Resultado Comportamiento() {
        int tamaño = this.sintomas.size();
        if(tamaño != 0){
            return new Resultado(Formula(), "Sin warning");
        } else{
            return new Resultado((float) (geografiaBD * TiempoSintomas() * TiempoIncubacion() * 0.4),
                    "No tiene sintomas, pero puede tener esta enfermedad ");

        }
    }
}
