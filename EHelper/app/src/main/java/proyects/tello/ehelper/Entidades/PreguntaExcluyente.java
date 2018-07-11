package proyects.tello.ehelper.Entidades;

public class PreguntaExcluyente {

    private String enunciado;
    private String alternativa1;
    private String alternativa2;

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getAlternativa1() {
        return alternativa1;
    }

    public void setAlternativa1(String alternativa1) {
        this.alternativa1 = alternativa1;
    }

    public String getAlternativa2() {
        return alternativa2;
    }

    public void setAlternativa2(String alternativa2) {
        this.alternativa2 = alternativa2;
    }

    public PreguntaExcluyente (String enun, String alt1, String alt2){
        enunciado = enun;
        alternativa1 = alt1;
        alternativa2 = alt2;
    }



}
