package fr.squelbut.adapter.bulk;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by raphael on 31/03/2017.
 */
public class ComposedAdapter<I, O> implements BiFunction<I, O, O> {

    private final BiFunction<I, O, O> delegate;

    public ComposedAdapter(BiFunction<I, O, O> adapter) {
        this.delegate = adapter;
    }

    public static <I,O> ComposedAdapter<I,O> with(Class<I> input, Class<O> output) {
        return new ComposedAdapter<>((I i, O o) -> o);
    }

    public ComposedAdapter<I, O> adapt(BiFunction<I, O, O> then) {
        BiFunction<I, O, O> newBifunction = (I i, O o) -> {
            O applied = delegate.apply(i, o);
            return then.apply(i, applied);
        };
        return new ComposedAdapter<>(newBifunction);
    }

    public <V> ComposedAdapter<I, O> adapt(Function<I, V> getter, BiConsumer<O, V> setter) {
        BiFunction<I, O, O> newBifunction = compose(getter, setter);
        return adapt(newBifunction);
    }

    public <V> ComposedAdapter<I, O> adapt(Supplier<V> getter, BiConsumer<O, V> setter) {
        return adapt((I i) -> getter.get(), setter);
    }

    @Override
    public O apply(I input, O output) {
        delegate.apply(input, output);
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
