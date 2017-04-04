package fr.squelbut.adapter.bulk;

import fr.squelbut.adapter.TestA;
import fr.squelbut.adapter.TestB;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by raphael on 31/03/2017.
 */
public class AdapterTest {

    @Test
    public void should_map_all_fields() {
        TestA expected = new TestA(asList(1, "titi"), true, new String[]{"A", "B"});

        Adapter<TestA, TestB> adapter = Adapter
                .newAdapter(TestA.class, TestB.class)
                .addAdapter(TestA::getList, TestB::setListe)
                .addAdapter(TestA::getArray, TestB::setTableau)
                .addAdapter(TestA::isBool, TestB::setBooleen)
                .addAdapter(() -> 5, TestB::setExtra)
                .build();

        TestB actual = adapter.apply(expected, TestB::new);

        assertThat(actual.getListe()).isNotNull()
                                     .isNotEmpty()
                                     .isEqualTo(expected.getList());
        assertThat(actual.getTableau()).isNotNull()
                                       .isNotEmpty()
                                       .isEqualTo(expected.getArray());
        assertThat(actual.isBooleen()).isEqualTo(expected.isBool());
        assertThat(actual.getExtra()).isEqualTo(5);

    }
}
