package fr.squelbut.adapter;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by raphael on 05/04/2017.
 */
@FunctionalInterface
public interface Adapter<I, O> extends BiFunction<I, O, O> {

    O apply(I i, O o);

    default O apply(I input, Supplier<O> output) {
        return apply(input, output.get());
    }

    default Adapter<I, O> then(Adapter<I, O> then) {
        return (I i, O o) -> then.apply(i, this.apply(i, o));
    }

    default Adapter<I, O> before(Adapter<I, O> then) {
        return (I i, O o) -> this.apply(i, then.apply(i, o));
    }

    default <C, V> BiAdapter<I, C, O> then(BiFunction<I, C, V> getter,
                                           BiConsumer<O, V> setter) {
        BiAdapter<I, C, O> biAdapter = (I i, C c, O o) -> o;
        return biAdapter.before(this);
    }

    default <V> Adapter<I, O> adapt(Function<I, V> getter, BiConsumer<O, V> setter) {
        return then((I i, O o) -> {
            setter.accept(o, getter.apply(i));
            return o;
        });
    }

    default <V> Adapter<I, O> adapt(Supplier<V> getter, BiConsumer<O, V> setter) {
        Function<I, V> function = (I input) -> getter.get();
        return adapt(function, setter);
    }

    default <I2> Adapter<I2, O> changeInput(Function<I2, I> toOriginalInput) {
        return (I2 changedInput, O output) -> {
            I originalInput = toOriginalInput.apply(changedInput);
            return this.apply(originalInput, output);
        };
    }

    default Function<I, O> toFunction(Supplier<O> output) {
        return (I i) -> apply(i, output.get());
    }
}
