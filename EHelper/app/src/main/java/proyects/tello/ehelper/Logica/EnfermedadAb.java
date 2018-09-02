package proyects.tello.ehelper.Logica;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import proyects.tello.ehelper.Entidades.Resultado;
import proyects.tello.ehelper.Entidades.ResultadoVT;
import proyects.tello.ehelper.Entidades.Sintoma;
import proyects.tello.ehelper.Entidades.Zona;

public abstract class EnfermedadAb {

    //Atributos propios, inputs del usuario
    public int tiempoIncubacion;
    public int duracionSintomas;
    public List<Sintoma> sintomas = new ArrayList<>();

    //Atributos de la BD
    int totalSintomas ; //TODO : Calcular numero de Sintomas en la BD
    private List<Sintoma> verdaderosSintomas = new ArrayList<>();
    private List<Integer> intervaloIncubacion = new ArrayList<>();
    private List<Integer> intervaloSintomas = new ArrayList<>();
    float geografiaBD ;
    Context context;

    public void setTotalSintomas(int totalSintomas) {
        this.totalSintomas = totalSintomas;
    }

    void setVerdaderosSintomas(List<Sintoma> verdaderosSintomas) {
        this.verdaderosSintomas = verdaderosSintomas;
    }

    public void setIntervaloIncubacion(List<Integer> intervaloIncubacion) {
        this.intervaloIncubacion = intervaloIncubacion;
    }

    public void setIntervaloSintomas(List<Integer> intervaloSintomas) {
        this.intervaloSintomas = intervaloSintomas;
    }

    public void setGeografiaBD(float geografiaBD) {
        this.geografiaBD = geografiaBD;
    }

    public EnfermedadAb(int tiempoIncubacion, int duracionSintomas, List<Sintoma> sintomas, Context context) {
        this.tiempoIncubacion = tiempoIncubacion;
        this.duracionSintomas = duracionSintomas;
        this.sintomas = sintomas;
        this.context = context;
    }

    public float Formula(){
        ResultadoVT tagsObl_tagsOpc = ValidandoTags(); //(tagsObligatorios, tagsOpcionales)
        int tagsObligatorios = (int) tagsObl_tagsOpc.getTagsObligatorios();
        int tagsOpcionales = (int) tagsObl_tagsOpc.getTagsOpcionales(); //Allan matate

        float suma1 = tagsObligatorios + tagsOpcionales;
        float suma2 = totalSintomas + tagsOpcionales;
        float div = suma1 / suma2 ;

        float t_inc = TiempoIncubacion();
        float t_sint = TiempoSintomas();


        float formula = geografiaBD * t_inc * t_sint * div ;
        return formula;
    }

    protected float TiempoIncubacion(){
        if (tiempoIncubacion >= intervaloIncubacion.get(0) && tiempoIncubacion <= intervaloIncubacion.get(1)){
            return 1;
        }
        else{
            return (float) 0.3;
        }
    }

    protected float TiempoSintomas(){
        if (duracionSintomas >= intervaloSintomas.get(0) && duracionSintomas <= intervaloSintomas.get(1) ){
            return (float) 0.7;
        }
        else {
            return (float) 0.4;
        }
    }

    private ResultadoVT ValidandoTags(){
        //Lo que voy a hacer tiene complejidad O(n^2), osea es muy feo
        List <Sintoma> tagsValidados = new ArrayList<>();
        for (int j = 0; j < sintomas.size(); j++){
            Sintoma sint = sintomas.get(j);
            for(int i= 0; i < verdaderosSintomas.size(); i++){
                String n = verdaderosSintomas.get(i).getNombre();
                if(n.equals(sint.getNombre())){
                    tagsValidados.add(sint);
                }
            }
        }

        return ContandoTags(tagsValidados);
    }

    private ResultadoVT ContandoTags(List<Sintoma> tagsValidados){
        int contandorObligatorio = 0;
        int contadorOpcional = 0;

        for (Sintoma sint: tagsValidados){
            if(sint.getTipo().equals("Excluyente")){
                contandorObligatorio++;
            } else{
                contadorOpcional++;
            }
        }
        ResultadoVT resultadoVT = new ResultadoVT(contandorObligatorio, contadorOpcional);
        return resultadoVT;
    }

    abstract public Resultado Comportamiento();


}
