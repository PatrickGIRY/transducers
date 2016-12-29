package experiments.transducteurs;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class SumOfIntegersImperativeTest {

    @Test
    public void should_calculate_the_sum_of_integers() throws Exception {

        Collection<Integer> integers = Arrays.asList(1, 2, 3, 4);
        int sum = 0;
        for (int i : integers) {
            sum += i;
        }

        assertThat(sum).isEqualTo(10);


    }
}
