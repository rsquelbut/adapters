package fr.squelbut.adapter.bulk;

import fr.squelbut.adapter.Adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by raphael on 31/03/2017.
 */
public class ListAdapter<I, O> implements Adapter<I, O> {

    private final Collection<Adapter<I, O>> transformers = new ArrayList<>();


    public static <I, O> ListAdapter<I, O> with(Class<I> input, Class<O> output) {
        return new ListAdapter<>();
    }

    @Override
    public O apply(I input, O output) {
        transformers.forEach(t -> t.apply(input, output));
        return output;
    }


    @Override
    public ListAdapter<I, O> adapt(Adapter<I, O> then) {
        transformers.add(then);
        return this;
    }


    @Override
    public <V> ListAdapter<I, O> adapt(Function<I, V> getter, BiConsumer<O, V> setter) {
        return adapt((I i, O o) -> {
            setter.accept(o, getter.apply(i));
            return o;
        });
    }

    @Override
    public <V> ListAdapter<I, O> adapt(Supplier<V> getter, BiConsumer<O, V> setter) {
        Function<I, V> function = (I input) -> getter.get();
        return adapt(function, setter);
    }
}
