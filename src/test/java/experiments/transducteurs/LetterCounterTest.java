package experiments.transducteurs;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import static experiments.transducteurs.ImperativePipeline.reduce;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class LetterCounterTest {

    @Test
    public void count_letters_in_sentence_with_reduce() {

        final BiFunction<Map<Character, Long>, Character, Map<Character, Long>> accLetter =
                (accumulator, letter) -> {
                    accumulator.put(letter, accumulator.getOrDefault(letter, 0L) + 1L);
                    return accumulator;
                };

        List<Character> listOfCharacters = listOfCharacters("Il était une fois une fonction reduce");

        Map<Character, Long> result = reduce(accLetter, new HashMap<>(), listOfCharacters);

        Map<Character, Long> expectedResult = new HashMap<>();
        expectedResult.put(' ', 6L);
        expectedResult.put('I', 1L);
        expectedResult.put('a', 1L);
        expectedResult.put('c', 2L);
        expectedResult.put('d', 1L);
        expectedResult.put('e', 4L);
        expectedResult.put('f', 2L);
        expectedResult.put('i', 3L);
        expectedResult.put('l', 1L);
        expectedResult.put('n', 4L);
        expectedResult.put('o', 3L);
        expectedResult.put('r', 1L);
        expectedResult.put('s', 1L);
        expectedResult.put('t', 3L);
        expectedResult.put('u', 3L);
        expectedResult.put('é', 1L);

        assertThat(result).isEqualTo(expectedResult);
    }

    private List<Character> listOfCharacters(String s) {
        return s.chars().mapToObj(c -> (char) c).collect(toList());
    }


}
