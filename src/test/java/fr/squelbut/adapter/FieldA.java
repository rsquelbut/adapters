package fr.squelbut.adapter;

import java.util.UUID;

/**
 * Created by raphael on 05/04/2017.
 */
public class FieldA {

    private UUID uuid;
    private Value value;

    public FieldA(UUID uuid, Value value) {
        this.uuid = uuid;
        this.value = value;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
