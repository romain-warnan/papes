package fr.plaisance.papes.data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@FunctionalInterface
public interface Itemizer<K, V> {

    Item map(Map.Entry<K, V> entry);

    static <K, V> List<Item> itemize(Map<K, V> map, Itemizer<K, V> itemizer) {
        return map
                .entrySet()
                .stream()
                .map(itemizer::map)
                .collect(Collectors.toList());
    }
}
