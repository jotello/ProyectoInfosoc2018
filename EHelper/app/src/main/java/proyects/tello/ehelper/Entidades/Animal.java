package proyects.tello.ehelper.Entidades;

public class Animal {

    private String nombre;
    private String raza;
    private Integer edad;

    public Animal(){
        // Constructor vacio
    }

    public Animal(String nombreA, String razaA, Integer edadA){
        this.nombre = nombreA;
        raza = razaA;
        edad = edadA;
        // Constructor de otra manera
    }

    public static void main(String[] args){
        //Cosas que pasan cuando se ejecuta una instancia de la clase Animal
    }

    static void hacerRuido(){
        System.out.println("\"Guau! Guau!\" ");
    }

    public void setNombre(String nombreNuevo){
        nombre = nombreNuevo;
    }

    public void setRaza(String razaNueva){
        raza = razaNueva;
    }

    public void setEdad(Integer edadNueva){
        edad = edadNueva;
    }

    public String getNombre(){
        return nombre;
    }

    public String getRaza() {
        return raza;
    }

    public Integer getEdad() {
        return edad;
    }
}
