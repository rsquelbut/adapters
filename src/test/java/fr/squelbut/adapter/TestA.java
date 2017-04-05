package fr.squelbut.adapter;

import java.util.List;

/**
 * Created by raphael on 31/03/2017.
 */
public class TestA {


    private List<Object> list;
    private boolean bool;
    private String[] array;
    private FieldA field;

    public TestA(List<Object> titi, boolean b, String[] strings, FieldA field) {
        this.array=strings;
        this.bool=b;
        this.list=titi;
        this.field=field;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }

    public FieldA getField() {
        return field;
    }

    public void setField(FieldA field) {
        this.field = field;
    }
}
