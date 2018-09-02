package proyects.tello.ehelper.Entidades;

import java.io.Serializable;

public class Resultado implements Serializable {
    float porcentaje;
    String mensaje;

    public Resultado(float porcentaje, String mensaje) {
        this.porcentaje = porcentaje;
        this.mensaje = mensaje;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
