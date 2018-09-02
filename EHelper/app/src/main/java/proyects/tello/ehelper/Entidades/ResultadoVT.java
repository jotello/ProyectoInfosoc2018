package proyects.tello.ehelper.Entidades;

import java.io.Serializable;

public class ResultadoVT implements Serializable{
    private int tagsObligatorios;
    private int tagsOpcionales;

    public ResultadoVT(int tagsObligatorios, int tagsOpcionales) {
        this.tagsObligatorios = tagsObligatorios;
        this.tagsOpcionales = tagsOpcionales;
    }

    public float getTagsObligatorios() {
        return tagsObligatorios;
    }

    public void setTagsObligatorios(int tagsObligatorios) {
        this.tagsObligatorios = tagsObligatorios;
    }

    public float getTagsOpcionales() {
        return tagsOpcionales;
    }

    public void setTagsOpcionales(int tagsOpcionales) {
        this.tagsOpcionales = tagsOpcionales;
    }
}
