package fr.squelbut.adapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by raphael on 31/03/2017.
 */
public class TestB {

    private List<Object> liste;
    private boolean booleen;
    private String[] tableau;
    private FieldB field;
    private Integer extra;

    public List<Object> getListe() {
        return liste;
    }

    public void setListe(List<Object> liste) {
        this.liste = liste;
    }

    public boolean isBooleen() {
        return booleen;
    }

    public void setBooleen(boolean booleen) {
        this.booleen = booleen;
    }

    public String[] getTableau() {
        return Arrays.copyOf(tableau, tableau.length);
    }

    public void setTableau(String[] tableau) {
        if (tableau == null) {
            return;
        }
        this.tableau = Arrays.copyOf(tableau, tableau.length);
    }

    public Integer getExtra() {
        return extra;
    }

    public void setExtra(Integer extra) {
        this.extra = extra;
    }

    public FieldB getField() {
        return field;
    }

    public void setField(FieldB field) {
        this.field = field;
    }
}
