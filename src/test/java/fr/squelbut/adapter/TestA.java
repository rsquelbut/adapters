package fr.squelbut.adapter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by raphael on 31/03/2017.
 */
public class TestA {


    private List<Object> list;
    private boolean bool;
    private String[] array;

    public TestA(List<Object> titi, boolean b, String[] strings) {
        this.array=strings;
        this.bool=b;
        this.list=titi;
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
}
