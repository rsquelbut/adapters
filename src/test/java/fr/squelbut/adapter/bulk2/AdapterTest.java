package fr.squelbut.adapter.bulk2;

import fr.squelbut.adapter.TestA;
import fr.squelbut.adapter.TestB;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by raphael on 04/04/2017.
 */
public class AdapterTest {

    @Test
    public void should_map_all_fields() {
        TestA expected = new TestA(asList(1, "titi"), true, new String[]{"A", "B"});

        Adapter<TestA, TestB> adapter = Adapter.with(TestA.class, TestB.class)
                .adapt(TestA::getList, TestB::setListe)
                .adapt(TestA::isBool, TestB::setBooleen)
                .adapt(TestA::getArray, TestB::setTableau)
                .adapt(() -> 5, TestB::setExtra);

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