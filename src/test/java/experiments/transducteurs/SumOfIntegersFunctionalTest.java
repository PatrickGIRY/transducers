package experiments.transducteurs;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static experiments.transducteurs.ImperativePipeline.reduce;
import static org.assertj.core.api.Assertions.assertThat;

public class SumOfIntegersFunctionalTest {

    @Test
    public void should_calculate_the_sum_of_integers() throws Exception {

        Collection<Integer> integers = Arrays.asList(1, 2, 3, 4);

        assertThat(reduce((sum, i) -> sum + i, 0, integers)).isEqualTo(10);

    }
}
