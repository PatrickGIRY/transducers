package experiments.transducteurs;

import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Pipeline {

    private Pipeline() {
    }

    static <T> List<T> filter(Predicate<T> predicate, Collection<T> collection) {
        return collection.stream().filter(predicate).collect(Collectors.toList());
    }

    static <T, R extends Collection<T>> BiFunction<R, T, R> filter(Predicate<T> predicate) {
        return (R accumulator, T item) -> {
            if (predicate.test(item)) {
                accumulator.add(item);
            }
            return accumulator;
        };
    }

    static <T, R> List<R> map(Function<T, R> mapper, Collection<T> collection) {
        return collection.stream().map(mapper).collect(Collectors.toList());
    }

    static <T, U, R extends Collection<U>> BiFunction<R, T, R> map(Function<T, U> mapper) {
        return (accumulator, item) -> {
            accumulator.add(mapper.apply(item));
            return accumulator;
        };
    }

    static <T, R> R reduce(BiFunction<R, T, R> reducer, R identity, BinaryOperator<R> combiner, Collection<T> collection) {
        return collection.stream().reduce(identity, reducer, combiner);
    }

    static <T, R> R parallelReduce(BiFunction<R, T, R> reducer, R identity, BinaryOperator<R> combiner, Collection<T> collection) {
        return collection.parallelStream().reduce(identity, reducer, combiner);
    }
}
