package fr.squelbut.adapter.bulk;

import fr.squelbut.adapter.*;
import org.junit.Test;

import java.util.UUID;
import java.util.function.Function;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by raphael on 04/04/2017.
 */
public class ComposedAdapterTest {

    @Test
    public void should_map_all_fields() {
        UUID uuid = UUID.randomUUID();
        FieldA field = new FieldA(uuid, Value.VALEUR2);
        TestA expected = new TestA(asList(1, "titi"),
                                   true,
                                   new String[]{"A", "B"},
                                   field);

        Function<TestA, FieldB> testA2FieldB = ComposedAdapter
                .with(FieldA.class, FieldB.class)
                .adapt(f -> f.getUuid().toString(),
                       FieldB::setUuid)
                .adapt(f -> f.getValue().name(),
                       FieldB::setValue)
                .changeInput(TestA::getField)
                .toFunction(FieldB::new);

        Adapter<TestA, TestB> adapter =
                ComposedAdapter.with(TestA.class, TestB.class)
                               .adapt(TestA::getList, TestB::setListe)
                               .adapt(TestA::isBool, TestB::setBooleen)
                               .adapt(TestA::getArray, TestB::setTableau)
                               .adapt(testA2FieldB, TestB::setField)
                               .adapt(() -> 5, TestB::setExtra);


        TestB actual1 = new TestB();
        TestB actual = adapter.apply(expected, actual1);

        assertThat(actual.getListe()).isNotNull()
                                     .isNotEmpty()
                                     .isEqualTo(expected.getList());
        assertThat(actual.getTableau()).isNotNull()
                                       .isNotEmpty()
                                       .isEqualTo(expected.getArray());
        assertThat(actual.isBooleen()).isEqualTo(expected.isBool());
        assertThat(actual.getExtra()).isEqualTo(5);
        assertThat(actual.getField().getUuid())
                .isEqualTo(expected.getField().getUuid().toString());
        assertThat(actual.getField().getValue())
                .isEqualTo(expected.getField().getValue().toString());

    }

}