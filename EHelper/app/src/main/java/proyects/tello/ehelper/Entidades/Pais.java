package proyects.tello.ehelper.Entidades;

import java.io.Serializable;

public class Pais implements Serializable {

    private String nombre;

    public Pais(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
