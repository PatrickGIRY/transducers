package experiments.transducteurs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

class ImperativePipeline {

    private ImperativePipeline() {}

    static  <T> List<T> filter(Predicate<T> predicate, Collection<T> collection) {
        List<T> accumulator = new ArrayList<>();
        for(T element: collection) {
            if (predicate.test(element)) {
                accumulator.add(element);
            }
        }
        return accumulator;
    }

    static <T, R> List<R> map(Function<T, R> mapper, Collection<T> collection) {
        List<R> accumulator = new ArrayList<>();
        for (T element: collection) {
            accumulator.add(mapper.apply(element));
        }
        return accumulator;
    }

    static <T, R> R reduce(BiFunction<R, T, R> reducer, R identity, Collection<T> collection) {
        R accumulator = identity;
        for (T element: collection) {
            accumulator = reducer.apply(accumulator, element);
        }
        return accumulator;
    }
}
