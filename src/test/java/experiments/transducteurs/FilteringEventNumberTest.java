package experiments.transducteurs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static experiments.transducteurs.ImperativePipeline.reduce;
import static org.assertj.core.api.Assertions.assertThat;

public class FilteringEventNumberTest {

    @Test
    public void select_even_with_reduce() throws Exception {

        List<Integer> result = reduce((list, i) -> (i%2 == 0) ? appender(list, i) : list, new ArrayList<>(),
                Arrays.asList(1,2,3,4,5,6));
        assertThat(result).containsExactly(2,4,6);
    }

    @Test
    public void select_even_with_transducer() throws Exception {

        List<Integer> result = reduce(Transducer.<Integer>filter(i -> i % 2 == 0).apply(this::appender), new ArrayList<>(),
                Arrays.asList(1,2,3,4,5,6));
        assertThat(result).containsExactly(2,4,6);
    }


    private <R extends List<Integer>> R appender(R list, Integer e) {
        list.<Integer>add(e);
        return list;
    }
}
