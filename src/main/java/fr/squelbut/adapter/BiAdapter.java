package fr.squelbut.adapter;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by raphael on 05/04/2017.
 */
@FunctionalInterface
public interface BiAdapter<I, C, O> {

    O apply(I input, C context, O output);


    default O apply(I input, C context, Supplier<O> output) {
        return apply(input, context, output.get());
    }

    default BiAdapter<I, C, O> then(BiAdapter<I, C, O> then) {
        return (I i, C context, O o) ->
                then.apply(i, context, this.apply(i, context, o));
    }

    default BiAdapter<I, C, O> before(BiAdapter<I, C, O> before) {
        return (I i, C context, O o) ->
                this.apply(i, context, before.apply(i, context, o));
    }

    default BiAdapter<I, C, O> then(Adapter<I, O> then) {
        return (I i, C context, O o) ->
                then.apply(i, this.apply(i, context, o));
    }

    default BiAdapter<I, C, O> before(Adapter<I, O> before) {
        return (I i, C context, O o) ->
                this.apply(i, context, before.apply(i, o));
    }

    default <V> BiAdapter<I, C, O> adapt(BiFunction<I, C, V> getter,
                                         BiConsumer<O, V> setter) {
        return then((I i, C context, O o) -> {
            setter.accept(o, getter.apply(i, context));
            return o;
        });
    }

    default <V> BiAdapter<I, C, O> adapt(Function<I, V> getter,
                                         BiConsumer<O, V> setter) {
        BiFunction<I, C, V> function = (I i, C context) -> getter.apply(i);
        return adapt(function, setter);
    }

    default <V> BiAdapter<I, C, O> adapt(Supplier<V> getter,
                                         BiConsumer<O, V> setter) {
        BiFunction<I, C, V> function = (I i, C context) -> getter.get();
        return adapt(function, setter);
    }

    default Adapter<I, O> toSimple(C context) {
        return (I i, O o) -> this.apply(i, context, o);
    }

}
