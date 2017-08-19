package fr.plaisance.papes.data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@FunctionalInterface
public interface Itemizer<K> {

    Item map(Map.Entry<K, Long> entry);

    static <K> List<Item> itemize(Map<K, Long> map, Itemizer<K> itemizer) {
        return map
                .entrySet()
                .stream()
                .map(itemizer::map)
                .collect(Collectors.toList());
    }
}
