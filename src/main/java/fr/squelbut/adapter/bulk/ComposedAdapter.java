package fr.squelbut.adapter.bulk;

import fr.squelbut.adapter.Adapter;

/**
 * Created by raphael on 31/03/2017.
 */
public class ComposedAdapter<I, O> implements Adapter<I, O> {

    private final Adapter<I, O> delegate;

    public ComposedAdapter(Adapter<I, O> adapter) {
        this.delegate = adapter;
    }

    public static <I,O> ComposedAdapter<I,O> with(Class<I> input, Class<O> output) {
        return new ComposedAdapter<>((I i, O o) -> o);
    }

    @Override
    public O apply(I input, O output) {
        delegate.apply(input, output);
        return output;
    }

}
