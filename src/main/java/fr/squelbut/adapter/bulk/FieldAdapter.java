package fr.squelbut.adapter.bulk;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Created by raphael on 31/03/2017.
 */
class FieldAdapter<I, O> implements BiConsumer<I, O> {

    private final BiConsumer<I, O> delegate;

    public <V> FieldAdapter(Function<I, V> getter, BiConsumer<O, V> setter) {
        this.delegate = (I i, O o) -> setter.accept(o, getter.apply(i));
    }

    @Override
    public void accept(I input, O output) {
        delegate.accept(input, output);
    }

}
