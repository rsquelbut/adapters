package fr.squelbut.adapter;

/**
 * Created by raphael on 12/04/2017.
 */
public class Adapters {

    public static <I, O> Adapter<I, O> from(Class<I> input, Class<O> output) {
        return (I i, O o) -> o;
    }

    public static <I, C, O> BiAdapter<I, C, O> from(Class<I> input,
                                                    Class<C> context,
                                                    Class<O> output) {
        return (I i, C c, O o) -> o;
    }
}
