package fr.squelbut.adapter.bulk2;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by raphael on 31/03/2017.
 */
class Adapter<I, O> implements BiFunction<I, O, O> {

    private final BiFunction<I, O, O> adapter;

    public Adapter(BiFunction<I, O, O> adapter) {
        this.adapter = adapter;
    }

    public static <I,O> Adapter<I,O> with(Class<I> input, Class<O> output) {
        return new Adapter<>((I i, O o) -> o);
    }

    public Adapter<I, O> adapt(BiFunction<I, O, O> then) {
        BiFunction<I, O, O> newBifunction = (I i, O o) -> {
            O applied = adapter.apply(i, o);
            return then.apply(i, applied);
        };
        return new Adapter(newBifunction);
    }

    public <V> Adapter<I, O> adapt(Function<I, V> getter, BiConsumer<O, V> setter) {
        BiFunction<I, O, O> newBifunction = compose(getter, setter);
        return adapt(newBifunction);
    }

    public <V> Adapter<I, O> adapt(Supplier<V> getter, BiConsumer<O, V> setter) {
        return adapt((I i) -> getter.get(), setter);
    }

    public O apply(I input, O output) {
        adapter.apply(input, output);
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
