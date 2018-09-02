package proyects.tello.ehelper.Entidades;

public class Enfermedad {

    private String nombre;
    private int tiempoIncubacionMin ;
    private int tiempoIncubacionMax;
    private int tiempoSintomasMin;
    private int tiempoSintomasMax;

    public Enfermedad(String nombre, int tiempoIncubacionMin, int tiempoIncubacionMax,
                      int tiempoSintomasMin, int tiempoSintomasMax) {
        this.nombre = nombre;
        this.tiempoIncubacionMin = tiempoIncubacionMin;
        this.tiempoIncubacionMax = tiempoIncubacionMax;
        this.tiempoSintomasMin = tiempoSintomasMin;
        this.tiempoSintomasMax = tiempoSintomasMax;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempoIncubacionMin() {
        return tiempoIncubacionMin;
    }

    public void setTiempoIncubacionMin(int tiempoIncubacionMin) {
        this.tiempoIncubacionMin = tiempoIncubacionMin;
    }

    public int getTiempoIncubacionMax() {
        return tiempoIncubacionMax;
    }

    public void setTiempoIncubacionMax(int tiempoIncubacionMax) {
        this.tiempoIncubacionMax = tiempoIncubacionMax;
    }

    public int getTiempoSintomasMin() {
        return tiempoSintomasMin;
    }

    public void setTiempoSintomasMin(int tiempoSintomasMin) {
        this.tiempoSintomasMin = tiempoSintomasMin;
    }

    public int getTiempoSintomasMax() {
        return tiempoSintomasMax;
    }

    public void setTiempoSintomasMax(int tiempoSintomasMax) {
        this.tiempoSintomasMax = tiempoSintomasMax;
    }
}
