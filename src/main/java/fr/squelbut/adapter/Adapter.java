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

    default Adapter<I, O> adapt(Adapter<I, O> then) {
        return (I i, O o) -> then.apply(i, this.apply(i, o));
    }

    default <V> Adapter<I, O> adapt(Function<I, V> getter, BiConsumer<O, V> setter) {
        return adapt((I i, O o) -> {
            setter.accept(o, getter.apply(i));
            return o;
        });
    }

    default <V> Adapter<I, O> adapt(Supplier<V> getter, BiConsumer<O, V> setter) {
        Function<I, V> function = (I input) -> getter.get();
        return adapt(function, setter);
    }
}
