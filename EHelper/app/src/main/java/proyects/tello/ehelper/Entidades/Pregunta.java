package proyects.tello.ehelper.Entidades;

import java.io.Serializable;

public class Pregunta implements Serializable {

    private String Sintoma;
    String enunciado;
    private String alternativa1;
    private String alternativa2;

    public Pregunta(String sintoma, String enunciado, String alternativa1, String alternativa2) {
        Sintoma = sintoma;
        this.enunciado = enunciado;
        this.alternativa1 = alternativa1;
        this.alternativa2 = alternativa2;
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

    public String getSintoma() {
        return Sintoma;
    }

    public void setSintoma(String sintoma) {
        Sintoma = sintoma;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }
}
