package proyects.tello.ehelper.Entidades;

import java.io.Serializable;

public class Zona implements Serializable{

    private int id;
    private String nombre;
    private String pais; //FK
    private Float riesgoZika;
    private Float riesgoMalaria;

    public Zona(String nombre, String pais, Float riesgoZika, Float riesgoMalaria) {
        this.nombre = nombre;
        this.pais = pais;
        this.riesgoZika = riesgoZika;
        this.riesgoMalaria = riesgoMalaria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Float getRiesgoZika() {
        return riesgoZika;
    }

    public void setRiesgoZika(Float riesgoZika) {
        this.riesgoZika = riesgoZika;
    }

    public Float getRiesgoMalaria() {
        return riesgoMalaria;
    }

    public void setRiesgoMalaria(Float riesgoMalaria) {
        this.riesgoMalaria = riesgoMalaria;
    }
}
