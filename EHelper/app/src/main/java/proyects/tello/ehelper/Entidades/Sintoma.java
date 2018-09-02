package proyects.tello.ehelper.Entidades;

import java.io.Serializable;

public class Sintoma implements Serializable{

    private String nombre;
    private String tipo;

    public Sintoma(String n, String t){
        this.nombre = n;
        this.tipo = t;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
