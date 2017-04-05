package fr.squelbut.adapter.bulk;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by raphael on 31/03/2017.
 */
public class ListAdapter<I, O> implements BiFunction<I, O, O> {

    private final List<BiFunction<I, O, O>> transformers = new ArrayList<>();


    public static <I, O> ListAdapter<I, O> with(Class<I> input, Class<O> output) {
        return new ListAdapter<>();
    }

    public ListAdapter<I, O> adapt(BiFunction<I, O, O> then) {
        transformers.add(then);
        return this;
    }

    public <V> ListAdapter<I, O> adapt(Function<I, V> getter, BiConsumer<O, V> setter) {
        BiFunction<I, O, O> newBifunction = compose(getter, setter);
        return adapt(newBifunction);
    }

    public <V> ListAdapter<I, O> adapt(Supplier<V> getter, BiConsumer<O, V> setter) {
        return adapt((I i) -> getter.get(), setter);
    }

    @Override
    public O apply(I input, O output) {
        transformers.forEach(t -> t.apply(input, output));
        return output;
    }

    public O apply(I input, Supplier<O> output) {
        return apply(input, output.get());
    }

    private <V> BiFunction<I, O, O> compose(Function<I, V> getter, BiConsumer<O, V> setter) {
        return (I i, O o) -> {
            setter.accept(o, getter.apply(i));
            return o;
        };
    }

}
