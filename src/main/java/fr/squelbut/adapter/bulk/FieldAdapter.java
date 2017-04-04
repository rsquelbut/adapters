package fr.squelbut.adapter.bulk;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by raphael on 31/03/2017.
 */
class FieldAdapter<I, O> implements BiConsumer<I, O> {

    private final BiConsumer<I, O> adapter;

    public <V> FieldAdapter(Function<I, V> getter, BiConsumer<O, V> setter) {
        BiConsumer<I, O> adapter = (I i, O o) -> setter
                .accept(o, getter.apply(i));
        this.adapter = adapter;
    }

    public void accept(I input, O output) {
        adapter.accept(input, output);
    }

}
