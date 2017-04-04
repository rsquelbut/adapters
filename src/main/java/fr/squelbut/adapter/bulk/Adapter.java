package fr.squelbut.adapter.bulk;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Created by raphael on 31/03/2017.
 */
public class Adapter<I, O> implements BiFunction<I, O, O> {

    private final List<? extends BiConsumer<I,O>> transformers;

    public Adapter(List<? extends BiConsumer<I,O>> transformers) {
        this.transformers = transformers;
    }

    public O apply(I input, O output) {
        transformers.forEach(t -> t.accept(input, output));
        return output;
    }

    public O apply(I input, Supplier<O> output) {
        return apply(input, output.get());
    }


    public static <I, O> AdapterBuilder<I, O> newAdapter(Class<I> i, Class<O> o) {
        return new AdapterBuilder();
    }
}
