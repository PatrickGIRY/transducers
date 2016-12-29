package experiments.transducteurs;

import java.util.Collection;

public class CollectionUtils {

    private CollectionUtils() {
    }

    static <T, R extends Collection<T>> R add(R accumulator, T item) {
        accumulator.add(item);
        return accumulator;
    }

    static <T, R extends Collection<T>> R addAll(R c1, R c2) {
        c1.addAll(c2);
        return c1;
    }
}
