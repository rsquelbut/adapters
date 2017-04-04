package fr.squelbut.adapter.bulk;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by raphael on 01/04/2017.
 */
public class AdapterBuilder<I, O> {

    private List<BiConsumer<I, O>> fieldAdapters = new ArrayList<>();

    public <V> AdapterBuilder<I, O> addAdapter(Function<I, V> getter,
                                               BiConsumer<O, V> setter) {
        fieldAdapters.add(new FieldAdapter<>(getter, setter));
        return this;
    }

    public <V> AdapterBuilder<I, O> addAdapter(Supplier<V> getter,
                                               BiConsumer<O, V> setter) {
        return addAdapter((I i) -> getter.get(), setter);
    }

    public Adapter<I, O> build() {
        return new Adapter<>(fieldAdapters);
    }
}
