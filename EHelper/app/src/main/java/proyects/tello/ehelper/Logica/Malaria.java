package proyects.tello.ehelper.Logica;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import proyects.tello.ehelper.Entidades.Resultado;
import proyects.tello.ehelper.Entidades.Sintoma;
import proyects.tello.ehelper.Entidades.Zona;

public class Malaria extends EnfermedadAb{

    int totalSintomas = 5; //TODO : Calcular numero de Sintomas en la BD


    public Malaria(int tiempoIncubacion, int duracionSintomas, List<Sintoma> sintomas, float geografia, Context context) {
        super(tiempoIncubacion, duracionSintomas, sintomas, context);
        setGeografiaBD(geografia);
        List<Sintoma> verdaderosSintomasMalaria = new ArrayList<>();
        verdaderosSintomasMalaria.add(new Sintoma("Fiebre Baja (38 °C)", "Excluyente"));
        verdaderosSintomasMalaria.add(new Sintoma("Dolor leve de cabeza", "Excluyente"));
        //Tags
        verdaderosSintomasMalaria.add(new Sintoma("Escalofríos", "Tag"));
        verdaderosSintomasMalaria.add(new Sintoma("Anemia grave", "Tag"));
        verdaderosSintomasMalaria.add(new Sintoma("Sufrimiento respiratorio", "Tag"));
        setVerdaderosSintomas(verdaderosSintomasMalaria);
        List<Integer> intervaloIncubacionMalaria = new ArrayList<>();
        intervaloIncubacionMalaria.add(10); //Min
        intervaloIncubacionMalaria.add(15); //Max
        List<Integer> intervaloSintomasMalaria = new ArrayList<>();
        intervaloSintomasMalaria.add(2); //Min
        intervaloSintomasMalaria.add(3); //Max

        setIntervaloIncubacion(intervaloIncubacionMalaria);
        setIntervaloSintomas(intervaloSintomasMalaria);
        setTotalSintomas(totalSintomas);
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
