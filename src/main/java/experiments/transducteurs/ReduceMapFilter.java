package experiments.transducteurs;

import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import static experiments.transducteurs.Pipeline.reduce;

class ReduceMapFilter {

    private ReduceMapFilter() {
    }

    static <T, U, R extends Collection<U>> R map(Function<T, U> mapper, R result, Collection<T> collection) {
        BiFunction<R, T, R> mapReducer = (accumulator, item) -> {
            accumulator.add(mapper.apply(item));
            return accumulator;
        };
        return reduce(mapReducer, result, CollectionUtils::addAll, collection);
    }

    static <T, R extends Collection<T>> R filter(Predicate<T> predicate, R result, Collection<T> collection) {
        BiFunction<R, T, R> filterReducer = (accumulator, item) -> {
            if (predicate.test(item)) {
                accumulator.add(item);
            }
            return accumulator;
        };
        return reduce(filterReducer, result, CollectionUtils::addAll, collection);
    }



}
