package experiments.transducteurs;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

abstract class Transducer<T, U> {

    private Transducer() {}

    abstract <R> BiFunction<R, U, R> apply(BiFunction<R, T, R> nextReducer);

     <V> Transducer<V, U> andThen(Transducer<V, T> after) {
        Objects.requireNonNull(after);
        return new Transducer<V, U>() {
            @Override
            public <R> BiFunction<R, U, R> apply(BiFunction<R, V, R> nextReducer) {
                return Transducer.this.apply(after.apply(nextReducer));
            }
        };
    }

    static <V> Transducer<V, V> filter(Predicate<V> predicate) {
        Objects.requireNonNull(predicate);
        return new Transducer<V, V>() {
            @Override
            public <R> BiFunction<R, V, R> apply(BiFunction<R, V, R> nextReducer) {
                return (accumulator, input) -> {
                    if (predicate.test(input)) {
                        return nextReducer.apply(accumulator, input);
                    }
                    return accumulator;
                };
            }
        };
    }

    static <T, U> Transducer<T, U> map(Function<U, T> mapper) {
        Objects.requireNonNull(mapper);
        return new Transducer<T, U>() {
            @Override
            public <R> BiFunction<R, U, R> apply(BiFunction<R, T, R> nextReducer) {
                return (accumulator, input) -> nextReducer.apply(accumulator, mapper.apply(input));
            }
        };
    }
}
