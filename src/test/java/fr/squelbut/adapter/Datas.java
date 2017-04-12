package fr.squelbut.adapter;

import java.util.Random;
import java.util.UUID;

import static java.util.Arrays.asList;

/**
 * Created by raphael on 06/04/2017.
 */
public class Datas {

    public static final Random RANDOM = new Random();

    public static TestA testA(){
        UUID uuid = UUID.randomUUID();
        FieldA field = new FieldA(uuid, Value.VALEUR2);
        return new TestA(asList(1, "titi"),
                         RANDOM.nextBoolean(),
                         new String[]{"A", "B"},
                         field);
    }
}
